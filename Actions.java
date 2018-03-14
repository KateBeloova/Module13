package Homework.Module13;

import Homework.Module13.pages.BoardPage;
import Homework.Module13.pages.LoginPage;
import Homework.Module13.pages.MainPage;
import org.testng.annotations.Test;

public class Actions {

    private static final String LOGIN = "kateatmbelova@gmail.com";
    private static final String PASSWORD = "Testatm2017";


    @Test(description = "Login test")
    public void Login() {
        LoginPage loginPage = new MainPage().open().openLink();
        loginPage.Login(LOGIN, PASSWORD);
    }

    @Test(description = "Open board test")
    public void OpenBoard() {
        BoardPage boardPage = new MainPage().boardPage();
        boardPage.openBoard();
    }

    @Test(description = "Drag-n-drop test")
    public void dragNDropDemo() {
        BoardPage boardPage = new MainPage().boardPage();
        boardPage.dragNDrop();
    }

}
