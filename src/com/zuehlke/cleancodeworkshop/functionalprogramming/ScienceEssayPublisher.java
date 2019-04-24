package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.Optional;
import java.util.Set;

public interface ScienceEssayPublisher {

    void contribute(Submission submission);

    void subscribeContributor(ScienceEssayContributor contributor);

    Submissions getSubmissionsOf(ScienceEssayContributor contributor);

    void subscribeReviewer(ScienceEssayReviewer reviewer);

    Optional<String> findEssayTextBySubmissionId(Long submissionId);

    void submit(Review review);

    String getName();

    int getNumberOfReviewsBySubmissionId(long submissionId);

    boolean gotAccepted(long submissionId);

    Set<String> getNamesOfReviewersOf(long submissionId);

    boolean canBeReviewed(Long submissionId);
}
