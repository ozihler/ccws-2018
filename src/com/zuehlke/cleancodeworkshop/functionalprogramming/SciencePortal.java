package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class SciencePortal implements IPublishScienceEssays {
    private final String name;
    private final Submissions submissions;
    private List<IReviewScienceEssays> reviewers = new ArrayList<>();
    private List<IContributeScienceEssays> contributors = new ArrayList<>();

    SciencePortal(String name, Submissions submissions) {
        this.name = name;
        this.submissions = submissions;
    }

    @Override
    public void subscribeReviewer(IReviewScienceEssays reviewer) {
        this.reviewers.add(reviewer);
    }

    @Override
    public void subscribeContributor(IContributeScienceEssays contributor) {
        this.contributors.add(contributor);
    }

    @Override
    public void contribute(Submission submission) {
        if (!contributors.contains(submission.getContributor())) {
            return;
        }

        submissions.add(submission);
        reviewers.forEach(reviewer -> reviewer.notifyAbout(submission));
    }

    Set<String> getTitlesOfAllSubmissions() {
        return Queries.collectTitles().of(submissions);
    }

    boolean isContributor(IReviewScienceEssays reviewer) {
        if (reviewers.contains(reviewer)) {
            for (IContributeScienceEssays contributor : contributors) {
                if (reviewer.getName().equalsIgnoreCase(contributor.getName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public Submissions getSubmissionsOf(IContributeScienceEssays contributor) {
        return Queries.collectSubmissions(submissions).of(contributor);
    }

    long countSubmissionsWithTitleContaining(String query) {
        return Queries.countSubmissions(submissions).containingTitle(query);
    }

    public String getName() {
        return name;
    }
}
