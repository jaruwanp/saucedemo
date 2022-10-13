package tests;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SaucedemoPage;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class SaucedemoTest extends BaseTest {
    SaucedemoPage page;
    public String password = "secret_sauce";

    @BeforeMethod
    public void setUp(){
        page = new SaucedemoPage(driver);
    }

    @Test(testName = "US 301 - Verify login",description = "US 301 - Verify standard_user can login with right password")
    public void test01(){

        String currentURL = driver.getCurrentUrl();
        page.login();
        //The url must be changed if logged in successfully
        page.assertNotEquals(currentURL,driver.getCurrentUrl());
        page.takeScreenshot();
        page.sleep(1000);
    }

    @Test(testName = "US 302 - When locked out user tries to login",description = "When locked out user tries to login with right password I would like to see an error message \"Epic sadface: Sorry, this user has been locked out.")
    public void test02(){
        page.sendKeys(page.txtUsername,"locked_out_user");
        page.sendKeys(page.txtPassword,password);
        page.click(page.btnLogin);
        String expectedText = "Epic sadface: Sorry, this user has been locked out.";
        String err = driver.findElement(By.xpath("//h3")).getText();
        page.assertEquals(expectedText,err);
        page.takeScreenshot();
        page.sleep(1000);


    }

    @Test(testName = "US 303 - When problem_user logs in", description = "all items on homepage should display same images")
    public void test03(){
        page.sendKeys(page.txtUsername,"problem_user");
        page.sendKeys(page.txtPassword,password);
        page.click(page.btnLogin);
        List<WebElement> imgList = page.imageList;

        //Retrieve the first image source which would be the same
        String expectedImg = imgList.get(0).getAttribute("src");
        for(WebElement element: imgList){
           page.assertEquals(expectedImg,element.getAttribute("src"));
        }
        page.takeScreenshot();
        page.sleep(1000);
    }


    @Test(testName = "US 304 - test display options", description = "I need an option to see navigation menu. When user clicks the button it should display following buttons:")
    public void test04(){
        page.login();
        page.click(page.btnBurgerMenu);
        String[] menuList= new String[] {"All Items","About","Logout","Reset App State"};
        for(String item:menuList){
            Assert.assertTrue(page.verifyLinkedMenu(item));
        }
        page.sleep(1000);

    }

    @Test(testName = "US 305 - Verify footer",description = "Footer of the page should be “© 2022 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy”")
    public void test05(){
        String expectedText = "© 2022 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
        WebElement element = page.footer;
        page.login();
        page.moveToViewAndHighlightAndScreenshot(element);
        page.assertEquals(expectedText,element.getText());
        page.sleep(1000);
    }
   /* US 306 - Filter options. When user clicks the filter it should have following options:
    Name (A to Z), Name (Z to A), Price (low to high), Price (high to low)*/
    @Test(testName = "US 306 - Filter options",
            description = "When user clicks the filter it should have following options: " +
                    "Name (A to Z), Name (Z to A), Price (low to high), Price (high to low)")
    public void test06(){
        page.login();
        page.click(page.btnProductViewOption);

        Select select = new Select(page.selectViewSortOption);

        //---------------------------------------------------------------------
        page.logInfo("<h2>Verify product sorted by Name (A to Z)</h2>");
        select.selectByVisibleText("Name (A to Z)");
        page.sleep(3000);
        page.takeScreenshot();
        List<WebElement> productItems = page.productItems;
        List<String> productItemSortedByName = new ArrayList<String>();

        //Add data to array
        productItems.forEach(e-> productItemSortedByName.add(e.getText()));
        Collections.sort(productItemSortedByName);//Ascending order
        System.out.println(productItemSortedByName);

        int i =0;
        for(WebElement each: page.productItems)
        {
            page.assertEquals(each.getText(),productItemSortedByName.get(i));
            i++;
        }

        //---------------------------------------------------------------------
        page.logInfo("<h3>Verify product sorted by Name (Z to A)</h3>");
        page.click(page.btnProductViewOption);
        select.selectByVisibleText("Name (Z to A)");
        page.sleep(1000);
        page.takeScreenshot();
        Collections.sort(productItemSortedByName,Collections.reverseOrder());//Reverse Array to Desc order
       // productItemSorted.forEach(e-> System.out.println(e));
        System.out.println(productItemSortedByName);
        i=0;
        for(WebElement each:page.productItems)
        {
            page.assertEquals(each.getText(),productItemSortedByName.get(i));
            i++;
        }
        //---------------------------------------------------------------------
        page.logInfo("<h3>Verify product sorted by Price (low to high)</h3>");
        page.click(page.btnProductViewOption);
        select.selectByVisibleText("Price (low to high)");
        page.sleep(1000);
        page.takeScreenshot();
        //Get the price list
        List<WebElement> prices = page.productPrices;

        //Create a nw arrayList for products sorted by price  ASC
        List<Double> productPrices = new ArrayList<Double>();
        //Add product item to array in order to sort
        prices.forEach(e-> productPrices.add(Double.parseDouble(e.getText().replace("$","").replace(" ",""))));

        Collections.sort(productPrices);//Sort product by Price ASC
        System.out.println(productPrices);
        //Verify if they are match
        i=0;

        for(WebElement each:page.productPrices)
        {
            page.assertEquals(each.getText(),"$" + productPrices.get(i).toString());
            i++;
        }

        //Price (high to low)
//---------------------------------------------------------------------
        page.logInfo("<h3>Verify product sorted by Price (high to low)</h3>");
        page.click(page.btnProductViewOption);
        select.selectByVisibleText("Price (high to low)");
        page.sleep(1000);
        page.takeScreenshot();
        //Add product item to array in order to sort
        //prices.forEach(e-> productPrices.add(Double.parseDouble(e.getText().replace("$","").replace(" ",""))));

        Collections.sort(productPrices,Collections.reverseOrder());//Sort product by Price ASC
        System.out.println(productPrices);
        //Verify if they are match
        i=0;
        for(WebElement each:page.productPrices)
        {
            page.assertEquals(each.getText(),"$" + productPrices.get(i).toString());
            i++;
        }
    }

    @Test(testName = "US 307 - Social media buttons",description = "Verify there are 3 social media buttons are present: twitter, facebook and linkedIn")
    public void test07(){
         page.login();
         Assert.assertTrue(page.verifyIfWebElementIsDisplayed(page.twitter,"Verify twitter button displayed :"));
         Assert.assertTrue(page.verifyIfWebElementIsDisplayed(page.facebook,"Verify facebook button displayed :"));
         Assert.assertTrue(page.verifyIfWebElementIsDisplayed(page.linkedin,"Verify linkedin button displayed :"));
         page.sleep(1000);
    }

}
