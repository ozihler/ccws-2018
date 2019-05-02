package com.zuehlke.cleancodeworkshop.scienceportal;
class Queries {
    static CountSubmissionsQuery countSubmissions(Submissions submissions) {
        return new CountSubmissionsQuery(submissions);
    }

    static CollectSubmissionsForAuthorQuery collectSubmissions(Submissions submissions) {
        return new CollectSubmissionsForAuthorQuery(submissions);
    }

    static CollectTitlesQuery collectTitles() {
        return new CollectTitlesQuery();
    }
}
