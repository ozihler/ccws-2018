package com.zuehlke.cleancodeworkshop.functionalprogramming;

import java.util.*;

public class Submissions {
    private final List<Submission> submissions;

    Submissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    Set<String> getTitlesOfAllSubmissions() {
        Set<String> titlesOfAllSubmissions = new HashSet<>();
        submissions.forEach(submission -> {
            Essay essay = submission.getEssay();
            titlesOfAllSubmissions.add(essay.getTitle());
        });
        return titlesOfAllSubmissions;
    }

    Submissions getSubmissionsForContributor(IContributeScienceEssays contributor) {
        List<Submission> submissionsOfContributor = new ArrayList<>();

        submissions.forEach(submission -> {
            IContributeScienceEssays contributorOfSubmission = submission.getContributor();

            if (contributorOfSubmission.getName().equalsIgnoreCase(contributor.getName())) {
                submissionsOfContributor.add(submission);
            }
        });

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
        submissions.add(submission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submissions that = (Submissions) o;
        return Objects.equals(submissions, that.submissions);
    }
}
