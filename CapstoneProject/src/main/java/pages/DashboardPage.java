package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Represents the dashboard page of the application.
 * Provides methods to interact with dashboard elements and navigate to other sections of the application.
 */
public class DashboardPage {
	
	private WebDriver driver;  // WebDriver instance for the page
	private WebDriverWait wait;  // WebDriverWait instance for explicit waits on this page
	
	private By pimLinkLocator = By.xpath("//span[.='PIM']");  // Locator for the PIM link
	private By leaveLinkLocator = By.xpath("//span[.='Leave']");  // Locator for the Leave link
	private By timeLinkLocator = By.xpath("//span[.='Time']");  // Locator for the Time link

	/**
	 * Constructs a new DashboardPage object.
	 *
	 * @param driver The WebDriver instance associated with this page.
	 * @param wait   The WebDriverWait instance for explicit waits on this page.
	 */
	public DashboardPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}
	
	/**
	 * Clicks on the provided link.
	 *
	 * @param linkLocator The By locator of the link to click.
	 */
	public void clickLink(By linkLocator) {
		try {
			// Wait for the visibility of the link before clicking
			wait.until(ExpectedConditions.visibilityOfElementLocated(linkLocator));
			driver.findElement(linkLocator).click();
		} catch (TimeoutException e) {
			System.out.println("Element was not found in the given timeframe: " + e.getMessage());
		} catch (NoSuchElementException e) {
			System.out.println("An element was not found: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error while clicking on the link: " + e.getMessage());
		}
	}
	
	/**
	 * Navigates to the PIM section of the application.
	 *
	 * @return A new PIMPage object after navigating to the PIM section.
	 */
	public PIMPage clickPIM() {
		clickLink(pimLinkLocator);
		return new PIMPage(driver, wait);
	}

	/**
	 * Navigates to the Leave section of the application.
	 *
	 * @return A new LeavePage object after navigating to the Leave section.
	 */
	public LeavePage clickLeave() {
		clickLink(leaveLinkLocator);
		return new LeavePage(driver, wait);
	}

	/**
	 * Navigates to the Time section of the application.
	 *
	 * @return A new TimePage object after navigating to the Time section.
	 */
	public TimePage clickTime() {
		clickLink(timeLinkLocator);
		return new TimePage(driver, wait);
	}

}
