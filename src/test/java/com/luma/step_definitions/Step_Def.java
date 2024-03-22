package com.luma.step_definitions;

import com.luma.pages.CreateAccountPage;
import com.luma.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;

import java.util.Map;

public class Step_Def {
    WebDriver driver = Hook.driver;
    MainPage mainPage = new MainPage(driver);
    CreateAccountPage accountPage = new CreateAccountPage(driver);
    @Given("user is ain page user clicks on create account button")
    public void user_is_ain_page_user_clicks_on_create_account_button() {
      mainPage.clickCreateAccount();
    }
    @Then("user is on create account page and verifies title")
    public void user_is_on_create_account_page_and_verifies_title(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> map = dataTable.asMap();
        Assert.assertEquals(map.get("title"), BrowserUtils.getTitle(driver));
    }
    @Then("user enters first name and last name")
    public void user_enters_first_name_and_last_name(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> map = dataTable.asMap();
        accountPage.fillNameLastName(map.get("firstname"),map.get("lastname"));
    }
    @When("user enters email and password and confirms the password")
    public void user_enters_email_and_password_and_confirms_the_password(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> map = dataTable.asMap();
        accountPage.emailPasswordConfirm(map.get("email"),map.get("password"),map.get("confirmPassword"));
    }
    @Then("user clicks create account and verifies success message")
    public void user_clicks_create_account_and_verifies_success_message(io.cucumber.datatable.DataTable dataTable) {
        Map<String,String> map = dataTable.asMap();
        accountPage.createAccountBtnValidateMsg(map.get("successMessage"),driver);
    }

}
