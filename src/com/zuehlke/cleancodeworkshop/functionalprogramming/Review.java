package com.zuehlke.cleancodeworkshop.functionalprogramming;

public class Review {
    private final Long submissionId;
    private final Author reviewer;
    private boolean accepted;

    public Review(Long submissionId, Author reviewer) {
        this.submissionId = submissionId;
        this.reviewer = reviewer;
    }

    public void accept() {
        this.accepted = true;
    }

    public void reject() {
        this.accepted = false;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public ScienceEssayReviewer getReviewer() {
        return this.reviewer;
    }
}
