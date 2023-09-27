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
	private By errorMessage = By.cssSelector(".oxd-alert-content-text"); // Locator for the element that displays error messages on the page.
	private By dashboarHeading = By.cssSelector("h6.oxd-topbar-header-breadcrumb-module"); // Locator for the heading element on the dashboard page.
	private By adminLinkLocator = By.xpath("//span[.='Admin']");
	private By recruitmentLinkLocator = By.xpath("//span[.='Recruitment']");
	private By myInfoLinkLocator = By.xpath("//span[.='My Info']");
	private By performanceLinkLocator = By.xpath("//span[.='Performance']");
	private By directoryLinkLocator = By.xpath("//span[.='Directory']");
	private By maintenanceLinkLocator = By.xpath("//span[.='Maintenance']");
	private By claimLinkLocator = By.xpath("//span[.='Claim']");
	private By timeAtWorkWidget = By.cssSelector(".orangehrm-dashboard-widget[data-v-fcab0262]");
	private By timeAtWorkWidgettitle = By.cssSelector(".oxd-text--p[data-v-fcab0262]");
	

	
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
	
	/**
	 * Retrieves the error message displayed on the page.
	 * <p>
	 * This method waits until the error message becomes visible 
	 * and then returns its text content.
	 * </p>
	 * 
	 * @return The text content of the error message. Returns null if the element is not found or any error occurs.
	 */
	public String getErrorMessage() {
	    try {
	        // Wait until the error message is visible on the page.
	        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));

	        // Return the text content of the error message.
	        return driver.findElement(errorMessage).getText();
	    } catch (TimeoutException e) {
	        // Log or print the exception, if desired. This is raised if the wait times out before the element becomes visible.
	        System.out.println("Timeout waiting for the error message to appear.");
	    } catch (NoSuchElementException e) {
	        // Log or print the exception. This is raised if the element does not exist on the page.
	        System.out.println("Error message element not found on the page.");
	    } catch (Exception e) {
	        // Handle any other unexpected exceptions.
	        System.out.println("Unexpected error occurred: " + e.getMessage());
	    }
	    return null;
	}

	/**
	 * Checks if the dashboard heading element is displayed on the page.
	 * 
	 * @return True if the dashboard heading is displayed, otherwise false.
	 */
	public boolean isDashboardElementDisplayed() {
	    try {
	        // Wait until the dashboard heading is visible on the page.
	        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboarHeading));

	        // Check if the dashboard heading is displayed on the page and return the result.
	        return driver.findElement(dashboarHeading).isDisplayed();
	    } catch (TimeoutException e) {
	        // Log or print the exception, if desired. This is raised if the wait times out before the element becomes visible.
	        System.out.println("Timeout waiting for the dashboard heading to appear.");
	    } catch (NoSuchElementException e) {
	        // Log or print the exception. This is raised if the element does not exist on the page.
	        System.out.println("Dashboard heading element not found on the page.");
	    } catch (Exception e) {
	        // Handle any other unexpected exceptions.
	        System.out.println("Unexpected error occurred: " + e.getMessage());
	    }
	    return false;
	}
	
	/**
	 * Verifies if the PIM link is present on the dashboard.
	 *
	 * @return True if the PIM link is present, otherwise false.
	 */
	public boolean isPIMLinkPresent() {
	    return isLinkPresent(pimLinkLocator);
	}

	/**
	 * Verifies if the Leave link is present on the dashboard.
	 *
	 * @return True if the Leave link is present, otherwise false.
	 */
	public boolean isLeaveLinkPresent() {
	    return isLinkPresent(leaveLinkLocator);
	}

	/**
	 * Verifies if the Time link is present on the dashboard.
	 *
	 * @return True if the Time link is present, otherwise false.
	 */
	public boolean isTimeLinkPresent() {
	    return isLinkPresent(timeLinkLocator);
	}

	/**
	 * Common method to verify if a given link is present on the dashboard.
	 *
	 * @param linkLocator The By locator of the link to verify.
	 * @return True if the link is present, otherwise false.
	 */
	private boolean isLinkPresent(By linkLocator) {
	    try {
	        // Wait for the presence of the link. It doesn't have to be visible, just present in the DOM.
	        wait.until(ExpectedConditions.presenceOfElementLocated(linkLocator));
	        return true;
	    } catch (TimeoutException e) {
	        // Log or print the exception, if desired. This is raised if the wait times out before the link is found.
	        System.out.println("Timeout waiting for the link to be present.");
	    } catch (Exception e) {
	        // Handle any other unexpected exceptions.
	        System.out.println("Unexpected error occurred: " + e.getMessage());
	    }
	    return false;
	}
	
	/**
	 * Verifies if the Admin link is present on the dashboard.
	 *
	 * @return True if the Admin link is present, otherwise false.
	 */
	public boolean isAdminLinkPresent() {
	    return isLinkPresent(adminLinkLocator);
	}

	/**
	 * Verifies if the Recruitment link is present on the dashboard.
	 *
	 * @return True if the Recruitment link is present, otherwise false.
	 */
	public boolean isRecruitmentLinkPresent() {
	    return isLinkPresent(recruitmentLinkLocator);
	}

	/**
	 * Verifies if the My Info link is present on the dashboard.
	 *
	 * @return True if the My Info link is present, otherwise false.
	 */
	public boolean isMyInfoLinkPresent() {
	    return isLinkPresent(myInfoLinkLocator);
	}

	/**
	 * Verifies if the Performance link is present on the dashboard.
	 *
	 * @return True if the Performance link is present, otherwise false.
	 */
	public boolean isPerformanceLinkPresent() {
	    return isLinkPresent(performanceLinkLocator);
	}

	/**
	 * Verifies if the Directory link is present on the dashboard.
	 *
	 * @return True if the Directory link is present, otherwise false.
	 */
	public boolean isDirectoryLinkPresent() {
	    return isLinkPresent(directoryLinkLocator);
	}

	/**
	 * Verifies if the Maintenance link is present on the dashboard.
	 *
	 * @return True if the Maintenance link is present, otherwise false.
	 */
	public boolean isMaintenanceLinkPresent() {
	    return isLinkPresent(maintenanceLinkLocator);
	}

	/**
	 * Verifies if the Claim link is present on the dashboard.
	 *
	 * @return True if the Claim link is present, otherwise false.
	 */
	public boolean isClaimLinkPresent() {
	    return isLinkPresent(claimLinkLocator);
	}
	
	public String getTimeAtWorkTitle() {
		return driver.findElement(timeAtWorkWidgettitle).getText();
	}



}
