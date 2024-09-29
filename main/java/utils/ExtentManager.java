package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ExtentTest test;

    public static ExtentReports getInstance() {
        if (extent == null) {
            sparkReporter = new ExtentSparkReporter("extent-report.html");
            sparkReporter.config().setReportName("Extent Reports - Selenium Automation");
            sparkReporter.config().setDocumentTitle("Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
        return test;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
