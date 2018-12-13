package selenium.WordPressPackage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WpNotePage {
    private final WebDriver notDriver;

    public WpNotePage(WebDriver driver) { //konstruktor
        notDriver = driver; //zasysam driver do tej klasy
    }

    public WpNotePage addComment(String comment, String author, String email) {
        // tu dodajemy notatkę
        notDriver.findElement(By.id("comment")).sendKeys(comment); //wypełniamy pola formularza
        notDriver.findElement(By.id("author")).sendKeys(author);
        notDriver.findElement(By.id("email")).sendKeys(email);
        notDriver.findElement(By.id("submit")).submit(); //klikamy enter

        return new WpNotePage(notDriver);
    }

    public boolean commentExists(String comment, String author) {
        // sprawdzić czy comment is published
        Stream<WebElement> listOfComments = notDriver.findElements(By.cssSelector(".comment-list > .comment")) //wyszukujemy elementy na stronie www które spełniają warunek po nazwie klasy
                .stream() //robimy stream
                .filter(n -> n.findElement(By.cssSelector(".comment-author > b")).getText().equals(author)) //filtrujemy stream, zostaje tylko to co spełnia warunek po strzałce
                .filter(n -> n.findElement(By.tagName("p")).getText().equals(comment)); //filtrujemy stream, zostaje tylko to co spełnia warunek po strzałce

        List<WebElement> filteredComments = listOfComments.collect(Collectors.toList());  //stream zamieniamy na listę

        return filteredComments.size() > 0;
    }
}
