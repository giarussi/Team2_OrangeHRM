package loginTest;

import org.testng.annotations.Test;

import base.Base;
import pages.DashboardPage;
import pages.PIMPage;

public class PIMTest extends Base {
	PIMPage pimPage;
	DashboardPage dashboardPage;
	
	
@Test
public void Add_Emp_01() throws InterruptedException {
	//pimPage.test();
	var loginPage = loginpage.login("admin", "admin123");
	Thread.sleep(4000);
	pimPage = loginPage.clickPIM();
	//dashBoardPage.clickPIM();
	Thread.sleep(4000);
	pimPage.createEmployee("", "", "" );
	
}
}
