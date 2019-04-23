package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.Set;

public class CollectTitlesQuery {
    public Set<String> of(Submissions submissions) {
        return submissions.getTitlesOfAllSubmissions();
    }
}
