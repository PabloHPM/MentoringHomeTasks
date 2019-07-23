package homeWork9;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Log4j2
public class SeleniumTasks {

    private static final String FACEBOOK_URL = "https://www.facebook.com/";

    private static final String WIKI_URL = "https://en.wikipedia.org/wiki/Main_Page";

    private static final String GOOGLE_URL = "https://www.google.com";

    private static final String DEMO_QA_URL = "https://demoqa.com";

    private static WebDriver driver;

    private static Actions action;


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
        action = new Actions(driver);
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
        headers.stream().map(WebElement::getText).forEach(log::info);
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

        List<String> list = listOfItems.stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());

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
        actions.dragAndDrop(from, to).build().perform();
        Thread.sleep(10000);

        List<String> list = listOfItems.stream().map(WebElement::getText).collect(Collectors.toList());

        List<String> sortedList = list.stream().sorted().collect(Collectors.toList());

        assertNotEquals(sortedList, list);
    }

    @Test
    @SneakyThrows
    void threeRandomScreen() {
        driver.get(DEMO_QA_URL.concat("/selectable"));
        PageFactory.initElements(driver, this);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector(".ui-selectable >li:nth-child(" + ((new Random()).nextInt(7) + 1) + ")"))
                  .click();
            takeScreenShot();
            Thread.sleep(2000);
        }
    }

    @Test
    @SneakyThrows
    void getSizeOfRectangle() {
        driver.get(DEMO_QA_URL.concat("/resizable"));

        WebElement element = driver.findElement(By.id("resizable"));
        assertTrue(element.isDisplayed());
        Dimension dimension;

        for (int i = 0; i < 3; i++) {
            dimension = element.getSize();
            log.info(String.format("Default size Height : %d Width : %d", dimension.height, dimension.getWidth()));
            resize(driver.findElement(By.cssSelector(".ui-resizable-handle.ui-resizable-se")),
                   new Random().nextInt(400), new Random().nextInt(400));
            Thread.sleep(2000);
        }
    }

    @Test
    @SneakyThrows
    void elementsWorkAsDefined() {
        driver.get(DEMO_QA_URL.concat("/tooltip-and-double-click"));

        WebElement doubleClickBtn = driver.findElement(By.id("doubleClickBtn"));
        WebElement rightClickBtn = driver.findElement(By.id("rightClickBtn"));
        WebElement tooltipDemo = driver.findElement(By.id("tooltipDemo"));

        action.doubleClick(doubleClickBtn).perform();
        driver.switchTo().alert().accept();
        assertTrue(doubleClickBtn.isEnabled());
        Thread.sleep(2000);

        action.contextClick(rightClickBtn).perform();
        assertTrue(driver.findElement(By.id("rightclickItem")).isDisplayed());
        Thread.sleep(2000);

        action.moveToElement(tooltipDemo).perform();
        assertTrue(driver.findElement(By.cssSelector("#tooltipDemo > span")).isDisplayed());
        Thread.sleep(2000);
    }

    @Test
    void checkTooltip() {
        driver.get(DEMO_QA_URL.concat("/tooltip"));

        WebElement tooltip = driver.findElement(By.id("age"));
        tooltip.sendKeys("Hi");
        WebElement tooltipTest = driver.findElement(By.cssSelector(".ui-tooltip.ui-corner-all"));
        assertTrue(tooltipTest.isDisplayed());
    }

    @Test
    @SneakyThrows
    void sliderActions() {
        driver.get(DEMO_QA_URL.concat("/slider"));

        WebElement slider = driver.findElement(By.cssSelector(".ui-slider"));
        assertTrue(slider.isDisplayed());

        log.info("Sliders’ current position :" + slider.findElement(By.cssSelector("span")).getLocation().toString());

        for (int i = 0; i < 3; i++) {
            action.dragAndDropBy(slider, new Random().nextInt(500) - 200, 0).build().perform();
            log.info(
                "Sliders’ current position :" + slider.findElement(By.cssSelector("span")).getLocation().toString());
            Thread.sleep(1000);
        }
    }

    @Test
    void datePickerCheck() {
        driver.get(DEMO_QA_URL.concat("/datepicker"));
        WebElement datepicker = driver.findElement(By.cssSelector(".hasDatepicker"));
        datepicker.sendKeys(randomDate());
        WebElement datepicker2 = driver.findElement(By.cssSelector(".ui-state-active"));
        assertTrue(datepicker2.isDisplayed());
    }

    @Test
    void checkboxCheck() {
        driver.get(DEMO_QA_URL.concat("/checkboxradio"));

        List<WebElement> radioList =
            driver.findElements(By.cssSelector(".widget:nth-child(5) > fieldset:nth-child(3) > label"));

        for (int i = 0; i < radioList.size(); i++) {
            radioList.get(i).click();
            List<Boolean> status =
                driver.findElements(By.cssSelector(".widget:nth-child(5) > fieldset:nth-child(3) > label")).stream()
                      .map(WebElement::isSelected).collect(Collectors.toList());
            status.contains(true);
        }

        WebElement radioBtn = driver.findElement(By.cssSelector(".ui-state-active"));
        log.info(radioBtn.getText());
    }

    private String randomDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = LocalDate.of(2019, 8, 1);
        LocalDate endDate = LocalDate.of(2019, 8, 31);
        long randomEpochDay =
            ThreadLocalRandom.current().longs(startDate.toEpochDay(), endDate.toEpochDay()).findAny().getAsLong();
        return LocalDate.ofEpochDay(randomEpochDay).format(formatter);
    }

    @SneakyThrows
    private void takeScreenShot() {
        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String name = String.format("screenshot\\%s.png", LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss")));
        FileUtils.copyFile(screen, new File(name));
    }

    private void resize(WebElement elementToResize, int xOffset, int yOffset) {
        action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
    }
}
