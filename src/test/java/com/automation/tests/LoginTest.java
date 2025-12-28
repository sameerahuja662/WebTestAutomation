package com.automation.tests;

import com.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

//        loginPage.enterUsername("standard_user");
//        loginPage.enterPassword("secret_sauce");
        loginPage.login("standard_user","secret_sauce");
        System.out.println("Hi");

        // Example Assertion: Verify URL changes after login
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }
}
