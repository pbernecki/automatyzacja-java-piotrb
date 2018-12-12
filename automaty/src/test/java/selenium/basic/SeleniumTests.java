package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumTests {

    private WebDriver driver;

    @BeforeEach
    public void startDriver(){
        //ustawiamy gdzie leży driver dla Chroma
        System.setProperty("webdriver.chrome.driver", "c:\\selenium\\chromedriver_win32\\chromedriver.exe");
        //Start przeglądarki chrom
        driver = new ChromeDriver();
        driver.manage().window().maximize();  //zarządzanie oknem przeglądarki - otwarcie na cały ekran
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //postój na 5 sekund zanim wywali że nie znalazł elementu (interwał sprawdzenia jest co 500 msekund) dotyczy każdej operacji selenium

    }

    @Test
    public void canFindCodeSprintersOnGoogle(){
        //System.setProperty("webdriver.chrome.driver", "c:\\selenium\\chromedriver_win32\\chromedriver.exe");
        //WebDriver driver = new ChromeDriver();
        //te dwie linie można wyciągnąc do BeforEach

        driver.get("https://www.google.com");                     //otwórz stronę googla
        WebElement searchbox = driver.findElement(By.name("q"));  //znajdż element pole do wpisywania
        searchbox.sendKeys("codesprinters");       //wpisz w pole wyszukiwania
        searchbox.submit();                                       //klikam "enter"


        //znajdź listę rezultatów googla- 10 szt
        Stream<WebElement> searchResults = driver.findElements(By.cssSelector("div.rc")).stream();
        //WebElement result = searchResults.filter(r ->r.findElement(By.cssSelector("div.r > a")).getAttribute("href").equals("http://agileszkolenia.pl/")).findFirst().get();
        //odfiltruj jeden element
        Stream <WebElement> resultFiltered = searchResults.filter(r ->r.findElement(By.cssSelector("div.r > a")).getAttribute("href").equals("http://agileszkolenia.pl/"));
        //zmień stream na listę
        List<WebElement> resultsList = resultFiltered.collect(Collectors.toList());

        //czy na liście jest jeden element?
        Assertions.assertEquals(1, resultsList.size());
        //sprawdzamy czy jest spełniony warunek że rezultat to agileszkolenia.pl?
        Assertions.assertEquals("agileszkolenia.pl/", resultsList.get(0).findElement(By.tagName("cite")).getText());

        //driver.quit();  można wyciągnąc do AfterEach
        }

    @Test
    public void verifyAuthorOfBlogNoteAboutTransformations(){
        driver.get("http://markowicz.pro/");  //wejście na stronę - uwaga na literówki w adresie

        //List<WebElement> listOfNotes = driver.findElements(By.className("entry-title")); //załadowanie listy notek, wyszukanie po nazwie klasy
        //Stream<WebElement> streamOfNotes = listOfNotes.stream();
        //listOfNotes.stream(); zamiana z listy na streama

        Stream<WebElement> listOfNotes = driver.findElements(By.className("entry-title")) //wyszukujemy elementy na stronie www które spełniają warunek po nazwie klasy
                .stream() //robimy stream
                .filter(n -> n.findElement(By.tagName("a")).getText().equals("O transformacjach")); //filtrujemy stream, n jest dowlną literką, zostaje tylko to co spełnia warunek po strzałce

        List<WebElement> filteredNotes = listOfNotes.collect(Collectors.toList());  //stream zamieniamy na listę

        Assertions.assertEquals(1, filteredNotes.size(), "Only one matching note is found on main page");
        //sprawdzenie czy lista zawiera jeden element, w komentarzu to co chcemy sprawdzić w asercji - pojawi się tylko jak się wywali test na tej asercji

        driver.findElement(By.cssSelector("#eu-cookie-law input")).submit();  //klikam przycisk na wyskakującym okienku RODO (sprawdzać na trybie incognito - wtedy wyskoczy)

        filteredNotes.get(0).click();   //klikamy w element

        WebElement author = driver.findElement(By.cssSelector(".author > a"));  //wyciągamy autora ze strony

        Assertions.assertEquals("Rafał", author.getText(), "Proper author name is displayed"); //sprawdzamy czy autor to Rafał
        Assertions.assertEquals("http://markowicz.pro/author/rafal-markowicz/",
                author.getAttribute("href"),
                "Valid author URL is displayed");
    }

    @Test
    public void verifyAuthorOfBlogNoteAboutTransformationsStepByStep() { //to samo ale inaczej napisane
        driver.get("http://markowicz.pro/");
        List<WebElement> listOfNotes = driver.findElements(By.className("entry-title"));
        Stream<WebElement> streamOfNotes = listOfNotes.stream();
        Stream<WebElement> filteredStream = streamOfNotes
                .filter(n -> n.findElement(By.tagName("a")).getText().equals("O transformacjach"));
        List<WebElement> filteredNotes = filteredStream.collect(Collectors.toList());
        Assertions.assertEquals(1, filteredNotes.size(), "Only one matching note is found on main page");
    }

    @AfterEach
    public void closeDriver(){ //close chromeDriver
        driver.quit();
    }
}
