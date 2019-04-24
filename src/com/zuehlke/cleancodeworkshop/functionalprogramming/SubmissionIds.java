package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubmissionIds {
    private final List<Long> submissionIds;

    public SubmissionIds() {
        this.submissionIds = new ArrayList<>();
    }

    public void add(long id) {
        this.submissionIds.add(id);
    }

    public int count() {
        return submissionIds.size();
    }

    public Optional<Long> next() {
        if (submissionIds.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(submissionIds.get(0));
    }

    public boolean hasNext() {
        return !this.submissionIds.isEmpty();
    }

    public void remove(Long submissionId) {
        this.submissionIds.remove(submissionId);
    }
}
