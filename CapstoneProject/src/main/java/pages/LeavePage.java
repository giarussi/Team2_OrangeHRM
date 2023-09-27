package pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeavePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private	By clickOnEntitlements=By.xpath("//span[text()='Entitlements ']");
	private By clickOnAddEntitlement=By.xpath("//ul//li//a[text()=\"Add Entitlements\"]");
	private By enterEmployeeField=By.xpath("//input[@placeholder=\"Type for hints...\"]");
	private By selectLeaveType=By.className("oxd-select-text-input");
	private By selectdate=By.xpath("//div[text()='2023-01-01 - 2023-12-31']");
	private By clickOnSaveButton=By.xpath("//button[@type='submit']");
	private By enterDay=By.xpath("//div[3]//input[@class=\"oxd-input oxd-input--active\"]");
	private By confirmMessage=By.xpath("//div/p[@class='oxd-text oxd-text--p oxd-text--card-title']");
	private By clickOnConfirm=By.xpath("//button[text()=' Confirm ']");
	private By multipleEmployeesCheckBox=By.xpath("//*[text()='Multiple Employees']");
	private By clickonEmpSubUnit=By.xpath("(//div[text()='-- Select --'])[2]");
	private By clickonEmpLeaveType=By.xpath("//label[text()='Leave Type']//..//..//i");
	private By clickONleaveListTab=By.xpath("//a[text()='Leave List']");

	public LeavePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait=wait;
	}
	
	
	public void clickOnEntitlements() {
		 wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnEntitlements));
		driver.findElement(clickOnEntitlements).click();
	}
	
	
public  void listData(String value) {
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']//span")));
		List<WebElement> options=driver.findElements(By.xpath("//div[@role='listbox']//span"));
				for(WebElement op:options)
				{
				System.out.println(op.getText());
				}
				for(WebElement op:options)
				{
				if(op.getText().equals(value))
				{		
				op.click();	
				break;
				}
				}
		
	}

	
	
	
	public void addEntitlements(String empName,String leaveType,String date,String days) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnAddEntitlement));
		driver.findElement(clickOnAddEntitlement).click();
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterEmployeeField));
		driver.findElement(enterEmployeeField).sendKeys(empName);
		listData("Shweta Misal");
		driver.findElement(selectLeaveType).click();
		listData(leaveType);
		driver.findElement(selectdate).click();
		listData(date);
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterDay));
		driver.findElement(enterDay).sendKeys(days);
		driver.findElement(clickOnSaveButton).click();
	}
	
	
	public String getConfrimationMessageAlert() {
		 wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMessage));
		return driver.findElement(confirmMessage).getText();
	}
	
	public void clickOnConfrimButton() {
		driver.findElement(clickOnConfirm).click();
		
	}
	
	
	public void addEntitlementsForMultipleEmployees(String subUnit, String leaveType,String date,String days) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickOnAddEntitlement));
		driver.findElement(clickOnAddEntitlement).click();
		wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(multipleEmployeesCheckBox));
		driver.findElement(multipleEmployeesCheckBox).click();
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickonEmpSubUnit));
		driver.findElement(clickonEmpSubUnit).click();
		listData(subUnit);
		driver.findElement(selectdate).click();
		listData(date);
		wait=new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(clickonEmpLeaveType));
		driver.findElement(clickonEmpLeaveType).click();
		listData(leaveType);
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(enterDay));
		driver.findElement(enterDay).sendKeys(days);
		driver.findElement(clickOnSaveButton).click();
	}

}
