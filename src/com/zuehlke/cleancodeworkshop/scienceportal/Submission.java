package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.Objects;
import java.util.Set;

public class Submission {
    private static long idCounter = 1;
    private final ScienceEssayContributor submitter;
    private final Essay essay;
    private final Reviews reviews;
    private long id;

    Submission(ScienceEssayContributor submitter, Essay essay) {
        this.submitter = submitter;
        this.essay = essay;
        this.reviews = new Reviews();
        this.id = idCounter++;
    }

    ScienceEssayContributor getContributor() {
        return submitter;
    }

    Essay getEssay() {
        return essay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Objects.equals(essay, that.essay)
                && Objects.equals(submitter, that.submitter);
    }

    int getNumberOfReviews() {
        return reviews.count();
    }

    void add(Review review) {
        this.reviews.add(review);
    }

    long getId() {
        return this.id;
    }

    boolean isReviewed() {
        return this.reviews.count() >= 2;
    }

    boolean wasReviewedBy(ScienceEssayReviewer reviewer) {
        return this.reviews.contains(reviewer);
    }

    boolean isAccepted() {
        if (isReviewed()) {
            return reviews.areAllAccepted();
        }
        return false;
    }

    Set<String> getAllReviewerNames() {
        return reviews.getAllReviewerNames();
    }

    private String getContributorName() {
        return getContributor().getName();
    }

    boolean isContributor(ScienceEssayContributor contributor) {
        return getContributorName().equalsIgnoreCase(contributor.getName());
    }

    boolean hasEssayContainingInTitle(String query) {
        return getEssay().getTitle().toLowerCase().contains(query.toLowerCase());
    }

    String format() {
        return getEssay().getTitle() + "\n" + getEssay().getText();
    }
}
