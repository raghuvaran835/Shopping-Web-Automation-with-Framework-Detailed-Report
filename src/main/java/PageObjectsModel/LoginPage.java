package PageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import AbstractComponents.AbstractComponent;
import dev.failsafe.internal.util.Assert;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Email")
	WebElement email_inputBox;
	
	@FindBy(id="Password")
	WebElement password_inputBox;
	
	@FindBy(id="RememberMe")
	WebElement RememberMe_chkbox;
	
	@FindBy(css="[href*='passwordrecovery']")
	WebElement forgetPassword;
	
	@FindBy(css=".login-button")
	WebElement loginBtn;
	
	public HomePage loginApplication(String uname,String password, ThreadLocal<ExtentTest> extentTest)
	{
		waitForElementToBeVisible(email_inputBox);
		
		email_inputBox.sendKeys(uname);
		drawBorder(email_inputBox);
		extentTest.get().log(Status.INFO, "Enter Email ID:'"+uname+"'",MediaEntityBuilder.createScreenCaptureFromBase64String(takeWebElementScreenshotBase64(email_inputBox)).build());
		
		password_inputBox.sendKeys(password);
		drawBorder(password_inputBox);
		extentTest.get().log(Status.INFO, "Enter Password:'"+password+"'",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());

		
		RememberMe_chkbox.click();
		
		drawBorder(loginBtn);
		extentTest.get().log(Status.INFO, "Click on Login Button",MediaEntityBuilder.createScreenCaptureFromBase64String(takeWebElementScreenshotBase64(loginBtn)).build());
		loginBtn.click();
		
		waitForElementToBeVisible(logout);
		
		HomePage hp=new HomePage(driver);
		return hp;
		
	}
	
	
	

}
