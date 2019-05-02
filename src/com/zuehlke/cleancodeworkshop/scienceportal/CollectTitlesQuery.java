package com.zuehlke.cleancodeworkshop.scienceportal;
import java.util.Set;

class CollectTitlesQuery {
    Set<String> of(Submissions submissions) {
        return submissions.getTitlesOfAllSubmissions();
    }
}
