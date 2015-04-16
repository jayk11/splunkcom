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

public class DownloadFlow extends SplunkWebPage {
 
	public DownloadFlow(WebDriver driver) {
		super(driver);
	}

	private static Log logger= SplunkCommonTest.getLog(Login_Page.class);
	
	//Verifying Existing userflow.
	
	public void VerifyDownloadFlow(String OSLink,String DLBit) throws Throwable {
		
		WebElement button=driver.findElement(By.xpath(OSLink));
		boolean result= button.isDisplayed();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		logger.info("Element present is..."+result);
		logger.info("OS CTA displayed is....."+ result);
		logger.info("Clicking on the Download Bit......");
        button.click();
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement link=driver.findElement(By.xpath(DLBit));
        link.click();
        ExistingUsrLogin exusr=new ExistingUsrLogin(driver);
    	exusr.existinguserflow();
	    logger.info("Clicking on 'OK' button on the download pop up.");
        Robot rb =new Robot();
        rb.keyPress(KeyEvent.VK_ENTER);
        logger.info("Clicked on Ok button............");
        Thread.sleep(5000);
        
		
	}
	
	
	
}
