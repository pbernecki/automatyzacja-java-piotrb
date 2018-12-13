package selenium.WordPressPackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;  //klasy pochodne (extends BaseTest) też to widzą i używają

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "c:\\selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitBrowser() {
        driver.quit();
    }
}
