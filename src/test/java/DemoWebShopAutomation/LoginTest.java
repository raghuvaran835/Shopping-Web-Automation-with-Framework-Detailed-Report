package DemoWebShopAutomation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import AbstractComponents.AbstractComponent;
import CustomUtilities.ExcelDataDriven;
import PageObjectsModel.LoginPage;
import TestComponents.BaseTest;

public class LoginTest extends BaseTest {

	@Test(dataProvider = "userCredentials", groups = { "login" })
	public void loginUser(String user, String dataCharacter, String uname, String password) throws IOException {
		
		System.out.println("Thread ID for Login is :"+Thread.currentThread().getId());
		
		test=extentReport.createTest("Login Test "+dataCharacter+" user :"+" "+uname+" , "+password);
		extentTest.set(test);
		
		AbstractComponent ac = new AbstractComponent(driver);
		LoginPage loginPage = ac.navigateToLoginPage();

		loginPage.loginApplication(uname, password,extentTest);
		
		String logindetails= "Login Successfull :"+"\nUsername:"+uname+"\nPassword:"+password;
		
		extentTest.get().log(Status.PASS,logindetails,MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"loginSuccess").build());
		
		Assert.assertTrue(loginPage.logout.isDisplayed());

	}

	@DataProvider(name = "userCredentials")
	public Object[][] getUserCredentials() throws IOException {

		return ExcelDataDriven.getExcelDataAsTwodimensionalArray("LoginData");
	}

}
