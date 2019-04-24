package com.zuehlke.cleancodeworkshop.functionalprogramming;

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
        if (submissions.findSubmissionById(submissionId).isPresent()) {
            Submission submission = submissions.findSubmissionById(submissionId).get();
            return Optional.of(submission.getEssay().getTitle() + "\n" + submission.getEssay().getText());
        }
        return Optional.empty();
    }

    @Override
    public void submit(Review review) {
        if (reviewers.contains(review.getReviewer())) {
            this.submissions.add(review);
            return;
        }
        throw new IllegalArgumentException(String.format("Reviewer %s is not registered as reviewer.", review.getReviewer()));
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

        submissions.add(submission);

        reviewers.forEach(reviewer -> {
            if (!reviewer.getName().equalsIgnoreCase(submission.getContributor().getName())) {
                reviewer.notifyAbout(submission);
            }
        });
    }

    Set<String> getTitlesOfAllSubmissions() {
        return Queries.collectTitles().of(submissions);
    }

    boolean isContributor(ScienceEssayReviewer reviewer) {
        if (reviewers.contains(reviewer)) {
            for (ScienceEssayContributor contributor : contributors) {
                if (reviewer.getName().equalsIgnoreCase(contributor.getName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
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
