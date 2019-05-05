package com.zuehlke.cleancodeworkshop.scienceportal;
class Review {
    private final Long submissionId;
    private final Author reviewer;
    private boolean accepted;

    private Review(Long submissionId, Author reviewer) {
        this.submissionId = submissionId;
        this.reviewer = reviewer;
    }

    static Review of(Long submissionId, Author reviewer) {
        return new Review(submissionId, reviewer);
    }

    public static Review of(String essayText, Long submissionId, Author reviewer) {
        Review review = of(submissionId, reviewer);

        if (isOriginal(essayText)) {
            review.accept();
        } else {
            review.reject();
        }
        return review;
    }

    private static boolean isOriginal(String essayText) {
        return essayText.length() > 20;
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
