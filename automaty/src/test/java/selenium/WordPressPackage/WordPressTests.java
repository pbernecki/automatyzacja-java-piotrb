package selenium.WordPressPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.WordPressPackage.pages.WpMainPage;
import selenium.WordPressPackage.pages.WpNotePage;

import java.util.UUID;

public class WordPressTests extends BaseTest {

    @Test
    public void canAddCommentToFirstNote(){

        String comment = generateRandomText();  //generuję dane
        String author = "Piotr "+generateRandomText();
        String email = generateRandomEmail();

        WpMainPage mainPage = new WpMainPage(driver); //open wordpress main page
        WpNotePage latestNote = mainPage.openLatestNote();  //open first note
        WpNotePage latestNoteWithComment = latestNote.addComment(comment, author, email); //add comment to first note

        Assertions.assertTrue(latestNoteWithComment.commentExists(comment, author)); //check that comment is added
    }

    private String generateRandomEmail() {
        return generateRandomText()+ "@test.pl";
    }

    private String generateRandomText() {
        return UUID.randomUUID().toString();
    }
}
