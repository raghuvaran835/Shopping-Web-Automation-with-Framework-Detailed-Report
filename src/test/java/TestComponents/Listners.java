package TestComponents;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import CustomUtilities.ReportsUtility;

public class Listners extends BaseTest implements ITestListener{
	
//	public ExtentReports extentReport =ReportsUtility.getExtentReporterObject();
//	public ExtentTest test;
//	public ThreadLocal<ExtentTest> extentTest;
	static int count=1;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
		
//		test=extentReport.createTest(result.getMethod().getMethodName());
//		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		try {
			extentTest=(ThreadLocal<ExtentTest>) result.getTestClass().getRealClass().getField("extentTest").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String filePath = null;
		
		try {
			extentTest=(ThreadLocal<ExtentTest>) result.getTestClass().getRealClass().getField("extentTest").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String screenshotName=result.getMethod().getMethodName()+""+count++;
		try {
			filePath=getScreenshot(screenshotName, driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentTest.get().fail(result.getThrowable());
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
//		extentReport.flush();
//		try {
//			Desktop.getDesktop().browse(new File(ReportsUtility.reportPath).toURI());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
