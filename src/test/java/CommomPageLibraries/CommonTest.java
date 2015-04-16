package CommomPageLibraries;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Augmenter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/***
* @author skumaresan
*/
public class CommonTest {

	protected static Properties props;
	protected static WebDriver driver = null;
	protected static String BaseURL = null;
	protected static String configFile = null;
	protected static int browserOpen = 0;
	protected static String Env=null;
    protected static String URL="";
	protected static String Browser = null;
	protected static int Max_Wait_Time_seconds = 100000;
	protected static String Remote = null;
	protected static String Hub = "10.140.15.35";
	protected static String Port = "4444";
	protected static String hubLocation = "http://" + Hub + ":" + Port+ "/wd/hub";

	protected static String FF_Profile_Path = null;
	protected static String NetworkTraffic = null;

	public String XMLResultURL = null;
	public static int MaxWaitSeconds = 100000;
	public static final String SCREENSHOTS_FOLDER_NAME = "screenshots";
	public static final String TEST_RESULTS_OUTPUT_FOLDER = "target";
	public static final String SCREENSHOT_IMG_FORMAT = ".png";
	public static String networkTrafficLocation = null;
	public static String networkTrafficFolder = null;
	public static DesiredCapabilities cap = null;

	public static void setVariables(Properties props) {
		BaseURL = props.getProperty("BASE_URL");
		Browser = props.getProperty("BROWSER");
		MaxWaitSeconds = Integer.parseInt(props
				.getProperty("MAX_WAIT_TIME_SECONDS"));
		Remote = props.getProperty("REMOTE");
	}

	private static FirefoxProfile setupFirefoxCapabilities()
			throws MalformedURLException {
		FirefoxProfile profile = null;
		if (FF_Profile_Path != null) {
			profile = new FirefoxProfile(new File(FF_Profile_Path));
		} else if (NetworkTraffic != null) {
			profile = new FirefoxProfile();

		} else {
			profile = new FirefoxProfile();

		}

		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");
		System.out.println("Value of Remote is " + Remote);
		System.out.println("Entered in to YES loop");
		System.out.println("Firefox is the driver opened in hub location"
				+ hubLocation);
		// }
		cap.setCapability(FirefoxDriver.PROFILE, profile);

		return profile;

	}

