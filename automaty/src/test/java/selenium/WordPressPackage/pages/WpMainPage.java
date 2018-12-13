package selenium.WordPressPackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WpMainPage {
    private final WebDriver mpDriver;

    public WpMainPage(WebDriver driver) { //konstruktor
        mpDriver=driver;  //zassanie drivera do tej klasy
        mpDriver.get("https://automatyzacja.benedykt.net"); //otwarcie właściwej strony
    }

    public WpNotePage openLatestNote() {
        // tu otwieramy najpóźniejszą notkę
        WebElement note = mpDriver.findElement(By.cssSelector(".entry-title > a"));
        note.click();

        return new WpNotePage(mpDriver);
    }
}
