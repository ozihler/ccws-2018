package com.zuehlke.cleancodeworkshop.scienceportal;
class CollectSubmissionsForAuthorQuery {
    private final Submissions submissions;

    CollectSubmissionsForAuthorQuery(Submissions submissions) {
        this.submissions = submissions;
    }

    Submissions of(ScienceEssayContributor contributor) {
        return submissions.getSubmissionsForContributor(contributor);
    }

}
