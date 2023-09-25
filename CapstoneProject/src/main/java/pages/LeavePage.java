package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeavePage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	public LeavePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait=wait;
	}

}
