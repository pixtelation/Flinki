package com.flinki;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.flinki.Base.base;

public class TestListener implements ITestListener {
    
     @Override
    public void onTestStart(ITestResult result) {
        System.out.println(" Test Started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println(" Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(" Test Failed: " + result.getName());

        // Screenshot on failure
        WebDriver driver = base.getDriver(); // Assumes you have static getDriver() in BaseTest
        if (driver != null) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "screenshots/" + result.getName() + "_" + timeStamp + ".png";

            try {
                FileUtils.copyFile(srcFile, new File(screenshotName));
                System.out.println(" Screenshot saved: " + screenshotName);
            } catch (IOException e) {
                System.out.println("⚠️ Failed to save screenshot: " + e.getMessage());
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println(" Test Skipped: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println(" Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println(" Test Suite Finished: " + context.getName());
    }

}
