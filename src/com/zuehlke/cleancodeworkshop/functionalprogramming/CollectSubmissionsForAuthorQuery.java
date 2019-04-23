package com.zuehlke.cleancodeworkshop.functionalprogramming;

public class CollectSubmissionsForAuthorQuery {
    private final Submissions submissions;

    CollectSubmissionsForAuthorQuery(Submissions submissions) {
        this.submissions = submissions;
    }

    public Submissions of(IContributeScienceEssays contributor) {
        return submissions.getSubmissionsForContributor(contributor);
    }

}
