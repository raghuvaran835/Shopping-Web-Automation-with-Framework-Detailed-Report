package DemoWebShopAutomation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import AbstractComponents.AbstractComponent;
import PageObjectsModel.CartPage;
import PageObjectsModel.CheckoutPage;
import PageObjectsModel.HomePage;
import PageObjectsModel.LoginPage;
import TestComponents.BaseTest;

public class E2ETest extends BaseTest{
	

	@Test(groups = {"e2e"})
	public void purchaseProduct() throws InterruptedException
	{
		System.out.println("Thread ID for E2E is :"+Thread.currentThread().getId());

		
		test=extentReport.createTest("E2E Shopping Test");
		extentTest.set(test);

		extentTest.get().log(Status.INFO, "URL Lanched Successfully 'https://demowebshop.tricentis.com/'",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64()).build());

		
		String username="ratest2@gmail.com";
		String password="Test@1234";
		AbstractComponent ac=new AbstractComponent(driver);
		
		LoginPage loginPage=ac.navigateToLoginPage();
		extentTest.get().log(Status.PASS,"Navigate to Login Page Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"loginPage").build());
		
		HomePage homePage=loginPage.loginApplication(username,password,extentTest);
		
		String logindetails= "Login Successfull :"+username+"\n"+password;
		
		extentTest.get().log(Status.PASS,logindetails,MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"loginSuccess").build());
		
		ac.navigateToContentPageBooks();
		
		extentTest.get().log(Status.PASS,"Navigate to Content Page Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"ContentPage").build());
		
		
		List<String> products=Arrays.asList("Computing and Internet","Fiction");
		extentTest.get().log(Status.INFO,"Adding products to Cart");
		extentTest.get().log(Status.INFO,MarkupHelper.createOrderedList(products));
		
		homePage.addListOfProductsToCart(products);
		extentTest.get().pass("Product added to cart successfully");
		
		CartPage cartPage=ac.navigateToCartPage();
		
		extentTest.get().log(Status.PASS,"Navigate to Cart Page Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"CartPage").build());
		
		Boolean found=cartPage.verifyCartProducts(products);
		
		Assert.assertTrue(found,"Product Not added to Cart Succesfully");
		
		extentTest.get().pass("Verify Product Added to Cart");
		
		CheckoutPage checkoutPage=cartPage.checkoutCartItems();
		extentTest.get().log(Status.PASS,"Navigate to Checkout Page Successful",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"Checkout Page").build());
		
		checkoutPage.provideBillingAndShippingAddress();
		extentTest.get().log(Status.PASS,"Billing and Shipping details",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"Billing and Shipping details ").build());
		
		checkoutPage.providePaymentDetails("cod");
		extentTest.get().log(Status.PASS,"Payment details",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"Payment details ").build());
		
		checkoutPage.confirmOrder();
		
		Thread.sleep(5000);
		
		extentTest.get().log(Status.PASS,"Order Success",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"Order Success").build());

		ac.logoutApplication();
		
		extentTest.get().log(Status.PASS,"logout Success",MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotBase64(),"logout Success").build());
		
	}

}
