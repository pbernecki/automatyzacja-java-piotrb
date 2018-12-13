package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordpressTests {
    private WebDriver driver;

    @BeforeEach
    public void startDriver() {
        System.setProperty("webdriver.chrome.driver", "c:\\selenium\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void  addCommentToFirstArticle() {
        driver.get("https://automatyzacja.benedykt.net/");
        WebElement post = driver.findElement(By.cssSelector(".entry-title > a"));
        post.click();

        WebElement inputComentText = driver.findElement(By.id("comment"));
        String uuid = UUID.randomUUID().toString();
        inputComentText.sendKeys(uuid);

        WebElement inputAuthor = driver.findElement(By.id("author"));
        inputAuthor.sendKeys("Jan Kowalski");

        driver.findElement(By.id("email")).sendKeys("a@a.pl");

        WebElement inputUrl = driver.findElement(By.id("url"));
        inputUrl.sendKeys("http://www.mojastrona.pl");

        WebElement submitComment = driver.findElement(By.id("submit"));
        //submitComment.click();
        submitComment.submit();  //to samo ale bezpieczniejsze (czasem przycisk jest przykrywany czymś i wtedy click() nie działa



        //WebElement myComment = driver.findElement(By.xpath(" "));
        //     //div[@class="comment-content"]/p[text()="uuid"]  <- ścieżka wyszukania przy pomocy xpatha


        Stream<WebElement> listOfComments = driver.findElements(By.className("comment-content")) //wyszukujemy elementy na stronie www które spełniają warunek po nazwie klasy
                .stream() //robimy stream
                .filter(n -> n.findElement(By.tagName("p")).getText().equals(uuid)); //filtrujemy stream, n jest dowlną literką, zostaje tylko to co spełnia warunek po strzałce

        List<WebElement> filteredComments = listOfComments.collect(Collectors.toList());  //stream zamieniamy na listę

        Assertions.assertEquals(1, filteredComments.size(), "Proper comment is added on page");

    }

    @Test
    public void addCommentToFirstNote() {
        String comment = UUID.randomUUID().toString();  //randomowe dane
        String author = UUID.randomUUID().toString();
        String email = UUID.randomUUID() + "@test.com";

        driver.get("https://automatyzacja.benedykt.net"); //ładowanie strony

        driver.findElement(By.cssSelector("article.post")) //znaleznienie elementu strony
                .findElement(By.className("entry-title"))
                .findElement(By.tagName("a"))
                .click();

        Assertions.assertEquals(1, driver.findElements(By.cssSelector("body.single-post")).size(),
                "Single note page is displayed");  //sprawdzenie że jest tylko jeden

        driver.findElement(By.id("comment")).sendKeys(comment);  //załadowanie randomowych danych

        driver.findElement(By.id("author")).sendKeys(author);

        driver.findElement(By.id("email")).sendKeys(email);

        WebElement submit = driver.findElement(By.id("submit"));  //javaScript pozwala przesunąc zawartośc ekranu aby dostępny był klik na buttonie
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();

        Stream<WebElement> comments = driver.findElements(By.cssSelector(".comment-list > .comment"))
                .stream()
                .filter(c -> c.findElement(By.cssSelector(".comment-author > b")).getText().equals(author))
                .filter(c -> c.findElement(By.cssSelector(".comment-content > p")).getText().equals(comment));  //wyszukanie dodanego elementu strony

        Assertions.assertEquals(1, comments.count(), "Exactly one matching comment is published");  //sprawdzenie czy jest tylko jeden rezultat
    }

    @AfterEach
    public void stopDriver() {
        driver.quit();
    }
}
