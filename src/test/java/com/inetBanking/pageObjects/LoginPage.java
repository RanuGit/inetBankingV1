/**
 * 
 */
package com.inetBanking.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author rathore
 *
 */
public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		// TODO Auto-generated constructor stub
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	//Page object factory
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;
	
	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(linkText="Log out")
	@CacheLookup
	WebElement lnkLogout;
	
	// @FindBy(tagName = "a") List<WebElement> links;
	 //@FindBy(how = How.TAG_NAME, using = "a") List<WebElement> links;
	
	//@FindBy(how=How.LINK_TEXT,using="test")
	//WebElement lnkzxLogout;
	
	//Implementation through Page object Model
	By user99GuruUserName = By.name("uid");
	
	public void setUserName(String userName)
	{
		txtUserName.sendKeys(userName);
		
		//ldriver.findElement(user99GuruUserName).sendKeys("test");
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void clickSubmit()
	{
		btnLogin.click();
	}
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
}
