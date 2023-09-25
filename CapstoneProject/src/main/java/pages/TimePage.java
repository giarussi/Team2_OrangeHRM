package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimePage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	public TimePage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait=wait;
	}

}
