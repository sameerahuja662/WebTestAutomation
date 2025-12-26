package com.automation.tests;

import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            // Selenium Manager downloads/uses the correct driver version
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions); // Selenium Manager downloads/uses the correct
            driver = new FirefoxDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Integer.parseInt(ConfigReader.getProperty("timeout"))));
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
