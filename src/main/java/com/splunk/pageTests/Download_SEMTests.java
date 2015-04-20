package com.splunk.pageTests;

import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


import CommomPageLibraries.SplunkCommonTest;
import CommomPageLibraries.SplunkWebPage;

public class Download_SEMTests extends SplunkCommonTest {
	
	private static Log logger=getLog(Login_Tests.class);
    public String URL="http://www.splunk.com/en_us/download/splunk-enterprise.html";
    
	
@Test 
public void validateSplunkDownloadFlow() throws Throwable{
	//Download_Splunk_EnterprisePage Dl_Sp=new Download_Splunk_EnterprisePage(driver);
	driver.get(URL);
	Thread.sleep(5000);
	//Clicking on the download bit
	//Dl_Sp.VerifySplunkDownloadFlow();
	
	
	
}


}

