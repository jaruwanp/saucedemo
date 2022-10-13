package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    protected ExtentReports extentReports;
    protected ExtentTest extentTest;

  //  @BeforeSuite
    public void createReport(){
        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(ConfigReader.readProperty("report_url"));
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setReportName(ConfigReader.readProperty("report_name"));
        extentSparkReporter.config().setDocumentTitle( ConfigReader.readProperty("report_title") + " - " +  DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy").format(LocalDate.now()));
        extentReports.attachReporter(extentSparkReporter);

    }
   // @AfterSuite
    public void closeReport(){
        extentReports.flush();
    }


    public void createTestReport(Method method){

        extentTest = extentReports.createTest(getTestName(method));
    }

    public void logScreenshot(WebDriver driver){
        if (ConfigReader.readProperty("takeScreenshot").equalsIgnoreCase("true"))
            extentTest.info(MediaEntityBuilder.createScreenCaptureFromBase64String(
                    Screenshot.takeScreenshot(driver)
            ).build());
    }
    public String getTestName(Method method){
        Test testClass = method.getAnnotation(Test.class);
        String testName;

        if(testClass.testName() != null && !testClass.testName().equals("")) {
            testName = testClass.testName();
        }else{
            testName = method.getName();
        }
        return testName;
    }
    public void logInfo(String msg){
        extentTest.info(msg);
    }

    public void logWarning(String msg){
        extentTest.warning(msg);
    }
    public void logInfo(String msg, WebElement element){
        String str = element.toString();
        String locator = str.substring(str.indexOf("->"), str.length()-1);

        logInfo(msg + " " + locator);
    }

    public void logResult(ITestResult result){
        if(result.getStatus() == ITestResult.FAILURE){
            extentTest.fail("TEST FAILED");
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("TEST PASSED");
           // extentTest.pass(result.toString());
            //extentTest.pass(result.);
        }
    }

    //making test name dynamic



}
