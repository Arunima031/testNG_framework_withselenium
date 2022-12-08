package org.example.testComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.example.resources.ExtentReporterNG;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Ilisteners extends BaseTest implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReport();
    ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test=extent.createTest(iTestResult.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentTest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentTest.get().log(Status.FAIL, "Test Failed,check screenshot");
        extentTest.get().fail(iTestResult.getThrowable());

        try {
            driver=(WebDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath = null;
        try {
            filePath = getScreenshot(iTestResult.getMethod().getMethodName(),driver);

        } catch (IOException e) {
            e.printStackTrace();
        }
        extentTest.get().addScreenCaptureFromPath(filePath, iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
