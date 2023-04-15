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

public class RegisterPage extends AbstractComponent{

	WebDriver driver;
	public RegisterPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	@FindBy(id="gender-male")
	WebElement genderMale;
	
	@FindBy(id="gender-female")
	WebElement genderFemale;
	
	@FindBy(id="FirstName")
	WebElement firstName;
	
	@FindBy(id="LastName")
	WebElement lastName;
	
	@FindBy(id="Email")
	WebElement email;
	
	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(id="ConfirmPassword")
	WebElement confirmPassword;
	
	@FindBy(id="register-button")
	WebElement registerBtn;
	
	@FindBy(xpath = "//div[@class='result' and contains(text(),'registration completed')]")
	public WebElement registerSuccess;
	
	public void registerUser(String fName,String lName,String gender,String emailId,String pword,String cPasword, ThreadLocal<ExtentTest> extentTest)
	{
		extentTest.get().log(Status.INFO,"Click on Register Button",MediaEntityBuilder.createScreenCaptureFromBase64String(takeWebElementScreenshotBase64(register)).build());

		navigateToRegisterPage();
		waitForElementToBeVisible(registerBtn);
		
		extentTest.get().log(Status.INFO,"Navigate to Register Page Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());

		
		if(gender.equalsIgnoreCase("Male"))
		{
			genderMale.click();
		}
		else if(gender.equalsIgnoreCase("Female")){
			genderFemale.click();
		}
		
		extentTest.get().log(Status.INFO, "Enter Required Details");
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		email.sendKeys(emailId);
		password.sendKeys(pword);
		confirmPassword.sendKeys(cPasword);
		
		extentTest.get().log(Status.INFO,"Details Enterted Successfully",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());
		
		registerBtn.click();
		
		extentTest.get().log(Status.INFO, "Click on Register Button");
		
		waitForElementToBeVisible(registerSuccess);
		
		
	}
	
	
	

}
