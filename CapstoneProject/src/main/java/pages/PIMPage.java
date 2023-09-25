package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PIMPage {
	
	private WebDriver driver;
	private WebDriverWait wait;

	public PIMPage(WebDriver driver,WebDriverWait wait) {
		this.driver = driver;
		this.wait=wait;
	}

}
