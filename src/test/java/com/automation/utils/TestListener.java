package com.automation.utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.tests.BaseTest;

public class TestListener implements ITestListener {
    // Make this thread-safe if you plan to run parallel tests later
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        System.out.println("Starting test: " +  result.getMethod().getMethodName());
        extentTest.set(test);
    }

    // Helper method to get the current test instance in any class
    public static ExtentTest getTest() {
        return extentTest.get();
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // 1. Get the test instance safely
        ExtentTest test = getTest();

        // 2. If it's null, the error happened in @BeforeMethod. Create the test entry now.
        if (test == null) {
            test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
        }

        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();

        if (driver != null) {
            // Use the method that returns the path
            String screenshotPath = ((BaseTest) testClass).getScreenshot(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush(); // This generates the actual HTML file
    }
}
