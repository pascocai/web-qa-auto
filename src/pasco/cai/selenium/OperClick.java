package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperClick extends Operation {
	
	private int defaultTimeOut = 30;
	
	public OperClick(int type, int timeout) {
		defaultTimeOut = timeout;
	}

	public WebElement element = null;
	
	public void run(String locator) {
		element = (new WebDriverWait(driver, defaultTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		element.click();
	}
}
