package PageObjectsModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindAll(@FindBy(css = "table.cart tr.cart-item-row td:nth-of-type(3) a"))
	List<WebElement> cartProducts;
	

	@FindBy(id="termsofservice")
	WebElement termsofservice;
	
	@FindBy(id="checkout")
	WebElement checkoutBtn;
	
	
	
	public boolean verifyCartProducts(List<String> products)
	{
		int count=0;
		for(String product: products)
		{
			for(WebElement cartProduct: cartProducts)
			{
				if(cartProduct.getText().equalsIgnoreCase(product))
				{
					count++;
					break;
				}
			}
		}
		
		if(count == products.size())
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public CheckoutPage checkoutCartItems()
	{
		termsofservice.click();
		checkoutBtn.click();
		
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
	
}
