package pasco.cai.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperUploadFile extends Operation {

	private WebElement element = null;

	public OperUploadFile(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public void run(String locator, String file, int defaultTimeOut) {
		element = (new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		element.sendKeys(file);
	}
}
