package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.Screenshot;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForElementClickability(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void moveIntoView(WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 2; i++) {
            try {
                if (i % 2 == 0) {
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black;" +
                            "border: 3px solid red; background: yellow");
                    //BaseTest.extentManager.logScreenshot(driver);
                } else {
                    sleep(600);
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void highlightElementWithScreenshot(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 0; i < 2; i++) {
            try {
                if (i % 2 == 0) {
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black;" +
                            "border: 3px solid red");
                    BaseTest.extentManager.logScreenshot(driver);
                } else {
                    sleep(600);
                    js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void sendKeys(WebElement element, String inputText){
        BaseTest.extentManager.logInfo("Entered the text ", element);
        waitForElementVisibility(element);
        moveIntoView(element);
        highlightElement(element);
        element.sendKeys(inputText);
    }

    public void moveToViewAndHighlightAndScreenshot(WebElement element){
        waitForElementVisibility(element);
        moveIntoView(element);
        highlightElementWithScreenshot(element);
    }
    public String getText(WebElement element){
        BaseTest.extentManager.logInfo("Retrieved the text ", element);
        waitForElementVisibility(element);
        moveIntoView(element);
        highlightElement(element);
        return element.getText();
    }

    public void click(WebElement element){
        BaseTest.extentManager.logInfo("clicked the button ", element);
        waitForElementClickability(element);
        moveIntoView(element);
        highlightElement(element);
        element.click();
    }

    public void assertEquals(String actual, String expected){
        BaseTest.extentManager.logInfo("Expected: " + expected);
        BaseTest.extentManager.logInfo("Actual: " + actual);
        Assert.assertEquals(actual, expected);
    }

    public void assertNotEquals(String actual, String expected){
        BaseTest.extentManager.logInfo("Expected: " + expected);
        BaseTest.extentManager.logInfo("Actual: " + actual);
        Assert.assertNotEquals(actual, expected);
    }

    public void takeScreenshot(){
        BaseTest.extentManager.logScreenshot(driver);
    }

    public void logInfo(String info){
        BaseTest.extentManager.logInfo(info);
    }

    public void logWarning(String info){
        BaseTest.extentManager.logWarning(info);
    }
}
