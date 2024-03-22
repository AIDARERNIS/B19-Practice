package com.luma.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;
import utils.ConfigReader;
import utils.DriverHelper;

public class Hook {
   public static WebDriver driver;
   @Before
   public void startUp(){
       driver= DriverHelper.getDriver();
       driver.get(ConfigReader.readProperties("lumaURL"));
   }
   @After
    public void tearDown(Scenario scenario) throws InterruptedException {
       BrowserUtils.takeScreenshotCucumber(scenario,driver);
       driver.quit();
   }

}
