package loginTest;

import org.testng.annotations.Test;

import base.Base;



public class LoginTest extends Base {
	
	 @Test(dataProvider="getData")

	    public void validLoginTest() throws InterruptedException {
		 	var loginPage = loginpage.login("admin", "admin123");
		 	loginPage.clickPIM();
            System.out.println("This is Login Test");        
	    }

	

}
