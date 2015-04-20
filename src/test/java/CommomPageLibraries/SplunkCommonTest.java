package CommomPageLibraries;
import org.apache.commons.logging.Log;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.splunk.pageTests.Login_Tests;

import CommomPageLibraries.LogUtil;


/***
* @author skumaresan
*/

public class SplunkCommonTest extends CommonTest{
private static Log logger=getLog(Login_Tests.class);
protected static int browserOpen =0;
XSSFWorkbook wb = new XSSFWorkbook();
XSSFSheet sheet = wb.createSheet();
XSSFRow row = sheet.createRow(0);
XSSFCell cell = row.createCell( 0);


@BeforeSuite
public void beforeSuite() {
   System.out.println("in beforeSuite");
}


@Parameters({ "need-new" })
@BeforeClass(alwaysRun = true)
public void beforeSuite(String neednew, ITestContext configParameters) {
if (configFile == null || browserOpen == 0) {
configFile = configParameters.getCurrentXmlTest().getParameter("configFileName");
readConfig(configFile);
browserOpen++;
} else if (neednew.equalsIgnoreCase("yes")) {
configFile = configParameters.getCurrentXmlTest().getParameter("configFileName");
readConfig(configFile);
browserOpen++;

} else {

}

}


@Parameters({ "need-new" })
@AfterClass(alwaysRun = true)
public void shutDownDriver1() {
browserOpen--;
driver.close();
}


@AfterMethod(alwaysRun = true)
public void clearcache(){
	driver.manage().deleteAllCookies();//deletes all cookies
	
}

@AfterSuite
public void afterSuite() {
if (browserOpen > 0){
driver.quit();
}
}

public void dumpConfigInfo() {
props.list(System.out);
}


public static Log getLog(Class<?> string) {
return LogUtil.getLog(string, "log4j.xml");
}

@DataProvider(name = "Login")
public Object[][] LinkUrl() throws Exception{
	logger.info("Entered in to data provider");
    Object[][] testdata=readExcelData("//Users//skumaresan//Documents//workspace//SplunkTesting//Data//TestData.xlsx","Splunk","Splunk Enterprise");
    logger.info("Reading the excel");
    return(testdata);
}

@DataProvider(name = "HunkDL")
public Object[][] Hunk() throws Exception{
	logger.info("Entered in to data provider");
    Object[][] testdata=readExcelData("//Users//skumaresan//Documents//workspace//SplunkTesting//Data//TestData.xlsx","Hunk","Hunk");
    logger.info("Reading the excel");
    return(testdata);
}

@DataProvider(name = "SplunkDL")
public Object[][] SplunkEnterPrise() throws Exception{
	logger.info("Entered in to data provider");
    Object[][] testdata=readExcelData("//Users//skumaresan//Documents//workspace//SplunkTesting//Data//TestData.xlsx","SplunkEnterprise","SplunkEnterprise");
    logger.info("Reading the excel");
    return(testdata);
} 

@DataProvider(name = "UnivDL")
public Object[][] Universal() throws Exception{
	logger.info("Entered in to data provider");
    Object[][] testdata=readExcelData("//Users//skumaresan//Documents//workspace//SplunkTesting//Data//TestData.xlsx","Universal","Universal");
    logger.info("Reading the excel");
    return(testdata);
} 


}