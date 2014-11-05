package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperCheckBox extends Operation {
	
	private int defaultTimeOut = 30;
	
	public OperCheckBox(int type, int timeout) {
		defaultTimeOut = timeout;
	}

	public WebElement element = null;
	
	public void run(String locator, String action) {
		//System.out.println(defaultTimeOut);
		//element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		element = (new WebDriverWait(driver, defaultTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
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
