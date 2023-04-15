package AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectsModel.CartPage;
import PageObjectsModel.LoginPage;
import PageObjectsModel.RegisterPage;

public class AbstractComponent {
	
	WebDriver driver;
	JavascriptExecutor js;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		this.js=(JavascriptExecutor) driver;

	}
	
	@FindBy(linkText = "Log in")
	WebElement login;
	
	@FindBy(css="a[href*='cart']")
	WebElement shoppingCart;
	
	@FindBy(linkText = "Register")
	protected WebElement register;
	
	@FindBy(linkText = "Wishlist")
	WebElement wishlist;
	
	@FindBy(linkText = "Log out")
	public WebElement logout;
	
	@FindBy(css="ul.top-menu a[href*='/books']")
	WebElement topmenu_books;
	
	@FindBy(css="ul.top-menu a[href*='computers']")
	WebElement topmenu_computers;
	
	@FindBy(css="ul.top-menu a[href*='electronics']")
	WebElement topmenu_electronics;
	
	@FindBy(css="ul.top-menu a[href*='apparel-shoes']")
	WebElement topmenu_apparelshoes;
	
	
	public LoginPage navigateToLoginPage()
	{
		login.click();
		LoginPage lp=new LoginPage(driver);
		return lp;
		
	}
	
	public RegisterPage navigateToRegisterPage()
	{
		register.click();
		RegisterPage rp=new RegisterPage(driver);
		return rp;
	}
	
	public CartPage navigateToCartPage()
	{
		shoppingCart.click();
		CartPage cp=new CartPage(driver);
		return cp;
	}
	public void logoutApplication()
	{
		logout.click();
	}
	
	public void navigateToWishListPage()
	{
		wishlist.click();
	}
	
	public void navigateToContentPageBooks()
	{
		topmenu_books.click();
	}
	
	public void waitForElementToBeVisible(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeInVisible(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementToBeVisible(By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementToBeInVisible(By locator)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public String takeWebElementScreenshotBase64(WebElement element)
	{
		String elementScreenshotFile=element.getScreenshotAs(OutputType.BASE64);
		return elementScreenshotFile;
	}
	
	public String takeScreenshotBase64()
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		String screenshotFile=ts.getScreenshotAs(OutputType.BASE64);
		return screenshotFile;
		
	}
	
	public void drawBorder(WebElement element)
	{
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void clickElement(WebElement ele)
	{
		js.executeAsyncScript("arguments[0].click();", ele);
	}
	
	public void refreshPageByJS()
	{
		js.executeAsyncScript("history.go(0)");
	}
	
	public void scrollPageDown()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void scrollPageUp()
	{
		js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}
	
	
	
	
	
}
