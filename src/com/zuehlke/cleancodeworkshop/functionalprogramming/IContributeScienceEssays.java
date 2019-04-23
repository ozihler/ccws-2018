package com.zuehlke.cleancodeworkshop.functionalprogramming;

public interface IContributeScienceEssays {
    void contributeTo(IPublishScienceEssays publisher);

    Submission submit(Essay essay);

    String getName();
}
