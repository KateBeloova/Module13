package Homework.Module13.pages;

import Homework.Module13.reporting.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage extends AbstractPage {

    private WebElement signInBtn;
    private WebDriver driver;
    private static final String LOGIN_INPUT = "user";
    private static final String PWD_INPUT = "//input[@id='password']";
    private static final String SIGNIN_BTN = "login";
    private WebElement loginInput;
    private WebElement pwdInput;

    public LoginPage Login (String login, String password){

        signInBtn = driver.findElement(By.xpath(SIGNIN_BTN));
        signInBtn.click();
        System.out.println("User login: " + login);
        loginInput = driver.findElement(By.id(LOGIN_INPUT));
        loginInput.clear();
        loginInput.sendKeys(login);
        MyLogger.info("Login is entered");

        System.out.println("User password: " + password);
        pwdInput = driver.findElement(By.xpath(PWD_INPUT));
        pwdInput.clear();
        pwdInput.sendKeys(password);
        MyLogger.info("Password is entered");

        signInBtn = driver.findElement(By.id(SIGNIN_BTN));

        signInBtn.click();

        MyLogger.info("Successfully login to trello!");
        return this;
    }

}
