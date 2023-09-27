package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utility.EventReporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Hashtable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;



/**
 * Base class that sets up and tears down the testing environment. It provides
 * browser initialization, WebDriverWait initialization, and screenshot
 * functionality.
 */
public class Base {

	public static WebDriver driver; // The main WebDriver instance for the test suite.
	public static WebDriverWait wait; // WebDriverWait instance for waiting for elements.
	protected DashboardPage dashBoardPage;
	protected LoginPage loginpage; // LoginPage object to perform login operations.
	protected PIMPage pimPage; // PIM Page object to perform

	static String testCaseSheetName = "TestCases";
	private static String colNameEmpNumber = "EmployeeNumber";
	private String sitTestCasesXLS = "C:\\Users\\u1234713\\Documents\\GitHub\\Team2_OrangeHRM\\CapstoneProject\\Resources\\DataDrivenTesting\\DataDriven.xlsx";
	public String path = "";
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	private static String testDataSheetName = "Valid";
	private String testcaseName = "";
	
	/**
	 * Private Constructor to read Excel file. It will initialize 'Excel Reader'
	 * with destination file path address.
	 * 
	 * @param filepath (Datatype: String)
	 */
	public Base() {
		path = sitTestCasesXLS;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			// fis.close();
		} catch (Exception e) {
			System.err.println(
					"Got an exception while reading the Excel file. " + path + "\nException is ==> " + e.getMessage());
			try {
				// fis.close();
			} catch (Exception ex) {
				System.err.println("Got an exception while closing the file input stream of the Excel file. " + path
						+ "\nException is ==> " + e.getMessage());
			}
		}
	}
	
	/**
	 * Method to get cell value from a sheet based on column number and row number
	 * 
	 * @param sheetName Name of sheet in workbook. (type: String)
	 * @param colNum    index of column number (type:int)
	 * @param rowNum    index of row number (type:int)
	 * @return cell data else throws an error (type: String)
	 */
	synchronized public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";

			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.getStackTrace();
			throw new Error("Got an exception while reading the data from row " + rowNum + " and column " + colNum);
		}
	}
	
	
	public  Object[][] getData(String testCaseName) {
		/*
		 * excelSheetCheck(sitTestCasesXLS, testCaseSheetName);
		 * testCaseNameCheck(sitTestCasesXLS, 0, testCaseName, testDataSheetName);
		 */
		// reads data for only testCaseName
		int testStartRowNum = 0;
		
		//String cellData = getCellData(testDataSheetName, 0, testStartRowNum);
		while (!getCellData(testDataSheetName, 0, testStartRowNum).equals(testCaseName)) {
			testStartRowNum++;
		}

		int colStartRowNum = testStartRowNum + 1;
		int dataStartRowNum = testStartRowNum + 2;

		// calculate rows of data
		int rows = 0;
		while (getCellData(testDataSheetName, 0, dataStartRowNum + rows).equals("")) {
			rows++;
		}

		// calculate total cols
		int cols = 0;
		while (getCellData(testDataSheetName, cols, colStartRowNum).equals("")) {
			cols++;
		}

		Object[][] data = new Object[rows][1];
		// read the data
		int dataRow = 0;
		Hashtable<String, String> table = null;
		for (int rNum = dataStartRowNum; rNum < dataStartRowNum + rows; rNum++) {
			table = new Hashtable<String, String>();
			for (int cNum = 0; cNum < cols; cNum++) {
				String key = getCellData(testDataSheetName, cNum, colStartRowNum);
				String value = getCellData(testDataSheetName, cNum, rNum);
				table.put(key, value);
			}
			data[dataRow][0] = table;
			dataRow++;
		}
		return data;
	}

	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	

	/**
	 * Initializes the WebDriver based on the given browser name.
	 * 
	 * @param browserName Name of the browser to initialize ("chrome", "firefox",
	 *                    etc.).
	 */

	public static void initializeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();

				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");

				driver = new ChromeDriver(options);
				WebDriverListener listener = new EventReporter();

				driver = new EventFiringDecorator(listener).decorate(driver);
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			// can add other browsers similarly
		} catch (Exception e) {
			System.out.println("Error initializing browser: " + e.getMessage());
		}
	}

	/**
	 * Initializes the WebDriverWait instance with a fixed timeout.
	 */

	public static void initializeWaits() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // waits up to 20 seconds
		} catch (Exception e) {
			System.out.println("Error initializing waits: " + e.getMessage());
		}
	}

	/**
	 * Set up method to be run before the entire test suite. Initializes the
	 * browser, sets the WebDriverWait, and navigates to the login page.
	 */

	@BeforeSuite
	public void setUp() {
		initializeBrowser("chrome"); // can use external config to manage browser type
		initializeWaits();
		loginpage = new LoginPage(driver, wait);
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//loginpage.login("admin", "admin123");
		dashBoardPage = new DashboardPage(driver, wait);
		
	}

	@BeforeClass
	public void loginTest() {

	}

	@BeforeTest
	public void loginTest2() {
		System.out.println("Before Test");

	}

	/**
	 * Clean-up method to be run after the entire test suite. Closes the browser if
	 * the driver is not null.
	 */
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit(); // closing the browser

		}
	}

	/**
	 * Takes a screenshot if the test method fails. The screenshot will be saved in
	 * the 'Resources/Screenshots/' directory.
	 *
	 * @param result The result of the test method execution.
	 */
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				TakesScreenshot camera = (TakesScreenshot) driver;
				File screenshot = camera.getScreenshotAs(OutputType.FILE);
				String screenshotName = result.getName() + "_" + System.currentTimeMillis() + ".png";
				String screenshotPath = "Resources/Screenshots/" + screenshotName;
				Files.createDirectories(Paths.get("Resources/Screenshots"));
				Files.move(screenshot.toPath(), Paths.get(screenshotPath), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Test failed: " + result.getName());
				System.out.println("Screenshot saved: " + screenshotPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Fetching Data from Excel
	 * 
	 */
	@DataProvider(name = "authdata")
	public Object[][] readExcelData() throws IOException {
		String filePath = "C:\\Users\\u1234713\\OneDrive - MMC\\Documents\\SDET\\TestAutomationSDET\\Resources\\DataDrivenTesting\\DataDriven.xlsx";
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workBook = new XSSFWorkbook(fis);
		Sheet sheet = workBook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
		int columnCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount][columnCount];
		for (int i = 1; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = cell.getStringCellValue();
			}
		}

		workBook.close();
		fis.close();

		return data;

	}

	
	@DataProvider(name = "getData")
	public Object[][] getData(Method method) {
		testcaseName = method.getName();
	
		Object[][] localData = getData(testcaseName);
		int rows = localData.length;
		Hashtable<String, String> getValues = new Hashtable<String, String>();
		for (int row = 0; row < rows; row++) {
			int cols = localData[row].length;
			for (int col = 0; col < cols; col++) {
				getValues = (Hashtable<String, String>) localData[row][col];
			}
		}
		//data = getValues;
		return localData;
	}
	

}
