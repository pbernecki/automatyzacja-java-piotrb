package selenium.pageobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.pageobjects.pages.GoogleMainPage;
import selenium.pageobjects.pages.GoogleResultPage;

import java.util.UUID;

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

        String randomText = generateRandomText();  //przykład jak wyciągnąć metodę do klasy wyżej
    }

    @Test
    public void canFindCodeSprinters(){
        String pageUrl = "http://agileszkolenia.pl/";
        String pageTitle = "Code Sprinters - Agile Experts -";
        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");  //code sprinters - to też można wyciągnąc do parametrów testu
        Assertions.assertTrue(resultPage.contains(pageUrl)); //metoda zwraca true/false ; w metodzie są ukryte odwołania do drivera - odseparowanie frameworka od page objectów
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageUrl, pageTitle));
    }

    @ParameterizedTest
    @CsvSource({
            "Scrum.org, https://www.scrum.org/, Scrum.org: Homepage",
            "Code Sprinters, http://agileszkolenia.pl/, Code Sprinters - Agile Experts -",
            "Rafał Markowicz, http://markowicz.pro/, Rafał Markowicz – Kolejny piękny dzień"
    })
    public void canFindPagesOnGoogle(String query, String url, String title) {
        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search(query);

        Assertions.assertTrue(resultPage.contains(url));
        Assertions.assertTrue(resultPage.containsResultWithTitle(url, title));
    }
}

//import static org.junit.jupiter.api.Assertions.*;  jeżeli jest to: static i * , to
//assertTrue(resultPage.contains(pageUrl)); tu nie trzeba wołać obiektu klasy Assertions. i metoda assertTrue jest statyczna (widać pochylą czcionkę)