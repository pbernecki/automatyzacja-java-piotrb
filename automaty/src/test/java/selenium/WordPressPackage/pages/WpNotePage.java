package selenium.WordPressPackage.pages;

import org.openqa.selenium.WebDriver;

public class WpNotePage {
    private final WebDriver notDriver;

    public WpNotePage(WebDriver driver) { //konstruktor
        notDriver = driver;
    }

    public WpNotePage addComment(String comment, String author, String email) {
        //todo: tu dodajemy notatki
        return new WpNotePage(notDriver);
    }

    public boolean commentExists(String comment, String author) {
        // todo: sprawdzić czy comment is published
        return false;
    }
}
