package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtils {

    public static void switchToNewWindow(WebDriver driver){
        for(String each: driver.getWindowHandles()){
            if (!each.equals(driver.getWindowHandle()))
                driver.switchTo().window(each);
        }
    }

    public static String xPathTranslate(String HtmlTag,String HtmlAttb, String value){
        //td[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = 'jhon@hotmail.com']
        //a[text()='Logout']
        //xPathTranslate("a","text()","john@gmail.com")
        return  "//" +HtmlTag + "[translate(" + HtmlAttb + ",'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz') = '" + value.toLowerCase()+ "']";

    }
    public static void moveIntoView(WebElement element, WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        sleep(2000L);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(2000L);

    }
    public static void sleep(Long milliSeconds){
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
