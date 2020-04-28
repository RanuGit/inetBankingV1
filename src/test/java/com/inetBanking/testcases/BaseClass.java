/**
 * 
 */
package com.inetBanking.testcases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.inetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * @author rathore
 *
 */
public class BaseClass {
	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setUP(String brow) {
	logger = logger.getLogger("LogCreation");
	PropertyConfigurator.configure("log4j.properties");
	
	if(brow.equals("IE"))
	
	{
		//System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"//Drivers//IEDriverServer.exe");
		System.setProperty("webdriver.ie.driver","C://Users//rathore//eclipse-workspace//inetBankingV1//Drivers//IEDriverServer.exe");
		//WebDriverManager.iedriver().version("3.150").setup();
		
		//WebDriverManager.iedriver().setup();
		
		driver = new InternetExplorerDriver();
	}else if(brow.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		//WebDriverManager.chromedriver().arch32().version("80.0.3987.106").setup();
		//ChromeOptions co =new ChromeOptions();
		//co.addArguments("--start-maximized");
		driver = new ChromeDriver();
	}else if(brow.equals("FF")) {
		//WebDriverManager.firefoxdriver().setup();
		System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//Drivers//GeckoDriver.exe");
		driver = new FirefoxDriver();
	}
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(baseURL);
}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreenshot(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des= new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(src, des);
		System.out.println("Screenshot taken :)");
		
	}
	

}
