package PageObjectsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent{

	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindAll(@FindBy(css = "div.item-box"))
	List<WebElement> products;
	
	public void addProductToCartPage(String productName)
	{
//		List<WebElement> productEle=products.stream().filter(p->p.findElement(By.xpath("//div[@class='product-title']")).getText().contains(productName)).limit(1).collect(Collectors.toList());
		
		for(WebElement product: products)
		{
			if(product.findElement(By.cssSelector("h2.product-title a")).getText().contains(productName))
			{
				product.findElement(By.cssSelector("input[value='Add to cart']")).click();
			}
		}
		
//		System.out.println(productEle.size());
//		productEle.get(0).findElement(By.cssSelector("input[value='Add to cart']")).click();
	}
	
	public void addListOfProductsToCart(List<String> productNames) throws InterruptedException
	{

		System.out.println(products.size());
		for(String productName : productNames)
		{
			driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(text(),"+"'"+productName+"'"+")]/parent::h2//following-sibling::div[@class='add-info']//input[@value='Add to cart']")).click();
			Thread.sleep(2000);
		}
		
				
	}
	
}
