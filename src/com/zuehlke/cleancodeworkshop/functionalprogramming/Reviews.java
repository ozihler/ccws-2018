package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reviews {
    private final List<Review> reviews;

    Reviews() {
        this.reviews = new ArrayList<>();
    }

    int count() {
        return reviews.size();
    }

    public void add(Review review) {
        this.reviews.add(review);
    }

    public boolean contains(ScienceEssayReviewer reviewer) {
        boolean contains = false;
        for (Review review : reviews) {
            contains = review.getReviewer().equals(reviewer);
        }
        return contains;
    }

    boolean areAllAccepted() {
        boolean isAccepted = true;
        for (Review review : reviews) {
            isAccepted = isAccepted(review, isAccepted);
        }
        return isAccepted;
    }

    private boolean isAccepted(Review review, boolean isAccepted) {
        if (!review.isAccepted()) {
            isAccepted = false;
        }
        return isAccepted;
    }

    Set<String> getAllReviewerNames() {
        HashSet<String> reviewerNames = new HashSet<>();
        reviews.forEach(review -> addReviewer(reviewerNames, review));
        return reviewerNames;
    }

    private void addReviewer(HashSet<String> reviewerNames, Review review) {
        ScienceEssayReviewer reviewer = review.getReviewer();
        String name = reviewer.getName();
        reviewerNames.add(name);
    }
}
