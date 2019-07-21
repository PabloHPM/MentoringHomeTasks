package homeWork9;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Log4j2
public class first {

    private static final String FACEBOOK_URL = "https://www.facebook.com/";
    private static final String WIKI_URL = "https://en.wikipedia.org/wiki/Main_Page";
    private static final String GOOGLE_URL = "https://www.google.com";
    private static final String DEMO_QA_URL = "https://demoqa.com";
    private static WebDriver driver;

    @FindBy(css = "#mw-content-text.mw-content-ltr .mw-headline")
    private List<WebElement> headers;

    @FindBy(css = ".ui-sortable >li")
    private List<WebElement> listOfItems;

    @BeforeAll
    static void init() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
//        System.setProperty("webdriver.edge.driver", "drivers\\MicrosoftWebDriver.exe");
//        driver = new EdgeDriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @SneakyThrows
    @Test
    public void facebookLogin() {
        driver.get(FACEBOOK_URL);

        driver.findElement(By.id("email")).sendKeys("ghostpasha16@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("L!feguard#2017");
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#u_0_a"))).click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("._2s25")));

        assertTrue(driver.findElement(By.cssSelector("._2s25")).isDisplayed());
    }

    @Test
    void getTextFromWiki() {
        driver.get(WIKI_URL);
        PageFactory.initElements(driver, this);
        headers.stream()
                .map(WebElement::getText)
                .forEach(log::info);
    }

    @Test
    @SneakyThrows
    void takeScreenshot() {
        driver.get(GOOGLE_URL);
        driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("selenium.org");
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name=\"btnK\"]")).sendKeys(Keys.ENTER);

        takeScreenShot();
    }

    @Test
    void checkOrder() {
        driver.get(DEMO_QA_URL.concat("/sortable"));
        PageFactory.initElements(driver, this);

        List<String> list = listOfItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedList = list
                .stream()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(sortedList, list);
    }

    @Test
    @SneakyThrows
    void changeOrder() {
        driver.get(DEMO_QA_URL.concat("/sortable"));
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement from = driver.findElement(By.cssSelector(".ui-sortable >li:nth-child(5)"));
        WebElement to = driver.findElement(By.cssSelector(".ui-sortable >li:nth-child(2)"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(from, to)
                .build()
                .perform();
        Thread.sleep(10000);

        List<String> list = listOfItems
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> sortedList = list
                .stream()
                .sorted()
                .collect(Collectors.toList());

        assertFalse(sortedList.equals(list));
    }

    @Test
    @SneakyThrows
    void threeRandomScreen() {
        driver.get(DEMO_QA_URL.concat("/selectable"));
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector(".ui-selectable >li:nth-child(" +( (new Random()).nextInt(7)+1) + ")")).click();
            takeScreenShot();
            Thread.sleep(2000);
        }
    }


    private void takeScreenShot() throws IOException {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screen, new File("screenshot\\"+(new Random()).nextInt(100)+".png"));
    }
}
