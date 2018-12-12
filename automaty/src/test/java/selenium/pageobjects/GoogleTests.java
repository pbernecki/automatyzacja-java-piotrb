package selenium.pageobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pageobjects.pages.GoogleMainPage;
import selenium.pageobjects.pages.GoogleResultPage;

public class GoogleTests extends BaseTest {

    @Test
    public void canFindScrumOrgOnGoogle(){
        String pageTitle = "Scrum.org: Homepage";
        String pageUrl = "https://www.scrum.org/";

        //open google main page
        GoogleMainPage googlePage = new GoogleMainPage(driver);

        //search for scrum.org
        GoogleResultPage resultPage = googlePage.search("Scrum.org");

        //assert Scrum.org page is found
        Assertions.assertTrue(resultPage.contains(pageUrl));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageUrl, pageTitle),
                "Scrum.org page has correct title");
    }

    @Test
    public void canFindCodeSprinters(){
        String pageUrl = "http://agileszkolenia.pl/";
        String pageTitle = "Code Sprinters - Agile Experts -";
        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");
        Assertions.assertTrue(resultPage.contains(pageUrl));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageUrl, pageTitle));
    }
}
