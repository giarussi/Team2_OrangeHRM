package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import utility.EventReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

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

public class Base{

    public static WebDriver driver;
    public static WebDriverWait wait;
    protected LoginPage loginpage;

    // Initialize the web browser
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

    // Initialize WebDriverWait object
    public static void initializeWaits() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // waits up to 20 seconds
        } catch (Exception e) {
            System.out.println("Error initializing waits: " + e.getMessage());
        }
    }

    @BeforeSuite
    public void setUp() {
        initializeBrowser("chrome");  // can use external config to manage browser type
        initializeWaits();
        

        loginpage = new LoginPage(driver,wait);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();//closing the browser
        }
    }
    
    /**
     * Method to take a screenshot when a test method fails.
     *
     * @param result The test result.
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