	private static void setupChromeCapabilities() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		// cap = DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		System.out.println("Im inside chrome setup capability");

	}
	
	private static void setupIECapabilities(){
		String vers = "";
		cap = DesiredCapabilities.internetExplorer();
		cap.setBrowserName("iexplorer");
		cap.setVersion(vers);
	
	}

	/*private static void setupIECapabilities() throws MalformedURLException {
		String vers = "";
		cap = DesiredCapabilities.internetExplorer();
		try {
			vers = Browser.replace("IE", "");
			if (Remote.equalsIgnoreCase("Yes")) {
				driver = new RemoteWebDriver(new URL(hubLocation), cap);
				driver.get("http://www.google.com");
			} else {
				cap.setBrowserName("iexplorer");
				cap.setVersion(vers);
			}
		} catch (NumberFormatException e) {
			System.out
					.println("IE version not specified.  Not setting version in driver capabilities.");
		}
	}*/

	private static void setupSafariCapabilities() throws MalformedURLException {
		cap = DesiredCapabilities.safari();
		if (Remote.equalsIgnoreCase("Yes")) {
			driver = new RemoteWebDriver(new URL(hubLocation), cap);
		} else {
			cap.setBrowserName("safari");
		}
	}

	public static void readConfig(String configFile) {
		try {
			props = new Properties();
			File myFile = new File(configFile);
			System.out.println(myFile.getAbsolutePath()
					+ " config file name:  " + configFile);
			FileReader configfile = new FileReader(myFile.getAbsolutePath());
			System.out.println("Reading the file");
			props.load(configfile);
			System.out.println("Loading the config file");
			setVariables(props);
			System.out.println("File loaded ");
			
			

			if (Browser.equalsIgnoreCase("FF")) {
				System.out.println("Browser is FF");
				setupFirefoxCapabilities();
				System.out.println(Browser + "Is the browser");

			} else if (Browser.equalsIgnoreCase("Chrome")) {
				System.out.println("Browser is Chrome");
				setupChromeCapabilities();

			} else if (Browser.contains("IE")) {
				setupIECapabilities();
			} else if (Browser.equalsIgnoreCase("Safari")) {
				setupSafariCapabilities();
			} else {
				System.out.println("Browser is not a desired browser");
			}

			if (Browser.equalsIgnoreCase("FF")
					&& (Remote.equalsIgnoreCase("Yes"))) {

				System.out.println("Instatating new Ff driver for the test");
				driver = new RemoteWebDriver(new URL(hubLocation),DesiredCapabilities.firefox());
				driver.manage().window().maximize();
				System.out.println("Instatating new Ff driver for the test");
				driver.manage().window().maximize();
				System.out.println("New driver is open");
			}

			else if ((Browser.equalsIgnoreCase("FF") && (Remote
					.equalsIgnoreCase("No")))) {
				System.out.println("Entering remote is no");
				driver = new FirefoxDriver(setupFirefoxCapabilities());
				
				
			} else if (Browser.equalsIgnoreCase("Chrome")&&
				 (Remote.equalsIgnoreCase("Yes"))) {
				System.out.println("Entering remote is no");
				// driver = new ChromeDriver();
				driver = new RemoteWebDriver(new URL(hubLocation),DesiredCapabilities.chrome()); 
				driver.manage().window().maximize();
				
			}else if(Browser.equalsIgnoreCase("Chrome")&&
					 (Remote.equalsIgnoreCase("No"))){
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver\\chromedriver.exe");				
				driver = new ChromeDriver(DesiredCapabilities.chrome());
				driver.manage().window().maximize();

		}
			
			else if (Browser.contains("IE") &&(Remote.equalsIgnoreCase("Yes"))) {
				driver = new RemoteWebDriver(new URL(hubLocation),DesiredCapabilities.internetExplorer()); 
				//driver = new InternetExplorerDriver(cap);
				driver.manage().window().maximize();
				
			} 
			
			else if (Browser.contains("IE") &&(Remote.equalsIgnoreCase("No"))) {
				driver = new RemoteWebDriver(new URL(hubLocation),DesiredCapabilities.internetExplorer()); 
				driver = new InternetExplorerDriver(DesiredCapabilities.internetExplorer());
				driver.manage().window().maximize();
				
			} 
			
			
			
			else if (Browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver(cap);
				driver = new RemoteWebDriver(new URL(hubLocation), cap);
				driver.manage().window().maximize();
			} else {
				driver = new FirefoxDriver(setupFirefoxCapabilities());
				// driver = new RemoteWebDriver(new URL(hubLocation), cap);
			}
			// }
			
			

		} catch (Exception ex) {
			System.out.println("Unable to load the config file: "
					+ ex.getMessage());
			ex.printStackTrace();
		}
		
		
	}

	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public static boolean isIE() {
		return props.getProperty("BROWSER").toUpperCase().contains("IE");
	}

	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public static boolean isIE8() {
		return props.getProperty("BROWSER").toUpperCase().contains("IE8");
	}

	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public static boolean isIE9() {
		return props.getProperty("BROWSER").toUpperCase().contains("IE9");
	}

	public static boolean isIE10() {
		return props.getProperty("BROWSER").toUpperCase().contains("IE10");
	}

	public static boolean isIE11() {
		return props.getProperty("BROWSER").toUpperCase().contains("IE11");
	}

	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public static boolean isFF() {
		return props.getProperty("BROWSER").toUpperCase().contains("FF");
	}

	/**
	 * check the current test is running on IE or non-IE browsers
	 * 
	 * @return return true if it's a IE browser test, else it will return false
	 */
	public static boolean isChrome() {
		return props.getProperty("BROWSER").toUpperCase().contains("CHROME");
	}

	/**
	 * check the current test is running on Safari or non-Safari browsers
	 * 
	 * @return return true if it's a Safari browser test, else it will return
	 *         false
	 */
	public static boolean isSafari() {
		return props.getProperty("BROWSER").toUpperCase().contains("SAFARI");
	}

	/**
	 * Clears Screen shot folder before execution of a test suite
	 */
	public void clearScreenShotFolder() {
		// Deletes all files and subdirectories under dir.
		// Returns true if all deletions were successful.
		// If a deletion fails, the method stops attempting to delete and
		// returns false.

		String workingDir = System.getProperty("user.dir");

		String screenShotFolder = workingDir + File.separator
				+ TEST_RESULTS_OUTPUT_FOLDER + File.separator
				+ SCREENSHOTS_FOLDER_NAME;
		System.out.println("Folder to be cleared is " + screenShotFolder);
		File dir = new File(screenShotFolder);
		if (dir.exists()) {
			try {
				delete(dir);
			} catch (IOException iex) {
				System.out.println("Error seen on deleting a directory "
						+ iex.getMessage());
				iex.printStackTrace();
			}
		} else
			System.out
					.println("Screen Shot director not present in test-output, no action needed");

	}

	/**
	 * CommonMethod(readExcelData) which reads the data from the excel sheet
	 * 
	 */
	
	
		public static String readEnv(String configFile) {
			try {
				props = new Properties();
				File myFile = new File(configFile);
				System.out.println(myFile.getAbsolutePath()
						+ " config file name:  " + configFile);
				FileReader configfile = new FileReader(myFile.getAbsolutePath());
				System.out.println("Reading the file for Environment");
				props.load(configfile);
				System.out.println("Loading the config file");
				setVariables(props);
				System.out.println("File loaded ");
		
				if(Env.equalsIgnoreCase("prod")){
					URL="http://www.splunk.com";
				}
				else if(Env.equalsIgnoreCase("qa")){
					URL="http://publish01-qa.cms.splunk.com";
				}
				else if(Env.equalsIgnoreCase("stage")){
					URL="http://web01-stg.cms.splunk.com";
				}
	}
			catch(Exception ex){
				
				System.out.println("Unable to load the config file: "
						+ ex.getMessage());
				ex.printStackTrace();
			
			
				
			}
			return URL;
		}
			
		
	public String[][] readExcelData(String filePath, String sheetName,
			String tableName) {
		String[][] testData = null;
		System.out.println("Reading readExcelData method");

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(
					filePath));
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Cell[] boundaryCells = findCell(sheet, tableName);
			int boundaryLenght = boundaryCells.length;
			System.out.println("Boundaru Cells are" + boundaryCells);
			Cell startCell = boundaryCells[0];
			System.out.println("Boundary start cell" + startCell);

			Cell endCell = boundaryCells[1];
			int startRow = startCell.getRowIndex() + 1;
			int endRow = boundaryLenght - 1;
			int startCol = startCell.getColumnIndex() + 1;
			int endCol = endCell.getColumnIndex() - 1;
			testData = new String[endRow - startRow + 1][endCol - startCol + 1];
			System.out.println("Reading for loop");
			for (int i = startRow; i < endRow + 1; i++) {
				for (int j = startCol; j < endCol + 1; j++) {
					if (sheet.getRow(i).getCell(j).getCellType() == XSSFCell.CELL_TYPE_STRING) {
						testData[i - startRow][j - startCol] = sheet.getRow(i)
								.getCell(j).getStringCellValue();
					} else if (sheet.getRow(i).getCell(j).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						Double temp = sheet.getRow(i).getCell(j)
								.getNumericCellValue();
						testData[i - startRow][j - startCol] = String
								.valueOf(temp.intValue());
					}
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		System.out.println("Returing testdata");

		return testData;

	}

	/**
	 * Deletes a directory. If directory has files, it will delete all files and
	 * then delete the drectory for empty directories it deletes directly
	 * 
	 * @param file
	 * @throws IOException
	 */
	private static void delete(File file) throws IOException {
		System.out.println("File Existis " + file.exists() + " file Directory "
				+ file.isDirectory());
		if (file.exists() && file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : "
						+ file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					fileDelete.delete();
					// delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : "
							+ file.getAbsolutePath());
				}
			}

		}

	}

	public static XSSFCell[] findCell(XSSFSheet sheet, String text) {
		String pos = "start";
		XSSFCell[] cells = new XSSFCell[2];
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING
						&& text.equals(cell.getStringCellValue())) {
					if (pos.equalsIgnoreCase("start")) {
						cells[0] = (XSSFCell) cell;
						// System.out.println("First Cell found at:"+cell.getRowIndex()+","+cell.getColumnIndex());
						pos = "end";
					} else {
						cells[1] = (XSSFCell) cell;
						// System.out.println("First Cell found at:"+cell.getRowIndex()+","+cell.getColumnIndex());
					}
				}
			}
		}
		return cells;
	}

	/**
	 * This method captures the screen shot. It supports both local and remote
	 * configurations for web driver
	 * 
	 * @param driver
	 * @param screenShotFile
	 */
	public void takeScreenShot(WebDriver driver, String screenShotFile) {
		try {
			File scrFile = null;
			if (props.getProperty("REMOTE").equals("Yes")) {
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				scrFile = ((TakesScreenshot) augmentedDriver)
						.getScreenshotAs(OutputType.FILE);
			} else {
				scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
			}

			System.out.println("Screen Shot file name is " + screenShotFile);
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(screenShotFile));
			System.out.println("Captured Screenshot File is saved as "
					+ scrFile.getAbsolutePath());

		} catch (IOException e) {
			System.out.println("Encountered exception " + e.getMessage()
					+ " on taking screen shot");
			e.printStackTrace();
		}

	}

	/**
	 * This is the method to take the screenshot in action
	 * 
	 * @param method
	 *            The actual test calling
	 * @param testResult
	 *            The result of the given test
	 */
	public void takeScreenShot(String methodName) {
		String workingDir = System.getProperty("user.dir");

		System.out.println("Working Directory is " + workingDir);
		System.out.println("Test Result for " + methodName + " is not passed ");

		try {
			File scrFile = null;
			if (props.getProperty("REMOTE").equalsIgnoreCase("yes")) {
				WebDriver augmentedDriver = new Augmenter().augment(driver);
				scrFile = ((TakesScreenshot) augmentedDriver)
						.getScreenshotAs(OutputType.FILE);
			} else {
				scrFile = ((TakesScreenshot) driver)
						.getScreenshotAs(OutputType.FILE);
			}

			String screenShotFile = workingDir + File.separator
					+ CommonTest.TEST_RESULTS_OUTPUT_FOLDER + File.separator
					+ CommonTest.SCREENSHOTS_FOLDER_NAME + File.separator
					+ methodName + props.getProperty("BROWSER")
					+ SCREENSHOT_IMG_FORMAT;
			System.out.println("Screen Shot file name is " + screenShotFile);
			// Now you can do whatever you need to do with it, for example copy
			// somewhere
			FileUtils.copyFile(scrFile, new File(screenShotFile));
		} catch (IOException e) {
			System.out.println("Encountered exception " + e.getMessage()
					+ " on taking screen shot");
			e.printStackTrace();
		} catch (WebDriverException wex) {
			System.out.println("Encountered exception " + wex.getMessage()
					+ " on taking screen shot");
			wex.printStackTrace();
		}
	}

}
