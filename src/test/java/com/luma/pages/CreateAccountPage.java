package com.luma.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

public class CreateAccountPage {
    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "#firstname")
    WebElement firstname;
    @FindBy(css = "#lastname")
    WebElement lastname;
    @FindBy(css = "#email_address")
    WebElement email;
    @FindBy(css = "#password-confirmation")
    WebElement confirmPassword;
    @FindBy(name = "password")
    WebElement password;
    @FindBy(xpath = "//button[@title='Create an Account']")
    WebElement createAccountBtn;
    @FindBy(xpath = "//div[.='Thank you for registering with Main Website Store.']")
    WebElement successMes;
    //just a comment
    //for practice
    public void fillNameLastName(String firstname,String lastname){
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
    }
    public void emailPasswordConfirm(String email,String password,String confirmPassword){
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.confirmPassword.sendKeys(confirmPassword);
    }
    public void createAccountBtnValidateMsg(String successMsg,WebDriver driver){
        createAccountBtn.click();
        Assert.assertEquals(successMsg, BrowserUtils.getText(this.successMes,driver));
    }
}
