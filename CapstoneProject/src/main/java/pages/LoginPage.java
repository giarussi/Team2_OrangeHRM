package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Represents the login page of the application.
 * Provides methods to interact with login page elements and perform actions such as logging in.
 */
public class LoginPage {
	
	private WebDriver driver;  // WebDriver instance for the page
	private WebDriverWait wait;  // WebDriverWait instance for explicit waits on this page
	private By usernameField = By.xpath("//input[@name='username']");  // Locator for the username input field
	private By passwordField = By.xpath("//input[@name='password']");  // Locator for the password input field
	private By loginButton = By.xpath("//button[@type='submit']");  // Locator for the login button
	

	/**
	 * Constructs a new LoginPage object.
	 *
	 * @param driver The WebDriver instance for this page.
	 * @param wait   The WebDriverWait instance for explicit waits on this page.
	 */
	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	/**
	 * Logs into the application using the provided username and password.
	 *
	 * @param username The username for logging in.
	 * @param password The password for the given username.
	 * @return A new DashboardPage object after successful login or null if an exception occurs.
	 */
	public DashboardPage login(String username, String password) {
		try {
			// Wait for the visibility of the username field before interacting
			wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
			
			driver.findElement(usernameField).sendKeys(username);  // Enter the provided username
			driver.findElement(passwordField).sendKeys(password);  // Enter the provided password
			driver.findElement(loginButton).click();  // Click the login button
			
			// Return a new DashboardPage object once the login action is complete
			return new DashboardPage(driver, wait);
		} catch (TimeoutException e) {
			System.out.println("Element was not found in the given timeframe: " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("An element was not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred during login: " + e.getMessage());
		}
		// Return null if there was an exception
		return null;
	}
	
	
}
