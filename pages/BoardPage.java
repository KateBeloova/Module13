package Homework.Module13.pages;


import Homework.Module13.reporting.MyLogger;
import Homework.Module7.pages.TrelloBoard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class BoardPage extends AbstractPage{

    private static final String BOARD = "//span[@class ='board-tile-details-name']";
    private WebElement boardLink;

    private static final By DRAGGABLE = By.xpath("//span[@class ='list-card-title js-card-name'][contains(text(),'First')]");
    private static final By DROPPABLE = By.xpath("(//a[@class ='open-card-composer js-open-card-composer'])[2]");

    private WebDriver driver;

    public BoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public BoardPage() {

    }

    public TrelloBoard openBoard() {

        boardLink = driver.findElement(By.xpath(BOARD));
        boardLink.click();
        MyLogger.info("Board is opened..");
        return new TrelloBoard(driver);
    }


    public BoardPage dragNDrop() {
        browser.dragAndDrop(DRAGGABLE, DROPPABLE);
        MyLogger.info("Successfully dragged item");
        return this;
    }

}
