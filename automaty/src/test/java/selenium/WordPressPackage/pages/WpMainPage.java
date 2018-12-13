package selenium.WordPressPackage.pages;

import org.openqa.selenium.WebDriver;

public class WpMainPage {
    private final WebDriver mpDriver;

    public WpMainPage(WebDriver driver) { //konstruktor
        mpDriver=driver;
        //todo: tu musimy otworzyc strone
    }

    public WpNotePage openLatestNote() {
        //todo: ty otwieramy najpozniejsza notkę
        return new WpNotePage(mpDriver);
    }
}
