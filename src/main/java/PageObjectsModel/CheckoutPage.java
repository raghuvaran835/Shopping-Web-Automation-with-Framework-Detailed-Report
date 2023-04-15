package PageObjectsModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="select#billing-address-select")
	WebElement billing_address_select;
	
	@FindBy(id="BillingNewAddress_FirstName")
	WebElement BillingNewAddress_FirstName;
	
	@FindBy(id="BillingNewAddress_LastName")
	WebElement BillingNewAddress_LastName;
	
	@FindBy(id="BillingNewAddress_Email")
	WebElement BillingNewAddress_Email;
	
	@FindBy(id="BillingNewAddress_CountryId")
	WebElement BillingNewAddress_CountryId;
	
	@FindBy(id="BillingNewAddress_StateProvinceId")
	WebElement BillingNewAddress_StateProvinceId;
	
	@FindBy(id="BillingNewAddress_City")
	WebElement BillingNewAddress_City;
	
	@FindBy(id="BillingNewAddress_Address1")
	WebElement BillingNewAddress_Address1;
	
	@FindBy(id="BillingNewAddress_ZipPostalCode")
	WebElement BillingNewAddress_ZipPostalCode;
	
	@FindBy(id="BillingNewAddress_PhoneNumber")
	WebElement BillingNewAddress_PhoneNumber;
	
	@FindBy(xpath = "//div[contains(@id,'billing-buttons-container')]//input[@type='button' and @value='Continue']")
	WebElement billing_buttons_continue;
	
	@FindBy(id="PickUpInStore")
	WebElement PickUpInStore;
	
	@FindBy(xpath="//div[contains(@id,'shipping-buttons')]//input[@type='button' and @value='Continue']")
	WebElement shipping_buttons_continue;
	
	@FindBy(css="input[value='Payments.CashOnDelivery']")
	WebElement paymentCashOnDelivery;
	
	@FindBy(id="paymentmethod_2")
	WebElement paymentCreditCard;
	
	@FindBy(xpath="//div[contains(@id,'payment-method')]//input[@type='button' and @value='Continue']")
	WebElement payment_buttons_continue;
	
	@FindBy(xpath="//div[contains(@id,'payment-info')]//input[@type='button' and @value='Continue']")
	WebElement payment_info_button_continue;
	
	@FindBy(xpath="//div[contains(@id,'confirm-order')]//input[@type='button' and @value='Confirm']")
	WebElement confirmOrderBtn;
	
	
	public void provideBillingAndShippingAddress()
	{
		Select address_dropdown=new Select(billing_address_select);
		
		if(address_dropdown.getOptions().size() > 1)
		{
			address_dropdown.selectByIndex(0);
			billing_buttons_continue.click();
			PickUpInStore.click();
			shipping_buttons_continue.click();
		}
		else
		{
			address_dropdown.selectByVisibleText("New Address");
			
			Select country_dropdown=new Select(BillingNewAddress_CountryId);
			country_dropdown.selectByVisibleText("India");
			
			Select state_dropdown=new Select(BillingNewAddress_CountryId);
			state_dropdown.selectByIndex(0);
			
			BillingNewAddress_City.sendKeys("vellore");
			BillingNewAddress_Address1.sendKeys("TVM");
			BillingNewAddress_ZipPostalCode.sendKeys("123456");
			BillingNewAddress_PhoneNumber.sendKeys("9999999999");
			
			billing_buttons_continue.click();
			
			PickUpInStore.click();
			
			shipping_buttons_continue.click();
		}
	}
	
	public void providePaymentDetails(String ...args)
	{
		
		if(args[0].equalsIgnoreCase("cod"))
		{
			paymentCashOnDelivery.click();
			payment_buttons_continue.click();
			payment_info_button_continue.click();
		}
		else if(args[0].equalsIgnoreCase("credit card"))
		{
			paymentCreditCard.click();
			payment_buttons_continue.click();
		}
		
	}
	
	public void confirmOrder()
	{
		confirmOrderBtn.click();
	}
	
	

}
