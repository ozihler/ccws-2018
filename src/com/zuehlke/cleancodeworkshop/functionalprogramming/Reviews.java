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
        for (Review review : reviews) {
            if (review.getReviewer().equals(reviewer)) {
                return true;
            }
        }
        return false;
    }

    boolean areAllAccepted() {
        boolean isAccepted = true;
        for (Review review : reviews) {
            if (!review.isAccepted()) {
                isAccepted = false;
            }
        }
        return isAccepted;
    }

    Set<String> getAllReviewerNames() {
        HashSet<String> reviewerNames = new HashSet<>();
        reviews.forEach(review -> {
            ScienceEssayReviewer reviewer = review.getReviewer();
            String name = reviewer.getName();
            reviewerNames.add(name);
        });
        return reviewerNames;
    }
}
