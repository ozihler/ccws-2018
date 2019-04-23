package com.zuehlke.cleancodeworkshop.functionalprogramming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.zuehlke.cleancodeworkshop.functionalprogramming.EssayBuilder.newEssay;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("An author")
class AuthorTest {
    @Test
    @DisplayName("can receive essays to review")
    void testReviewer() {

        IPublishScienceEssays acm = new SciencePortal("ACM", new Submissions(new ArrayList<>()));
        IContributeScienceEssays contributor = new Author("Author 1");
        IReviewScienceEssays reviewer = new Author("Author 2");

        contributor.contributeTo(acm);
        reviewer.reviewSubmissionsFrom(acm);

        Essay essay = newEssay()
                .withTitle("Simple Essay")
                .withText("This is a very simple and short essay")
                .complete();

        contributor.submit(essay)
                .to(acm);

        assertEquals(new Submissions(List.of(new Submission(contributor, essay))), acm.getSubmissionsOf(contributor));
        assertEquals(new Submissions(List.of(new Submission(contributor, essay))), reviewer.getSubmissionsToReview());
    }

    @Nested
    @DisplayName("can submit a essays")
    class whenSubmittingAnEssayAllProofReadersAreInformed {
        @Test
        void test() {
            SciencePortal acm = new SciencePortal("ACM", new Submissions(new ArrayList<>()));
            Author author = new Author("Author 1");
            Author anotherAuthor = new Author("Author 2");

            author.contributeTo(acm);
            anotherAuthor.contributeTo(acm);

            Essay essay = newEssay()
                    .withTitle("Simple Essay")
                    .withText("This is a very simple and short essay")
                    .complete();

            author.submit(essay)
                    .to(acm);

            assertEquals(new Submissions(List.of(new Submission(author, essay))), acm.getSubmissionsOf(author));
            assertEquals(new Submissions(List.of()), acm.getSubmissionsOf(anotherAuthor));
        }
    }
}
