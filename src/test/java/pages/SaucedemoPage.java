package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.BrowserUtils;
import java.util.List;


public class SaucedemoPage extends BasePage {

    public SaucedemoPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "user-name")
    public WebElement txtUsername;

    @FindBy(id = "password")
    public WebElement txtPassword;

    @FindBy(id = "login-button")
    public WebElement btnLogin;

    @FindBy(xpath = "//div[@class='inventory_item_img']//img")
    public List<WebElement> imageList;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement btnBurgerMenu;

    @FindBy(xpath = "//div[@class='footer_copy']")
    public WebElement footer;

    @FindBy(xpath = "//li[@class='social_twitter']")
    public WebElement twitter;

    @FindBy(xpath = "//li[@class='social_facebook']")
    public WebElement facebook;

    @FindBy(xpath = "//li[@class='social_linkedin']")
    public WebElement linkedin;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement btnProductViewOption;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    public WebElement selectViewSortOption;


    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> productItems;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> productPrices;

    public void login(){
        this.sendKeys(txtUsername,"standard_user");
        this.sendKeys(txtPassword,"secret_sauce");
        this.click(btnLogin);
    }

    public boolean verifyIfWebElementIsDisplayed(WebElement element, String logString){
        moveIntoView(element);
        boolean result = element.isDisplayed();
        logInfo(logString + result);
        highlightElementWithScreenshot(element);

        return  result;
    }
    public boolean verifyLinkedMenu(String linkText){
        BrowserUtils.xPathTranslate("a","text()",linkText);
        //ignore case sensitive
        String path =BrowserUtils.xPathTranslate("a","text()",linkText);
        //"//a[text()='" + linkText +"']";
        List<WebElement> linksItems = driver.findElements(By.xpath(path));
        if (linksItems.size() > 0) {
            this.highlightElement(linksItems.get(0));
            logInfo("Link text=" + linkText + ", xpath=" + path);
            return true;
        }
        else {
            logWarning("Can't find: " + path);
        return false;
        }

    }


    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement btnFirstProductItem;
    @FindBy(id = "first-name")
    public WebElement txtFirstName;
    @FindBy(id = "last-name")
    public WebElement txtLastName;
    @FindBy(id = "postal-code")
    public WebElement txtZipCodee;

    //---------------- product elements  ---------------------//
    @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> productNames;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    public List<WebElement>productsDesc;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> productsPrices;

    @FindBy(xpath = "//button[text()='Add to cart']")
    public List<WebElement> btnsAddToCart;


    //---------------- end of product elements ------------------//



    //------------- Cart Page -------------------------//
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    public WebElement btnShoppingCartIcon;
    @FindBy(id = "checkout")
    public  WebElement btnCheckout;

     @FindBy(xpath = "//div[@class='inventory_item_name']")
    public List<WebElement> productNamesInCart;

    @FindBy(xpath = "//div[@class='inventory_item_desc']")
    public List<WebElement>productsDescInCart;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> productsPricesInCart;

    @FindBy(xpath = "//button[text()='Remove']")
    public List<WebElement> btnsRemoveProduct;


/*

    public boolean checkProductNameInCart(String productName){
        String path = "//div[@class='inventory_item_name' and text()='" + productName + "']";
        logInfo(path);
        return driver.findElements(By.xpath(path)).size() > 0;
    }

    public boolean checkProductDescInCart(String productDesc){
        String path = "//div[@class='inventory_item_desc' and text()='" + productDesc + "']";
        logInfo(path);
        return driver.findElements(By.xpath(path)).size() > 0;
    }

    public boolean checkProductPriceInCart(String productPrice){
        String path = "//div[@class='inventory_item_price' and text()='" + productPrice + "']";
        logInfo(path);
        return driver.findElements(By.xpath(path)).size() > 0;
    }
*/


    //------------- End of Cart Page -------------------------//


    //checkout-step-one.html : Check your Info
    @FindBy(id = "continue")
    public WebElement btnContinue;

    //checkout-step-two.html
    @FindBy(id = "finish")
    public WebElement btnFinish;




}
