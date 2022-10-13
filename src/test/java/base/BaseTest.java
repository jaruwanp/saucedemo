package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;
import utils.ExtentManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentManager extentManager;

    @BeforeSuite
    public void startReport(){
        extentManager = new ExtentManager();
        extentManager.createReport();
    }

    @AfterSuite
    public void closeReport(){
        extentManager.closeReport();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver(Method method){
        initializeDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(ConfigReader.readProperty("url"));
        extentManager.createTestReport(method);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        extentManager.logResult(result);
        driver.quit();
    }

    public void initializeDriver(String browser){
        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", ConfigReader.readProperty("chromeDriver"));
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", ConfigReader.readProperty("firefoxDriver"));
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Invalid browser name");
        }
    }

}
