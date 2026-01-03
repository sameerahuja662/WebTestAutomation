package com.automation.tests;

import com.automation.pages.LoginPage;
import com.automation.pages.ShoppingPage;
import com.automation.utils.TestListener;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");
        System.out.println("Hi");
        // Example Assertion: Verify URL changes after login

        // TAKE AND ATTACH
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        TestListener.getTest().info("Proof: Username entered",
                MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }
}
