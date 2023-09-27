package utility;

import java.lang.reflect.Method;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;


public class EventReporter implements WebDriverListener {
	
	
	 // Navigation
	
	public void afterAnyNavigationCall(WebDriver.Navigation navigation, Method method, Object[] args, Object result) {		
	System.out.println("Navigation call: "+method.getName());
		
	}
	
	 public void beforeTo(WebDriver.Navigation navigation, String url) {
		 System.out.println("Before navigating to: " + url);
	 }
	 
	 public void afterTo(WebDriver.Navigation navigation, String url) {	 
		 System.out.println("After navigating to: " + url);
		 
	 }
	 

	 // WebElement
	 public void beforeClick(WebElement element) {
		 System.out.println("Before clicking on element: " + element.getAccessibleName());
	 }
	 
	 public void afterClick(WebElement element) {
			System.out.println("Clicked: " + element);
		}
	 
	 public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
         System.out.println("After sending keys to element: " + element.getText());  

	 }
	 
	 
	// Alert
	 
	 public void beforeAccept(Alert alert) {
         System.out.println("Before accepting alert: " + alert.getText());
     }
	 
	 public void afterAccept(Alert alert){
		 System.out.println("Alert accepted");
		 
	 }
	 
	 public void beforeAnyAlertCall(Alert alert, Method method, Object[] args) {
         System.out.println("Before any alert call - Method: " + method.getName());
     }
	 
	 
	 
	 // Option
	 
	 public void beforeAddCookie(WebDriver.Options options, Cookie cookie) {
         System.out.println("Before adding cookie: " + cookie.getName() + "=" + cookie.getValue());
     }
	 
	 
	 public void afterGetTitle(WebDriver driver, String result) {
         System.out.println("Page title retrieved: " + result);
     }
	 
	 public void afterQuit(WebDriver driver) {
			System.out.println("Browser closed.");
		}



}
