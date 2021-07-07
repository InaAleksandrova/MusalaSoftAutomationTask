package reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    static ExtentReports extent;

    public static ExtentReports getReportObject() {

        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Test results");
        reporter.config().setDocumentTitle("Musala Soft Automation task");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Ina Aleksandrova");
        extent.setSystemInfo("Browser", "");
        extent.setSystemInfo("OS", "Windows 10");
        return extent;
    }
}
