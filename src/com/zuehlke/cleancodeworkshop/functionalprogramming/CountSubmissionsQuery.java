package com.zuehlke.cleancodeworkshop.functionalprogramming;

class CountSubmissionsQuery {
    private final Submissions submissions;

    CountSubmissionsQuery(Submissions submissions) {
        this.submissions = submissions;
    }

    long containingTitle(String query) {
        return submissions.countSubmissionsWithTitleContaining(query);
    }
}
