package Homework.Module13.pages;

import Homework.Module13.browser.Browser;


public class AbstractPage {

    protected Browser browser;

    protected AbstractPage() {
        this.browser = Browser.getInstance();
    }


}
