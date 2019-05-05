package com.zuehlke.cleancodeworkshop.scienceportal;

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
        while (submissionsToReview.next().isPresent()) {
            Long submissionId = submissionsToReview.next().get();
            if (this.publishers.canBeReviewed(submissionId)) {
                String essayText = this.publishers.findEssayTextBySubmissionId(submissionId);
                this.publishers.submit(Review.of(essayText, submissionId, this));
                return;
            } else {
                submissionsToReview.remove(submissionId);
            }
        }
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
