package com.splunk.pageCommonLibraries;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommomPageLibraries.SplunkCommonTest;
import CommomPageLibraries.SplunkWebPage;

public class ExistingUsrLogin extends SplunkWebPage 
{
	
		 
		protected ExistingUsrLogin(WebDriver driver) {
		super(driver);
	}
		private static Log logger= SplunkCommonTest.getLog(Login_Page.class);
		private static String LoginHere="//*[@id='singleColumn']/p/a";
		private static String UserName="//*[@id='username']";
		private static String Password="//*[@id='password']";
		private static String LoginCTA="//*[@id='loginform']/p[2]/input";
		private static String User="skum";
		private static String Pass="demo1234";
		public void existinguserflow(){
			
			WebElement loginhere=driver.findElement(By.xpath(LoginHere));
		    loginhere.click();
		    
		    driver.findElement(By.xpath(UserName)).sendKeys(User);
			driver.findElement(By.xpath(Password)).sendKeys(Pass);
		    driver.findElement(By.xpath(LoginCTA)).click();

		    
		}

}
