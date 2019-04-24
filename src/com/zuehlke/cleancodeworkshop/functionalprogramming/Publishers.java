package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Publishers {
    private List<ScienceEssayPublisher> publishers;

    Publishers() {
        publishers = new ArrayList<>();
    }

    String findEssayTextBySubmissionId(Long submissionId) {
        for (var publisher : publishers) {
            Optional<String> essayTextBySubmissionId = publisher.findEssayTextBySubmissionId(submissionId);
            if (essayTextBySubmissionId.isPresent()) {
                return essayTextBySubmissionId.get();
            }
        }

        throw new IllegalArgumentException(String.format("Cannot find submission with id %d", submissionId));
    }

    void submit(Review review) {
        for (var publisher : publishers) {
            publisher.submit(review);
        }
    }

    void submit(Submission submission, ScienceEssayPublisher publisherToSubmit) {
        for (ScienceEssayPublisher publisher : publishers) {
            if (publisher.equals(publisherToSubmit)) {
                publisher.contribute(submission);
                return;
            }
        }
        throw new IllegalArgumentException(String.format("You are not contributor of %s", publisherToSubmit.getName()));
    }

    public void add(ScienceEssayPublisher publisher) {
        this.publishers.add(publisher);
    }

    public boolean canBeReviewed(Long submissionId) {
        for (ScienceEssayPublisher publisher : publishers) {
            if (publisher.canBeReviewed(submissionId)) {
                return true;
            }
        }
        return false;
    }
}
