package com.splunk.CommomPageLibraries;
import org.openqa.selenium.WebDriver;

/***
* @author skumaresan
*/

public class SplunkPage {

protected static WebDriver driver = null;
public SplunkPage(WebDriver driver) {
if (SplunkPage.driver == null) {
     SplunkPage.driver = driver;
}
}
    
public void setPageDriver( WebDriver driver){
    SplunkPage.driver = driver;
}
   
public void sleep(long timeout){
        
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    

}

