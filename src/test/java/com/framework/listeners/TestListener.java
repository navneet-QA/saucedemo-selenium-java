package com.framework.listeners;

import com.framework.base.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Registered in testng.xml. Logs each test's outcome and grabs a screenshot
 * automatically whenever a test fails.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("STARTED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("PASSED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("FAILED: {}", result.getMethod().getMethodName(), result.getThrowable());
        captureScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("SKIPPED: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite finished: {}", context.getName());
    }

    private void captureScreenshot(String testName) {
        if (DriverManager.getDriver() == null) {
            return;
        }
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            File src = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";
            Files.copy(src.toPath(), Paths.get(destPath));
            logger.info("Screenshot saved: {}", destPath);
        } catch (IOException e) {
            logger.error("Failed to capture screenshot for {}", testName, e);
        }
    }
}
