package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By backpackProductLink = By.id("item_4_title_link");
    private By addToCartButton = By.xpath("//button[text()='Add to cart']");
    private By removeButton = By.xpath("//button[text()='Remove']");
    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        // Set up a 10-second explicit wait
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    public void clickOnBackpackProduct() {
        driver.findElement(backpackProductLink).click();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButton).click();
    }

    public String getRemoveButtonText() {
         return driver.findElement(removeButton).getText();
    }
}
