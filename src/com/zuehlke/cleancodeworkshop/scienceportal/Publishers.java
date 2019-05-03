package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Publishers {
    private List<ScienceEssayPublisher> publishers;

    Publishers() {
        publishers = new ArrayList<>();
    }

    String findEssayTextBySubmissionId(Long submissionId) {
        return publishers.stream()
                .map(publisher -> publisher.findEssayTextBySubmissionId(submissionId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot find submission with id %d", submissionId)));
    }

    void submit(Review review) {
        for (var publisher : publishers) {
            publisher.submit(review);
        }
    }

    void submit(Submission submission, ScienceEssayPublisher publisherToSubmit) {
        publishers.stream()
                .filter(publisher -> publisher.equals(publisherToSubmit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("You are not contributor of %s", publisherToSubmit.getName())))
                .contribute(submission);
    }

    void add(ScienceEssayPublisher publisher) {
        this.publishers.add(publisher);
    }

    boolean canBeReviewed(Long submissionId) {
        return publishers.stream()
                .anyMatch(publisher -> publisher.canBeReviewed(submissionId));
    }
}
