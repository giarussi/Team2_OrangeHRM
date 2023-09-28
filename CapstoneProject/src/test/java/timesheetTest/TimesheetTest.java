package timesheetTest;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import base.Base;
import loginTest.LoginTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TimePage;

public class TimesheetTest extends Base {
	
	
	
	
	 private SoftAssert softAssert = new SoftAssert();
	TimePage time ;
	
	@BeforeSuite
	
	public void loginSetup() {
		
		var loginPage = loginpage.login("Admin", "admin123");
	 	
	 	 time=loginPage.clickTime();
	}
	
	@Test
	
	public void createtimesheet_by_admin() throws InterruptedException {
		
		
		
		boolean isElementLocated=time.searchEmployee("a");
		softAssert.assertTrue(isElementLocated, "Element is not visible");
		time.createTimesheet("8","a");
		
	}
	
	
	
	@Test
	
	public void testPunchIn() {
		
	 	boolean isElementLocated=time.punchIn(); // calling punch In function
	 	softAssert.assertTrue(isElementLocated, "Element is not visible");
	 	
	 	
	 	// calling log out function
	 	 softAssert.assertAll();
	}
	
	@Test
	
	public void testPunchOut() throws InterruptedException {
		
	 	boolean isElementLocated=time.punchOut();
	 	softAssert.assertTrue(isElementLocated, "Element is not visible");
	 	//calling logout function
	 	 softAssert.assertAll();
	}
	
	
	@Test
	
	public void testviewMyRecords() {
		
	 	boolean isElementLocated=time.viewMyRecords();
	 	softAssert.assertTrue(isElementLocated, "Element is not visible");
		
	}

	
	@Test
	
	public void testViewProjectReport() {
		boolean isElementLocated= time.viewProjectReport();
		
	}

	
	@Test
	
	public void testEmployeeToCreateTimesheet() {
		
		time.employeeToEnterTimesheet();
	}

}
