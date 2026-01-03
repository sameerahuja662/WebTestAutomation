package com.automation.tests;

import com.automation.utils.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Listeners(com.automation.utils.TestListener.class)
public class BaseTest {
    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        String browser = ConfigReader.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);// Selenium Manager downloads/uses the correct driver version

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions); // Selenium Manager downloads/uses the correct
        }

        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                    Integer.parseInt(ConfigReader.getProperty("timeout"))));
            driver.get(ConfigReader.getProperty("url"));
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String getScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // 1. Define the filename
        String fileName = testName + "_" + timestamp + ".png";

        // 2. Full path for saving the file physically on the drive
        String destinationPath = System.getProperty("user.dir") + "/test-output/screenshots/" + fileName;

        try {
            FileUtils.copyFile(source, new File(destinationPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3. RETURN RELATIVE PATH for the Extent Report
        // Since ExtentReport.html is in /reports/, the image is in screenshots/
        return "screenshots/" + fileName;
    }
}
