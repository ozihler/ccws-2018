package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.Objects;

public class Submission {
    private final IContributeScienceEssays submitter;
    private final Essay essay;

    Submission(IContributeScienceEssays submitter, Essay essay) {
        this.submitter = submitter;
        this.essay = essay;
    }

    public void to(IPublishScienceEssays publisher) {
        publisher.contribute(this);
    }

    IContributeScienceEssays getContributor() {
        return submitter;
    }

    Essay getEssay() {
        return essay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Objects.equals(essay, that.essay)
                && Objects.equals(submitter, that.submitter);
    }
}
