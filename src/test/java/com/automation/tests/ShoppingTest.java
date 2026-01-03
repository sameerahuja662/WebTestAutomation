package com.automation.tests;
import com.automation.pages.LoginPage;
import com.automation.pages.ShoppingPage;
import com.automation.utils.TestListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingTest extends BaseTest {


    @Test
    public void ShoppingTest () {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user","secret_sauce");

        // TAKE AND ATTACH SS
        TestListener.getTest().addScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                "Proof: Username entered");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

        ShoppingPage shoppingPage= new ShoppingPage(driver);
        shoppingPage.clickOnBackpackProduct();

        // TAKE AND ATTACH SS
        TestListener.getTest().addScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                "Proof: Product inventory page");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item.html"));
        //add to cart
        shoppingPage.clickAddToCart();
        // TAKE AND ATTACH SS
        TestListener.getTest().addScreenCaptureFromBase64String(((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64),
                "Proof: Clicked add to cart");
        //verify remove button
        Assert.assertEquals(shoppingPage.getRemoveButtonText(),"Remove");

    }


}
