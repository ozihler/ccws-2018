package com.zuehlke.cleancodeworkshop.scienceportal;

import java.util.Optional;

public class Author implements ScienceEssayContributor, ScienceEssayReviewer {
    private final String name;
    private final SubmissionIds submissionsToReview;
    private final Publishers publishers;

    Author(String name) {
        this.name = name;
        submissionsToReview = new SubmissionIds();
        this.publishers = new Publishers();
    }

    @Override
    public long submit(Essay essay, ScienceEssayPublisher publisher) {
        Submission submission = new Submission(this, essay);
        this.publishers.submit(submission, publisher);
        return submission.getId();
    }

    @Override
    public void notifyAbout(Submission submission) {
        this.submissionsToReview.add(submission.getId());
    }

    @Override
    public SubmissionIds getSubmissionsToReview() {
        return submissionsToReview;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void reviewNextSubmission() {

        while (submissionsToReview.hasNext()) {
            Optional<Long> submissionsForContributor = submissionsToReview.next();

            if (submissionsForContributor.isPresent()) {
                Long submissionId = submissionsForContributor.get();
                if (this.publishers.canBeReviewed(submissionId)) {
                    String essayText = this.publishers.findEssayTextBySubmissionId(submissionId);

                    Review review = new Review(submissionId, this);
                    if (isOriginal(essayText)) {
                        review.accept();
                    } else {
                        review.reject();
                    }

                    this.publishers.submit(review);
                    return;
                } else {
                    submissionsToReview.remove(submissionId);
                }
            }
        }


    }

    private boolean isOriginal(String essayText) {
        return essayText.length() > 20;
    }

    @Override
    public void reviewFor(ScienceEssayPublisher publisher) {
        publisher.subscribeReviewer(this);
        this.publishers.add(publisher);
    }

    @Override
    public void contributeTo(ScienceEssayPublisher publisher) {
        publisher.subscribeContributor(this);
        this.publishers.add(publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name);
    }

}
