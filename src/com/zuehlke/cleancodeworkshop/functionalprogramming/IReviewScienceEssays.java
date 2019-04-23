package com.zuehlke.cleancodeworkshop.functionalprogramming;

public interface IReviewScienceEssays {
    void notifyAbout(Submission submission);

    Submissions getSubmissionsToReview();

    void reviewSubmissionsFrom(IPublishScienceEssays publisher);

    String getName();
}
