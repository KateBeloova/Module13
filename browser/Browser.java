package Homework.Module13.browser;

import Homework.Module13.reporting.MyLogger;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {

    private WebDriver driver;
    private static Browser instance = null;
    private static final int PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS = 15;
    private static final int COMMAND_DEFAULT_TIMEOUT_SECONDS = 10;
    private static final int WAIT_ELEMENT_TIMEOUT = 10;
    private static final String SCREENSHOTS_NAME_TPL = "screenshots/scr";

    private Browser(WebDriver driver) {
        this.driver = driver;
    }

    public static Browser getInstance() {
        if (instance != null) {
            return instance;
        }
        return instance = init();
    }

    private static Browser init() {
        System.setProperty("webdriver.chrome.driver", "D:\\ATM\\Homework\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(COMMAND_DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return new Browser(driver);
    }

    public void open(String url) {
        MyLogger.info("Going to URL: " + url);
        driver.get(url);
    }

    private void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_ELEMENT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    private void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='5px solid green'", driver.findElement(locator));
    }

    public void dragAndDrop(final By locator, final By targetLocator) {
        waitForElementVisible(locator);
        waitForElementVisible(targetLocator);
        WebElement element = driver.findElement(locator);
        WebElement target = driver.findElement(targetLocator);
        takeScreenshot();
        MyLogger.info("Dragging element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")" +
                "to '" + driver.findElement(targetLocator).getText() + "' (Located: " + targetLocator + ")");
        (new Actions(driver)).dragAndDrop(element, target).perform();
        takeScreenshot();
    }

    private void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String screenshotName = SCREENSHOTS_NAME_TPL + System.nanoTime();
            String scrPath = screenshotName + ".jpg";
            File copy = new File(scrPath);
            org.apache.commons.io.FileUtils.copyFile(screenshot, copy);
            captureScreenshot(this.driver);
        } catch (IOException e) {
            MyLogger.error("Failed to make screenshot");
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] captureScreenshot(WebDriver driver) {
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

    public void click(final By locator) {
        waitForElementVisible(locator);
        MyLogger.info("Clicking element '" + driver.findElement(locator).getText() + "' (Located: " + locator + ")");
        highlightElement(locator);
        takeScreenshot();
        unHighlightElement(locator);
        driver.findElement(locator).click();
    }

    private void unHighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", driver.findElement(locator));
    }


}
