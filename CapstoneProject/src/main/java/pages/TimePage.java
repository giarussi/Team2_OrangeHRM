package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimePage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By employeeNameLocator = By.xpath("//input[@placeholder='Type for hints...']");// Employee Name to be
																							// searched
	private By viewbuttonLocator = By.xpath("//button[@type='submit']");// view button to search employee
	private By createTimesheet = By.xpath("//button[@type='button']"); // create timesheet button
	private By editButton = By.xpath("//button[text()=' Edit ']");// edit timesheet button
	private By enterProject = By.xpath("//input[@placeholder='Type for hints...']");// enter project box
	private By activitySelection = By.xpath("//*[text()='-- Select --']");// activity selection dropdown
	private By weekdayTime = By.xpath("//input[@autocomplete='off']");// enter time columns monday to sunday

	private By attendanceTab = By.xpath("//span[text()='Attendance ']");
	private By punchInOut = By.xpath("//ul/li/a[text()='Punch In/Out']");
	private By submitPunchIn = By.xpath("//button[text()=' In ']");
	private By submitPunchOut = By.xpath("//div/button[text()=' Out ']");
	private By myRecords = By.xpath("//ul/li/a[text()='My Records']");
	private By myRecordsTableData = By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border']");
	private By reportsTab = By.xpath("//span[text()='Reports ']");
	private By projectReport = By.xpath("//li/a[text()='Project Reports']");
	private By projectName = By.xpath("//input[@placeholder='Type for hints...']");

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

	public TimePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
	}

	public boolean searchEmployee(String employeeName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNameLocator));
		driver.findElement(employeeNameLocator).sendKeys(employeeName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		listData("Odis  Adalwin");
		
		clickLink(viewbuttonLocator);
		boolean isElementVisible = driver.findElement(viewbuttonLocator).isDisplayed();
		return isElementVisible;

	}

	public void createTimesheet(String time, String projectName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(createTimesheet));
		clickLink(createTimesheet);
		wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
		clickLink(editButton);
		driver.findElement(enterProject).sendKeys(projectName);
		listData("ACME Ltd - ACME Ltd");
		

		// Dynamic element locators to find
		driver.findElement(activitySelection).click();
		listData("Customizations");
		//driver.findElement(activitySelection).sendKeys(Keys.ARROW_DOWN);
		// Find multiple elements using XPath
		List<WebElement> links = driver.findElements(weekdayTime);
		// Iterate through the found elements and enter the time in it

		for (WebElement link : links) {
			link.sendKeys(time);
		}

	}

	public boolean punchIn() {

		clickLink(attendanceTab);
		clickLink(punchInOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitPunchIn));
		clickLink(submitPunchIn);
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitPunchOut));
		boolean isElementVisible = driver.findElement(submitPunchOut).isDisplayed();
		return isElementVisible;

	}

	public boolean punchOut() throws InterruptedException {
		clickLink(attendanceTab);
		clickLink(punchInOut);

		wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitPunchOut));
		Thread.sleep(5000);
		clickLink(submitPunchOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(submitPunchIn));
		boolean isElementVisible = driver.findElement(submitPunchIn).isDisplayed();
		return isElementVisible;
	}

	public boolean viewMyRecords() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(attendanceTab));
		clickLink(attendanceTab);
		clickLink(myRecords);
		wait.until(ExpectedConditions.visibilityOfElementLocated(myRecordsTableData));
		boolean isElementVisible = driver.findElement(myRecordsTableData).isDisplayed();
		return isElementVisible;
	}

	public void listData(String value) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//span")));
		List<WebElement> options = driver.findElements(By.xpath("//div[@role='listbox']//span"));
		for (WebElement op : options) {
			System.out.println(op.getText());
		}
		for (WebElement op : options) {
			if (op.getText().equals(value)) {
				op.click();
				break;
			}
		}

	}

	public boolean viewProjectReport() {
		try {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(reportsTab));
			
			clickLink(reportsTab);
			clickLink(projectReport);
			//clickLink(projectName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(projectName));
			driver.findElement(projectName).sendKeys("a");
			listData("ACME Ltd - ACME Ltd");
			boolean isElementVisible = driver.findElement(projectName).isDisplayed();
			return isElementVisible;
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	public void employeeToEnterTimesheet() {
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(editButton));
		clickLink(editButton);
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterProject));
		driver.findElement(enterProject).sendKeys("a");
		listData("ACME Ltd - ACME Ltd");
		
		
		
	}
	
	
}
