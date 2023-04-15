package TestComponents;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import CustomUtilities.ReportsUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest{
	
	public WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest;

	public WebDriver initializeDriver() throws IOException
	{
		File f=new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GlobalData.properties");
		FileInputStream fis=new FileInputStream(f);
		
		Properties prop=new Properties();
		prop.load(fis);
		
		String browserName=System.getProperty("browser") == null ? prop.getProperty("browser"):System.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.setAcceptInsecureCerts(true);
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver(options);
		}
		else if(browserName.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			
		}
		else if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}
		else
		{
			WebDriverManager.safaridriver().setup();
			driver=new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	public String getScreenshot(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		
		String path=System.getProperty("user.dir")+"\\Screenshots\\ss_"+testcaseName+".png";
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File(path));
		return path;
	}
	
	public String takeScreenshotBase64()
	{
		TakesScreenshot ts= (TakesScreenshot) driver;
		String screenshotFile=ts.getScreenshotAs(OutputType.BASE64);
		return screenshotFile;
		
	}
	
	@BeforeMethod
	public void launchApplication() throws IOException
	{
		driver=initializeDriver();
		driver.get("https://demowebshop.tricentis.com/");
	}
	
	@AfterMethod
	public void closeApplication() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.quit();
//		extentTest.get().log(Status.INFO,"Browser Closed");
//		extentReport.flush();
	}
	
	@BeforeSuite
	public void setupReport()
	{
		extentTest=new ThreadLocal<ExtentTest>();
		System.out.println("before Suit executed");
		extentReport =ReportsUtility.getExtentReporterObject();
	}
	
	@AfterSuite
	public void saveReport() throws IOException
	{
		extentReport.flush();
		Desktop.getDesktop().browse(new File(ReportsUtility.reportPath).toURI());
		
	}
	

}
