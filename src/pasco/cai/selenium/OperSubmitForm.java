package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperSubmitForm extends Operation {
	
	public OperSubmitForm(int type, int timeout) {
		defaultTimeOut = timeout;
	}

	public WebElement element = null;
	private int defaultTimeOut = 30;
	
	public void run(String locator) {
		//System.out.println(defaultTimeOut);
		element = (new WebDriverWait(driver, defaultTimeOut)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		element.submit();
	}
}
