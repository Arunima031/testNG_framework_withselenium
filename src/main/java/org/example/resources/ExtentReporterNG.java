package org.example.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReport(){
        String path = System.getProperty("user.dir")+ "\\reports\\index.html";
        ExtentSparkReporter reporter =new ExtentSparkReporter(path);
        reporter.config().setReportName("Automation Test Reports");
        reporter.config().setDocumentTitle("Test Report");

        ExtentReports extent =new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Author","Arunima");
        return extent;
    }
}
