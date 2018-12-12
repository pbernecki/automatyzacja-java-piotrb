package selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleMainPage {

    public static final By SEARCH_BOX_LOC = By.name("q");
    private static final String GOOGLE_PAGE_URL = "http://www.google.com";
    private final WebDriver gmpDriver;

    public GoogleMainPage(WebDriver driver) {
        gmpDriver = driver;
        gmpDriver.get(GOOGLE_PAGE_URL);
    }

    public GoogleResultPage search(String searchQuery) {
        WebElement searchBox = gmpDriver.findElement(SEARCH_BOX_LOC);
        searchBox.sendKeys(searchQuery);
        searchBox.submit();

        return new GoogleResultPage(gmpDriver);
    }
}
