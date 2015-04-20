package com.splunk.pageCommonLibraries;



import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.splunk.CommomPageLibraries.SplunkCommonTest;
import com.splunk.CommomPageLibraries.SplunkWebPage;
/**
 * Hello world!
 *
 */
public class Login_Page extends SplunkWebPage{
	
	
	private String  header_xpath="//div[@class='navbar-header']";
	private String UserName="//*[@id='username']";
	private String Password="//*[@id='password']";
	private String LoginCTA="//*[@id='loginform']/p[2]/input";
	public Login_Page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	
	private static Log logger= SplunkCommonTest.getLog(Login_Page.class);
	
	public void LoginTest() throws InterruptedException{
		driver.get("http://www.splunk.com");
	    
    	Thread.sleep(5000);
    	
    
    	
	}
	

	@SuppressWarnings("unused")
	public boolean isHeaderPresent(String header_xpath){
		
		
		WebElement header=driver.findElement(By.xpath(header_xpath));
		
		
		System.out.println(header.getText());
		
		if (header==null){
			return false;
		}
				logger.info("Header is present in the home page");
				return true;
		
		}
	
	public void LoginValidation(String xpath) throws InterruptedException{
		
		WebElement MyAccount=driver.findElement(By.xpath(xpath));
		
		MyAccount.click();
		
		Actions action = new Actions(driver);
		 
        action.moveToElement(MyAccount).build().perform();
 
        driver.findElement(By.linkText("Login")).click();
		Thread.sleep(5000);
		
		String currenturl=driver.getCurrentUrl();
		logger.info("Current url is....."+currenturl);
		driver.findElement(By.xpath(UserName)).sendKeys("skum");
		driver.findElement(By.xpath(Password)).sendKeys("demo1234");
        driver.findElement(By.xpath(LoginCTA)).click();
	    Thread.sleep(5000);
	    
		
		
	}
	
	
	}
