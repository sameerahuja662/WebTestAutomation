package com.automation.tests;


import com.automation.pages.LoginPage;
import com.automation.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTest extends BaseTest {


    @Test
    public void ShoppingTest () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("problem_user","secret_sauce");

        // TAKE AND ATTACH SS
        String path = captureAndAttachScreenshot("login step");
        TestListener.getTest().addScreenCaptureFromPath(path, "Proof: Username entered");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));



    }


}
