package com.zuehlke.cleancodeworkshop.scienceportal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubmissionIds {
    private final List<Long> submissionIds;

    SubmissionIds() {
        this.submissionIds = new ArrayList<>();
    }

    void add(long id) {
        this.submissionIds.add(id);
    }

    public int count() {
        return submissionIds.size();
    }

    Optional<Long> next() {
        if (submissionIds.isEmpty()) {
            return Optional.empty();
        }

        return Optional.ofNullable(submissionIds.get(0));
    }

    boolean hasNext() {
        return !this.submissionIds.isEmpty();
    }

    void remove(Long submissionId) {
        this.submissionIds.remove(submissionId);
    }
}
