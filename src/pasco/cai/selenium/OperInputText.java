package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperInputText extends Operation {
	
	private int defaultTimeOut = 30;
	
	public OperInputText(int type, int timeout) {
		defaultTimeOut = timeout;
	}

	public WebElement element = null;
	
	public void run(String locator, String value) {
		//System.out.println(defaultTimeOut);
		element = (new WebDriverWait(driver, defaultTimeOut)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		//element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}
}
