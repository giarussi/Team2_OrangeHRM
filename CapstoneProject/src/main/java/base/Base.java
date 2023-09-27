package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utility.ExcelUtils;
import utility.EventReporter;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.HashMap;

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
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


/**
 * Base class that sets up and tears down the testing environment.
 * It provides browser initialization, WebDriverWait initialization, and screenshot functionality.
 */
public class Base {

    public static WebDriver driver;  // The main WebDriver instance for the test suite.
    public static WebDriverWait wait;  // WebDriverWait instance for waiting for elements.
    protected LoginPage loginpage;  // LoginPage object to perform login operations.
    public String testcaseName = null;
   	public String shortTestName = null;
   	public Object[][] localData = null;
   	public String path;
   	
   	private String sheet;
   	HashMap<String, String> expectedCommonValidationdata = new HashMap<String, String>();
    /**
     * Initializes the WebDriver based on the given browser name.
     * 
     * @param browserName Name of the browser to initialize ("chrome", "firefox", etc.).
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
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // waits up to 20 seconds
        } catch (Exception e) {
            System.out.println("Error initializing waits: " + e.getMessage());
        }
    }


    /**
     * Set up method to be run before the entire test suite.
     * Initializes the browser, sets the WebDriverWait, and navigates to the login page.
     */


    @BeforeSuite
    public void setUp() {
        initializeBrowser("chrome");  // can use external config to manage browser type
        initializeWaits();


        loginpage = new LoginPage(driver, wait);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    
    
    @DataProvider(name="getdata") // parallel=true
   	public Object[][] getData(Method method) {
   		path="Resources\\TestData\\OrangeHRM_TestCases.xlsx";
   		sheet="Valid";
   			System.out.println("Test Case method Name :" + method.getName());
   			testcaseName = method.getName();
   			try {
   				localData = ExcelUtils.getData(path,sheet,testcaseName);
   			} catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}			

   		return localData;
   	}

    /**
     * Clean-up method to be run after the entire test suite.
     * Closes the browser if the driver is not null.
     */
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit(); //closing the browser

 
        }
    }
    
    /**
     * Takes a screenshot if the test method fails.
     * The screenshot will be saved in the 'Resources/Screenshots/' directory.
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

}
