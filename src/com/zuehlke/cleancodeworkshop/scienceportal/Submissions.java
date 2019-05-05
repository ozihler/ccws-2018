package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Submissions {
    private final List<Submission> submissions;

    Submissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    Set<String> getTitlesOfAllSubmissions() {
        return submissions.stream()
                .map(Submission::getEssay)
                .filter(Objects::nonNull)
                .map(Essay::getTitle)
                .collect(toSet());
    }

    Submissions getSubmissionsForContributor(ScienceEssayContributor contributor) {
        return new Submissions(submissionsOf(contributor));
    }

    private List<Submission> submissionsOf(ScienceEssayContributor essayContributor) {
        return submissions.stream()
                .filter(submission -> submission.isContributor(essayContributor))
                .collect(Collectors.toList());
    }

    long countSubmissionsWithTitleContaining(String query) {
        return submissions.stream()
                .filter(submission -> submission.hasEssayContainingInTitle(query))
                .count();
    }

    void add(Submission submission) {
        if (submission != null) {
            submissions.add(submission);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submissions that = (Submissions) o;
        return Objects.equals(submissions, that.submissions);
    }

    int count() {
        return this.submissions.size();
    }


    void add(Review review) {
        submissions.stream()
                .filter(submission -> submission.getId() == review.getSubmissionId())
                .filter(submission -> !submission.wasReviewedBy(review.getReviewer()))
                .filter(submission -> !submission.isReviewed())
                .findFirst()
                .ifPresent(submission -> submission.add(review));
    }

    Optional<Submission> findSubmissionById(Long submissionId) {
        return submissions.stream()
                .filter(submission -> submission.getId() == submissionId)
                .findFirst();
    }

    Optional<String> formatEssayTextIfExists(Long submissionId) {
        return findSubmissionById(submissionId).map(Submission::format);
    }
}
