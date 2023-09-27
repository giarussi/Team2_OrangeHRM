package leaveTests;

import java.util.Hashtable;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.Base;
import pages.LeavePage;

public class VaildLeave extends Base {
	
	LeavePage leavepage;
	
	SoftAssert softAssert=new SoftAssert();
	
	
	@Test(dataProvider="getdata")
	public void loginvalid(Hashtable<String, String> data) {
		
		var loginPage = loginpage.login(data.get("UserName"),data.get("Password"));
		leavepage=loginPage.clickLeave();
	}
	
	
	@Test(dataProvider="getdata")
	public void testAdminAssignLeaveForSpecificEmployee(Hashtable<String, String> data) {
		leavepage.clickOnEntitlements();
		leavepage.addEntitlements("Shweta","CAN - Bereavement","2023-01-01 - 2023-12-31","10");
		softAssert.assertEquals(leavepage.getConfrimationMessageAlert(),"Updating Entitlement","Faill to add leaves");
		leavepage.clickOnConfrimButton();
		softAssert.assertAll();
	}
	
	

	@Test(dependsOnMethods={"loginvalid"})
	public void testAdminAllocateLeaveForMultipleEmployees() {
		leavepage.clickOnEntitlements();
		leavepage.addEntitlementsForMultipleEmployees("Human Resources","US - Personal","2023-01-01 - 2023-12-31","10");
		softAssert.assertEquals(leavepage.getConfrimationMessageAlert(),"Updating Entitlement - Matching Employees","Faill to add leaves");
		leavepage.clickOnConfrimButton();
		softAssert.assertAll();
	}
	
	
	
	
	

}
