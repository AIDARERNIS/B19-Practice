package com.luma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='header content']//..//a[.='Create an Account']")
    WebElement createAccount;
    public void clickCreateAccount(){
        createAccount.click();
    }
}
