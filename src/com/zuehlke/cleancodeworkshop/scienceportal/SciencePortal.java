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
        return submissions.findSubmissionById(submissionId)
                .map(submission -> submission.getEssay().getTitle() + "\n" + submission.getEssay().getText());
    }

    @Override
    public void submit(Review review) {
        reviewers.stream()
                .filter(r -> r.getName().equals(review.getReviewer().getName()))
                .findFirst()
                .ifPresentOrElse(
                        r -> this.submissions.add(review),
                        () -> {
                            throw new IllegalArgumentException(String.format("Reviewer %s is not registered as reviewer.", review.getReviewer()));
                        }
                );
    }

    @Override
    public void subscribeContributor(ScienceEssayContributor contributor) {
        this.contributors.add(contributor);
    }

    @Override
    public void contribute(Submission submission) {
        if (!contributors.contains(submission.getContributor())) {
            throw new IllegalArgumentException(String.format("Contributor %s is not registered as contributor.", submission.getContributor()));
        }

        save(submission);
        notifyReviewersOf(submission);
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

    boolean isContributor(ScienceEssayReviewer reviewer) {
        boolean isContained = false;
        if (reviewers.contains(reviewer)) {
            for (ScienceEssayContributor contributor : contributors) {
                if (isContributor(reviewer, contributor)) {
                    isContained = true;
                }
            }
        }
        return isContained;
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
        if (submissions.findSubmissionById(submissionId).isPresent()) {
            return submissions.findSubmissionById(submissionId).get().getNumberOfReviews();
        } else {
            return 0;
        }
    }

    @Override
    public boolean gotAccepted(long submissionId) {
        if (submissions.findSubmissionById(submissionId).isPresent()) {
            return submissions.findSubmissionById(submissionId).get().isAccepted();
        }
        return false;
    }

    @Override
    public Set<String> getNamesOfReviewersOf(long submissionId) {
        Optional<Submission> submissionById = submissions.findSubmissionById(submissionId);
        if (submissionById.isPresent()) {
            Submission submission = submissionById.get();
            return submission.getAllReviewerNames();
        }
        return Set.of();
    }

    @Override
    public boolean canBeReviewed(Long submissionId) {
        if (submissions.findSubmissionById(submissionId).isPresent()) {
            return !submissions.findSubmissionById(submissionId).get().isReviewed();
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciencePortal that = (SciencePortal) o;
        return name.equals(that.name);
    }
}
