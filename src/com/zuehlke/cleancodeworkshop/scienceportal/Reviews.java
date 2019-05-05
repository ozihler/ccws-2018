package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

class Reviews {
    private final List<Review> reviews;

    Reviews() {
        this.reviews = new ArrayList<>();
    }

    int count() {
        return reviews.size();
    }

    void add(Review review) {
        this.reviews.add(review);
    }

    boolean contains(ScienceEssayReviewer reviewer) {
        return reviews.stream()
                .anyMatch(review -> review.getReviewer().equals(reviewer));
    }

    boolean areAllAccepted() {
        return reviews.stream()
                .allMatch(Review::isAccepted);
    }

    Set<String> getAllReviewerNames() {
        return reviews.stream()
                .map(Review::getReviewer)
                .map(ScienceEssayReviewer::getName)
                .collect(toSet());
    }

}
