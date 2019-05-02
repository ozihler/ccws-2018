package com.zuehlke.cleancodeworkshop.scienceportal;
class CountSubmissionsQuery {
    private final Submissions submissions;

    CountSubmissionsQuery(Submissions submissions) {
        this.submissions = submissions;
    }

    long containingTitle(String query) {
        return submissions.countSubmissionsWithTitleContaining(query);
    }
}
