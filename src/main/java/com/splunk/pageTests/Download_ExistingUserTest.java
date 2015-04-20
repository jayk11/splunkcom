package com.splunk.pageTests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.testng.annotations.Test;

import com.splunk.CommomPageLibraries.CommonTest;
import com.splunk.CommomPageLibraries.SplunkCommonTest;
import com.splunk.pageCommonLibraries.DownloadFlow;


public class Download_ExistingUserTest extends SplunkCommonTest {
	
	private static Log logger=getLog(Login_Tests.class);
   //String URL="http://www.splunk.com/en_us/download/hunk.html";
   //SplunkCommonTest cmtest=new SplunkCommonTest();
// static String URL="";
	String configFiles="ConFig Files//Splunk_Prod_Sanity_FF_Config.txt";
   
  @Test(dataProvider="HunkDL")
public void validateHunkDownloadFlow(String Xpath_CTA,String Xpath_bit) throws Throwable{
	  System.out.println("In now entering downoad flow");
	DownloadFlow DL=new DownloadFlow(driver);
System.out.println("Reading config files");
readEnv(configFile);
	System.out.println("URL is"+URL);
    driver.get(URL);
	Thread.sleep(5000);
	//Clicking on the download bit
	DL.VerifyDownloadFlow(Xpath_CTA,Xpath_bit);
}

/*@Test(dataProvider="SplunkDL")
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

}*/
}

