package com.splunk.pageTests;

import org.apache.commons.logging.Log;
import org.testng.annotations.Test;

import com.splunk.pageCommonLibraries.DownloadFlow;
import CommomPageLibraries.SplunkCommonTest;

public class Download_ExistingUserTest extends SplunkCommonTest {
	
	private static Log logger=getLog(Login_Tests.class);
    //public String URL="http://www.splunk.com/en_us/download/hunk.html";
    
	
@Test(dataProvider="HunkDL")
public void validateHunkDownloadFlow(String URL,String Xpath_CTA,String Xpath_bit) throws Throwable{
	DownloadFlow DL=new DownloadFlow(driver);
    driver.get(URL);
	Thread.sleep(5000);
	//Clicking on the download bit
	DL.VerifyDownloadFlow(Xpath_CTA,Xpath_bit);
	
}

@Test(dataProvider="SplunkDL")
public void validateSplunkDownloadFlow(String URL,String Xpath_CTA,String Xpath_bit) throws Throwable{
	DownloadFlow DL=new DownloadFlow(driver);
    driver.get(URL);
	Thread.sleep(5000);
	//Clicking on the download bit
	DL.VerifyDownloadFlow(Xpath_CTA,Xpath_bit);
}

@Test(dataProvider="UnivDL")
public void validateUnivDownloadFlow(String URL,String Xpath_CTA,String Xpath_bit) throws Throwable{
	DownloadFlow DL=new DownloadFlow(driver);
    driver.get(URL);
	Thread.sleep(5000);
	//Clicking on the download bit
	DL.VerifyDownloadFlow(Xpath_CTA,Xpath_bit);

}

}

