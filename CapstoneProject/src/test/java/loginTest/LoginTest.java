package loginTest;

import org.testng.annotations.Test;

import base.Base;



public class LoginTest extends Base {
	
	 @Test

	    public void validLoginTest() throws InterruptedException {
		 	var loginPage = loginpage.login("admin", "admin123");
		 	loginPage.clickPIM();

	        
	    }

	

}
