package selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {

    private static final By SEARCH_BOX_LOC = By.name("q");
    private static final String GOOGLE_PAGE_URL = "http://www.google.com";
    private final WebDriver gmpDriver;  //private bo tylko w tej klasie używana zmienna gmpdriver; final aby w trakcie wykonania testu niezmienić tej stałej

    public GoogleMainPage(WebDriver driver) { //konstruktor klasy GoogleMainPage - używamy driver który wcześniej odpaliliśmy w GoogleTests
        gmpDriver = driver;   //zmienna gmpdriver musi być zdeklarowana poza metodą GoogleMainPage
        gmpDriver.get(GOOGLE_PAGE_URL);  //otwórz stronę googla
    }

    public GoogleResultPage search(String searchQuery) {
        WebElement searchBox = gmpDriver.findElement(SEARCH_BOX_LOC); //łatwiej utrzymać kod wyciągając do stałej SearchBoxLoc
        searchBox.sendKeys(searchQuery);
        searchBox.submit();

        return new GoogleResultPage(gmpDriver);
    }
}
