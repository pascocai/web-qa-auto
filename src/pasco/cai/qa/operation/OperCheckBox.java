package pasco.cai.qa.operation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperCheckBox extends Operation {

	public WebElement element = null;
	
	public OperCheckBox(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}
	
	public void run(String locator, String action) {
		element = (new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		if(Integer.parseInt(action)==1) {	// check it
			if(!element.isSelected()) {
				element.click();
			}
		} else { // uncheck it
			if(element.isSelected()) {
				element.click();
			}
		}
	}
}
