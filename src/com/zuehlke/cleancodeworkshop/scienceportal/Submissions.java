package com.zuehlke.cleancodeworkshop.scienceportal;
import java.util.*;

public class Submissions {
    private final List<Submission> submissions;

    Submissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    Set<String> getTitlesOfAllSubmissions() {
        Set<String> titlesOfAllSubmissions = new HashSet<>();
        for (Submission submission : submissions) {
            addSubmission(titlesOfAllSubmissions, submission);
        }
        return titlesOfAllSubmissions;
    }

    private void addSubmission(Set<String> titlesOfAllSubmissions, Submission submission) {
        Essay essay = submission.getEssay();
        if (essay != null) {
            String title = essay.getTitle();
            titlesOfAllSubmissions.add(title);
        }
    }

    Submissions getSubmissionsForContributor(ScienceEssayContributor contributor) {
        List<Submission> submissionsOfContributor = new ArrayList<>();

        submissions.stream()
                .filter(submission -> {
                    String name = submission.getContributor().getName();
                    String contributorName = contributor.getName();
                    return name.equalsIgnoreCase(contributorName);
                })
                .forEach(submissionsOfContributor::add);

        return new Submissions(submissionsOfContributor);
    }

    long countSubmissionsWithTitleContaining(String query) {
        List<Submission> essaysWithQueryInTitle = new ArrayList<>();

        submissions.forEach(submission -> {
            String submissionTitle = submission.getEssay().getTitle().toLowerCase();
            String queryFormatted = query.toLowerCase();

            if (submissionTitle.contains(queryFormatted)) {
                essaysWithQueryInTitle.add(submission);
            }
        });

        return essaysWithQueryInTitle.size();
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
        for (Submission submission : submissions) {
            if (submission.getId() == review.getSubmissionId()) {
                if (!submission.isReviewed()) {
                    if (!submission.wasReviewedBy(review.getReviewer())) {
                        submission.add(review);
                    }
                }
            }
        }
    }

    Optional<Submission> findSubmissionById(Long submissionId) {
        for (Submission submission : submissions) {
            if (submission.getId() == submissionId) {
                return Optional.of(submission);
            }
        }
        return Optional.empty();
    }
}
