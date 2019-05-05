package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

class SciencePortal implements ScienceEssayPublisher {
    private final String name;
    private final Submissions submissions;
    private final List<ScienceEssayReviewer> reviewers;
    private final List<ScienceEssayContributor> contributors;

    SciencePortal(String name, Submissions submissions) {
        this.name = name;
        this.submissions = submissions;
        reviewers = new ArrayList<>();
        contributors = new ArrayList<>();
    }

    SciencePortal(String name) {
        this(name, new Submissions(new ArrayList<>()));
    }

    @Override
    public void subscribeReviewer(ScienceEssayReviewer reviewer) {
        this.reviewers.add(reviewer);
    }

    @Override
    public Optional<String> findEssayTextBySubmissionId(Long submissionId) {
        return submissions.formatEssayTextIfExists(submissionId);
    }

    @Override
    public void submit(Review review) {
        if (!isRegisteredReviewer(review.getReviewer())) {
            throw new IllegalArgumentException(String.format("Reviewer %s is not registered as reviewer.", review.getReviewer()));
        }

        save(review);
    }

    private void save(Review review) {
        this.submissions.add(review);
    }

    private boolean isRegisteredReviewer(ScienceEssayReviewer reviewerToCheck) {
        return reviewers.stream()
                .anyMatch(reviewer -> reviewer.getName().equals(reviewerToCheck.getName()));
    }

    @Override
    public void subscribeContributor(ScienceEssayContributor contributor) {
        this.contributors.add(contributor);
    }

    @Override
    public void contribute(Submission submission) {
        if (!isContributor(submission.getContributor())) {
            throw new IllegalArgumentException(String.format("Contributor %s is not registered as contributor.", submission.getContributor()));
        }

        save(submission);
        notifyReviewersOf(submission);
    }

    private boolean isContributor(ScienceEssayContributor contributor) {
        return contributors.contains(contributor);
    }

    private void save(Submission submission) {
        submissions.add(submission);
    }

    private void notifyReviewersOf(Submission submission) {
        reviewers.stream()
                .filter(reviewer -> !isContributor(reviewer, submission.getContributor()))
                .forEach(reviewer -> reviewer.notifyAbout(submission));
    }

    private boolean isContributor(ScienceEssayReviewer reviewer, ScienceEssayContributor contributor) {
        return reviewer.getName().equalsIgnoreCase(contributor.getName());
    }

    Set<String> getTitlesOfAllSubmissions() {
        return Queries.collectTitles().of(submissions);
    }

    boolean isRegisteredAsContributor(ScienceEssayReviewer reviewer) {
        return isReviewer(reviewer) && isContributor(reviewer);
    }

    private boolean isContributor(ScienceEssayReviewer reviewer) {
        return contributors.stream()
                .anyMatch(contributor -> isContributor(reviewer, contributor));
    }

    private boolean isReviewer(ScienceEssayReviewer reviewer) {
        return reviewers.contains(reviewer);
    }

    @Override
    public Submissions getSubmissionsOf(ScienceEssayContributor contributor) {
        return Queries.collectSubmissions(submissions).of(contributor);
    }

    long countSubmissionsWithTitleContaining(String query) {
        return Queries.countSubmissions(submissions).containingTitle(query);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getNumberOfReviewsBySubmissionId(long submissionId) {
        return submissions.findSubmissionById(submissionId)
                .map(Submission::getNumberOfReviews)
                .orElse(0);
    }

    @Override
    public boolean gotAccepted(long submissionId) {
        return submissions.findSubmissionById(submissionId)
                .map(Submission::isAccepted)
                .orElse(false);
    }

    @Override
    public Set<String> getNamesOfReviewersOf(long submissionId) {
        return submissions.findSubmissionById(submissionId)
                .map(Submission::getAllReviewerNames)
                .orElseGet(Set::of);
    }

    @Override
    public boolean canBeReviewed(Long submissionId) {
        return submissions.findSubmissionById(submissionId)
                .filter(submission -> !submission.isReviewed())
                .isPresent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciencePortal that = (SciencePortal) o;
        return name.equals(that.name);
    }
}
