package com.zuehlke.cleancodeworkshop.scienceportal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;


@DisplayName("An author")
class AuthorTest {

    @Test
    @DisplayName("can receive essays to review")
    void testReviewer() {

        ScienceEssayPublisher acm = new SciencePortal("ACM");
        ScienceEssayContributor contributor = new Author("scienceportal.Author 1");
        ScienceEssayReviewer reviewer = new Author("scienceportal.Author 2");

        contributor.contributeTo(acm);
        reviewer.reviewFor(acm);

        Essay essay = EssayBuilder.newEssay()
                .withTitle("Simple scienceportal.Essay")
                .withText("This is a very simple and short essay")
                .complete();

        contributor.submit(essay, acm);

        Assertions.assertEquals(new Submissions(List.of(new Submission(contributor, essay))), acm.getSubmissionsOf(contributor));
        Assertions.assertEquals(new Submissions(List.of(new Submission(contributor, essay))).count(), reviewer.getSubmissionsToReview().count());
    }

    @Test
    @DisplayName("can submit a essays")
    void whenSubmittingAnEssayAllProofReadersAreInformed() {
        SciencePortal acm = new SciencePortal("ACM");
        Author author = new Author("scienceportal.Author 1");
        Author anotherAuthor = new Author("scienceportal.Author 2");

        author.contributeTo(acm);
        anotherAuthor.contributeTo(acm);

        Essay essay = EssayBuilder.newEssay()
                .withTitle("Simple scienceportal.Essay")
                .withText("This is a very simple and short essay")
                .complete();

        author.submit(essay, acm);
        Assertions.assertEquals(new Submissions(List.of(new Submission(author, essay))), acm.getSubmissionsOf(author));
        Assertions.assertEquals(new Submissions(List.of()), acm.getSubmissionsOf(anotherAuthor));

    }

    @Test
    @DisplayName("can review a received submission and post it back to the publisher")
    void testReviewing() {
        ScienceEssayPublisher publisher = new SciencePortal("ACM");
        ScienceEssayContributor contributor = new Author("Contributor 1");
        ScienceEssayReviewer reviewer = new Author("Reviewer 1");
        ScienceEssayReviewer otherReviewer = new Author("Reviewer 2");
        ScienceEssayReviewer anotherReviewer = new Author("Reviewer 3");

        contributor.contributeTo(publisher);
        reviewer.reviewFor(publisher);
        otherReviewer.reviewFor(publisher);
        anotherReviewer.reviewFor(publisher);

        Essay essayToReview = EssayBuilder.newEssay().withText("my essay").complete();

        long submissionId = contributor.submit(essayToReview, publisher);
        reviewer.reviewNextSubmission();

        Assertions.assertEquals(1, publisher.getNumberOfReviewsBySubmissionId(submissionId));

        reviewer.reviewNextSubmission();
        Assertions.assertEquals(1, publisher.getNumberOfReviewsBySubmissionId(submissionId));

        otherReviewer.reviewNextSubmission();
        Assertions.assertEquals(2, publisher.getNumberOfReviewsBySubmissionId(submissionId));
        Assertions.assertFalse(publisher.gotAccepted(submissionId));

        anotherReviewer.reviewNextSubmission();
        Assertions.assertEquals(2, publisher.getNumberOfReviewsBySubmissionId(submissionId));
        Assertions.assertEquals(Set.of(reviewer.getName(), otherReviewer.getName()), publisher.getNamesOfReviewersOf(submissionId));

        Essay essayToReview2 = EssayBuilder.newEssay()
                .withText("my essay")
                .withText("this is a long text so that the submission is going to be accepted")
                .complete();
        long submissionId2 = contributor.submit(essayToReview2, publisher);

        anotherReviewer.reviewNextSubmission();
        Assertions.assertEquals(2, publisher.getNumberOfReviewsBySubmissionId(submissionId));
        Assertions.assertEquals(Set.of(reviewer.getName(), otherReviewer.getName()), publisher.getNamesOfReviewersOf(submissionId));
        Assertions.assertEquals(1, publisher.getNumberOfReviewsBySubmissionId(submissionId2));
        Assertions.assertEquals(Set.of(anotherReviewer.getName()), publisher.getNamesOfReviewersOf(submissionId2));

    }
}
