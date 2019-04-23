package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.ArrayList;

public class Author implements
        IContributeScienceEssays,
        IReviewScienceEssays {

    private final Submissions submissionsToReview;
    private String name;

    Author(String name) {
        this.name = name;
        submissionsToReview = new Submissions(new ArrayList<>());
    }

    @Override
    public Submission submit(Essay essay) {
        return new Submission(this, essay);
    }

    @Override
    public void notifyAbout(Submission submission) {
        this.submissionsToReview.add(submission);
    }

    @Override
    public Submissions getSubmissionsToReview() {
        return submissionsToReview;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reviewSubmissionsFrom(IPublishScienceEssays publisher) {
        publisher.subscribeReviewer(this);
    }

    @Override
    public void contributeTo(IPublishScienceEssays publisher) {
        publisher.subscribeContributor(this);
    }
}
