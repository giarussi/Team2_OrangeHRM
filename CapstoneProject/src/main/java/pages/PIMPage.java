package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	private By addEmployeeButton = By.xpath("//div[@class='orangehrm-header-container']/button[text()=' Add ']");  // Add button for Employee
	private By employeeFirstName = By.xpath("//input[@name='firstName']");  // Employee first name
	private By employeeMiddleName = By.xpath("//input[@name='middleName']");  //Employee middle name
	private By employeeLastName = By.xpath("//input[@name='lastName']");  //Employee last name
	private By createLoginDetailsToggleSwitch = By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");  //Create Details Toggle switch
	private By userName = By.xpath("//*[text()='Username']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");  //username
	private By password =  By.xpath("//*[text()='Password']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");  // password
	private By confirmPassword =  By.xpath("//*[text()='Confirm Password']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");  //Confirm Password
	private By saveButton = By.xpath("//button[@type='submit']");   //save Button
	
	

	public PIMPage(WebDriver driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait=wait;
	}
	

	/**
	 * Create Employee Functionality.
	 *
	 * @return A new PIMPage object after navigating to the PIM section.
	 * @throws InterruptedException 
	 */
	public void createEmployee(String FirstName, String MiddleName, String LastName   ) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(addEmployeeButton).click();
		Thread.sleep(5000);
		driver.findElement(employeeFirstName).sendKeys(FirstName);
		driver.findElement(employeeLastName).sendKeys(MiddleName);
		driver.findElement(employeeLastName).sendKeys(LastName);
		driver.findElement(createLoginDetailsToggleSwitch).click();
		driver.findElement(userName).sendKeys("Siddhant28");
		driver.findElement(password).sendKeys("admin123");
		driver.findElement(confirmPassword).sendKeys("admin123");
		driver.findElement(saveButton);
	}
	
	
	
}
