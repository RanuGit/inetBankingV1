package com.inetBanking.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_Login_001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered the user name");
		lp.setPassword(password);
		logger.info("Entered Password");
		lp.clickSubmit();
		System.out.println(System.getProperty("user.dir"));
		Thread.sleep(3000);
		logger.info("Login button clicked");

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test case passed");
		} 
		else 
		{
			captureScreenshot(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
	}

}
