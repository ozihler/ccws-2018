package com.zuehlke.cleancodeworkshop.functionalprogramming;

public interface IPublishScienceEssays {

    void contribute(Submission submission);

    void subscribeContributor(IContributeScienceEssays contributor);

    Submissions getSubmissionsOf(IContributeScienceEssays contributor);

    void subscribeReviewer(IReviewScienceEssays reviewer);
}
