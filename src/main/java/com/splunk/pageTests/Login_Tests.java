package com.splunk.pageTests;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.apache.commons.logging.Log;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.splunk.CommomPageLibraries.SplunkCommonTest;
import com.splunk.pageCommonLibraries.Login_Page;


//import CommomPageLibraries.SplunkCommonTest;


/**
 * Hello world!
 *
 */
public class Login_Tests extends SplunkCommonTest{
	private static Log logger=getLog(Login_Tests.class);
	//private static Log logger= getLog.Login_Tests.class);

public static void LoginPageTest(String URL,String Xpath) throws InterruptedException{
		
		Login_Page lp=new Login_Page(driver);
		driver.get(URL);
	    
    	Thread.sleep(5000);
    	logger.info("Verifying the header in the home page........");
    	Assert.assertTrue(lp.isHeaderPresent("//div[@class='navbar-header']"),"Header Element not found");
    	
    	
    	//System.out.println("Header element is present");
		
	}
	
	@Test(dataProvider = "Login")
	public static void LoginVerification(String URL,String Xpath) throws InterruptedException{
		
		Login_Page lp=new Login_Page(driver);
		driver.get(URL);
		
		Thread.sleep(5000);
		
        lp.LoginValidation(Xpath);

		
	}
	
	
	}
	
    
