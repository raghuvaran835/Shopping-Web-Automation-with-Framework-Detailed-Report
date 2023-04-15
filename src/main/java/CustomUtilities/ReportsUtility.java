package CustomUtilities;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportsUtility {

	public static String reportPath;
	public static ExtentReports getExtentReporterObject()
	{
		reportPath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter setupReport=new ExtentSparkReporter(new File(reportPath));
		
		setupReport.config().setDocumentTitle("Test Results");
		setupReport.config().setReportName("Demo Web Shop Automation");
		
		ExtentReports extentReport=new ExtentReports();
		extentReport.attachReporter(setupReport);
		
		extentReport.setSystemInfo("Tester", "Raghuvaran");
		extentReport.setSystemInfo("Environment", "Production");
		
		return extentReport;
	}
}
