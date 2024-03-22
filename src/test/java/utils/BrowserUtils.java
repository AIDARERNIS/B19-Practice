package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BrowserUtils {
    public static String getText(WebElement element, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.textToBePresentInElement(element, ""));
        return element.getText().trim();
    }

    public static String getText(WebElement element, WebDriver driver,String text){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        return element.getText().trim();
    }
    public static String getText(WebElement element){
        return element.getText().trim();
    }

    public static String getTitle(WebDriver driver){
        return driver.getTitle().trim();
    }
    public static void selectBy(WebElement element,String value,String methodName){
        Select select = new Select(element);
        switch (methodName){
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println("Please choose correct select method");
        }

    }

    public static List<WebElement> getOptionsSelect(WebElement element){
        Select select = new Select(element);
        return select.getOptions();


    }

    public  static WebElement findElement (WebDriver driver, By by) {
        return driver.findElement(by);
    }
    public static void dismissAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
    public static void acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    public static void sendKeysToAlert(WebDriver driver,String keysToSend){
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(keysToSend);
    }
    public static String alertGetText(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        return alert.getText().trim();

    }
    public static void clickHoldAndDrop(WebDriver driver,WebElement location,WebElement dropZone){
        Actions actions = new Actions(driver);
        actions.clickAndHold(location).moveToElement(dropZone).release().build().perform();
    }
    public static void dragAndDrop(WebDriver driver,WebElement draggable,WebElement dropZone){
        Actions actions =new Actions(driver);
        actions.dragAndDrop(draggable, dropZone).perform();
    }
    public static void scrollByAmount(WebDriver driver,int x,int y){
        Actions actions =new Actions(driver);
        actions.scrollByAmount(x,y).build().perform();
    }
    public static void scrollToElement(WebDriver driver,WebElement toElement){
        Actions actions =new Actions(driver);
        actions.scrollToElement(toElement).build().perform();

    }
    public static void moveByOffSetWithClick(WebDriver driver,WebElement element,int x,int y){
        Actions actions = new Actions(driver);
        actions.clickAndHold().moveByOffset(x,y).build().perform();

    }
    public static void moveTo(WebDriver driver,WebElement target){
        Actions actions= new Actions(driver);
        actions.moveToElement(target).build().perform();
    }
    public static void takeScreenshot(WebDriver driver){
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String location  = System.getProperty("user.dir") + "/src/main/screenshots/";

        File directory = new File(location);
        if(!directory.exists()){
            directory.mkdir();
        }
        try {
            FileUtils.copyFile(file,new File(location + System.currentTimeMillis() + ".png"));
        }catch (IOException e){
            throw new RuntimeException(e);
        }


    }
    //JS methods
    public static String getTitleJS(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = js.executeScript("return document.title").toString();
        return title;
    }
    public static void clickWithJs(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }

    public static void scrollIntoViewJS(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)",element);


    }
    public static void scrollWithPoints(WebDriver driver,WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Point point = element.getLocation();
        int x = point.getX();
        int y = point.getY();

        js.executeScript("window.scrollTo(" + x + "," + y + ")");


    }

    public static void switchWindows(WebDriver driver){
        String mainPageID = driver.getWindowHandle();
       Set<String> windowHandle = driver.getWindowHandles();
       for(String id : windowHandle){
           if(!id.equals(mainPageID)){
               driver.switchTo().window(id);
               System.out.println("Driver switch to new window");
               break;
           }
       }


    }
    public static void switchWindowsWithTitle(WebDriver driver,String title){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String id:windowHandles){
            driver.switchTo().window(id);
            if(driver.getTitle().contains(title)){
                break;
            }
        }
    }
    public static void switchWindowsWithURL(WebDriver driver,String url){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String id:windowHandles){
            driver.switchTo().window(id);
            if(driver.getCurrentUrl().contains(url)){
                break;
            }
        }

    }

    public static void switchDriverToIFrame(WebDriver driver, String nameOrID){
        driver.switchTo().frame(nameOrID);
        System.out.println("driver switched to iframe "+ nameOrID);
    }
    public static void switchDriverToIFrame(WebDriver driver,WebElement element){
        driver.switchTo().frame(element);
        System.out.println("driver switched to iframe using WebElement ");
    }
    public static void switchDriverToDefaultContent(WebDriver driver){
        //used this method when you dealing with iframes
        driver.switchTo().defaultContent();

    }
    public static void switchDriverToParentFrame(WebDriver driver){
        //used this method when you dealing with iframes
        driver.switchTo().parentFrame();


    }
    public static WebElement getFirstOptionSelect(WebElement element){
        Select select = new Select(element);
        return select.getFirstSelectedOption();
    }

    public static void sendKeys(WebElement element,String keys,WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(keys);
    }
    public static void takeScreenshotCucumber(Scenario scenario, WebDriver driver) {

        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(":", "-");
        if (scenario.isFailed()) {
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenShotFile, new File("src/test/java/screenshots/" + screenshotFileName + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }



}
