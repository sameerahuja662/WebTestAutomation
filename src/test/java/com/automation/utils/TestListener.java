package com.automation.utils;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.tests.BaseTest;

public class TestListener implements ITestListener {
    private static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        // Get the driver instance from the Test Class
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver(); // Requires a getDriver() method in BaseTest

        if (driver != null) {
            String screenshotPath = ((BaseTest) testClass).getScreenshot(result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush(); // This generates the actual HTML file
    }
}
