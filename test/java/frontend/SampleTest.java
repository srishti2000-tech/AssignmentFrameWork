package frontend;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import utils.ExtentManager;
import utils.WebDriverUtils;

public class SampleTest {
    private WebDriver driver;
    private ExtentTest test;

    @BeforeClass
    public void setup() {
        // Setup code (e.g., WebDriver initialization)
        driver = WebDriverUtils.getDriver();
    }

    @BeforeMethod
    public void startTest(Method method) {
        test = ExtentManager.createTest(method.getName(), method.getAnnotation(Test.class).description());
    }

    @Test()
    public void verifysamplecase() {
        // Example test code
        

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed");
            test.log(Status.FAIL, result.getThrowable());
            // Capture screenshot
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "screenshots/" + result.getMethod().getMethodName() + ".png";
            try {
                FileHandler.copy(src, new File(screenshotPath));
                test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
            } catch (IOException e) {
                test.log(Status.FAIL, "Failed to capture screenshot: " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
        ExtentManager.flushReports();
    }
}
