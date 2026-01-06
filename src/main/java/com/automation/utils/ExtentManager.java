package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (ExtentManager.class) {
                if (extent == null) {
                    extent = createInstance();
                }
            }
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        // Define report path
        String path = System.getProperty("user.dir") + "/test-output/reports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);

        // Configure Report Look & Feel
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Regression Results");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().thumbnailForBase64(true);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add System Info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }
}
