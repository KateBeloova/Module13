package Homework.Module13.pages;

import org.openqa.selenium.By;

public class MainPage extends AbstractPage {

    private static final String MAIN_URL = "https://trello.com/";
    private static final By BOARD = By.xpath("//span[@class ='board-tile-details-name']");

    public MainPage() {
    }

    public MainPage open() {
        browser.open(MAIN_URL);
        return this;
    }

    public LoginPage openLink() {
        browser.open(MAIN_URL);
        return new LoginPage();
    }

    public BoardPage boardPage() {
        browser.click(BOARD);
        return new BoardPage();
    }

}
