package selenium.pageobjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.stream.Stream;

public class GoogleResultPage {
    public static final By RESULT_LOC = By.cssSelector("#search .rc .r");

    public GoogleResultPage(WebDriver driver) {
        grpDriver = driver;
    }

    private final WebDriver grpDriver;


    public boolean contains(String resultUrl) {
        Stream<WebElement> results = getResultsByUrl(resultUrl);

        if (results.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean containsResultWithTitle(String pageUrl, String pageTitle) {
        Stream<WebElement> results = getResultsByUrl(pageUrl)
                .filter(n -> n.findElement(By.tagName("h3")).getText().equals(pageTitle));
        if (results.count() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Stream<WebElement> getResultsByUrl(String resultUrl) {
        return grpDriver.findElements(RESULT_LOC)
                .stream()
                .filter(n -> n.findElement(By.tagName("a")).getAttribute("href").equals(resultUrl));
    }
}
