package loginTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;



public class LoginTest extends Base {
	
	 
	 @Test
	    public void validLoginTest() {
		 var loginPage = loginpage.login("admin", "admin123");
	        
	        
	        // Assert that the dashboard element is displayed after valid login
	        Assert.assertTrue(loginPage.isDashboardElementDisplayed(), "Dashboard element is not displayed after valid login.");
	    }

	    @Test
	    public void invalidLoginTest() {
	    	var loginPage = loginpage.login("invalidUsername", "invalidPassword");
	        
	        
	        // Assert that the error message is displayed for an invalid login
	        String expectedErrorMessage = "Invalid credentials";
	        String actualErrorMessage = loginPage.getErrorMessage();
	        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message mismatch for invalid login.");
	    }

	

}
