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

public class Download_SEM_Page extends SplunkWebPage {
 
	public Download_SEM_Page(WebDriver driver) {
		super(driver);
	}

	private static Log logger= SplunkCommonTest.getLog(Login_Page.class);
	private String WindowsCTA="//*[@id='content']/div/div/section/div/div/div[1]/button[3]";
	private String WinDLBit="//*[@id='windows-modal']/div/div/div[2]/div[1]/a";
	
	
	//Verifying Existing userflow.
	
	public void VerifySplunkDownloadFlow() throws Throwable {
		
		WebElement button=driver.findElement(By.xpath(WindowsCTA));
		boolean result= button.isDisplayed();
		logger.info("Element present is..."+result);
		logger.info("WinCTA displayed is....."+ result);
		logger.info("Clicking on Windows bit......");
        button.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement link=driver.findElement(By.xpath(WinDLBit));
        link.click();
        ExistingUsrLogin exusr=new ExistingUsrLogin(driver);
    	exusr.existinguserflow();
    	Thread.sleep(5000);
	    logger.info("Clicking on 'OK' button on the download pop up.");
        Robot rb =new Robot();
        rb.keyPress(KeyEvent.VK_ENTER);
        logger.info("Clicked on Ok button............");
        Thread.sleep(5000);
        
		
	}
	
	
	
}
