package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperSubmitForm extends Operation {

	public WebElement element = null;
	
	public OperSubmitForm(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}
	
	public void run(String locator) {
		element = (new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		element.submit();
	}
}
