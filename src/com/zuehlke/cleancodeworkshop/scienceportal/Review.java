package com.zuehlke.cleancodeworkshop.scienceportal;
class Review {
    private final Long submissionId;
    private final Author reviewer;
    private boolean accepted;

    Review(Long submissionId, Author reviewer) {
        this.submissionId = submissionId;
        this.reviewer = reviewer;
    }

    void accept() {
        this.accepted = true;
    }

    void reject() {
        this.accepted = false;
    }

    boolean isAccepted() {
        return accepted;
    }

    long getSubmissionId() {
        return submissionId;
    }

    ScienceEssayReviewer getReviewer() {
        return this.reviewer;
    }
}
