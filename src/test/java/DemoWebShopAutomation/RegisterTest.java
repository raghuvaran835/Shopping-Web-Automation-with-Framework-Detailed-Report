package DemoWebShopAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import CustomUtilities.ExcelDataDriven;
import PageObjectsModel.RegisterPage;
import TestComponents.BaseTest;

public class RegisterTest extends BaseTest {

	@Test(dataProvider = "registerData", groups = { "register" })
	public void RegisterUser(String testRecord, String charType, String fname, String lname, String gender,
			String email, String pword, String cpword) throws IOException 
	{
		System.out.println("Thread ID for Register is :" + Thread.currentThread().getId());

		test = extentReport.createTest("Register Test with " + testRecord + " with " + charType + ":");
		extentTest.set(test);

		RegisterPage rp = new RegisterPage(driver);

		rp.registerUser(fname, lname, gender, email, pword, cpword,extentTest);

		extentTest.get().log(Status.PASS,"Registration Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());

		Assert.assertTrue(rp.registerSuccess.isDisplayed(), "Register User Failed");

	}

	@DataProvider(name = "registerData")
	public Object[][] getRegisterUserData() throws IOException {
		
		//Single User Registration
//		return ExcelDataDriven.getExcelDataAsTwodimensionalArray("RegisterData", "user1");
		
		//Multiple User Registration
		return ExcelDataDriven.getExcelDataAsTwodimensionalArray("RegisterData");
	}
}
