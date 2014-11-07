package pasco.cai.qa.operation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperClick extends Operation {

	private WebElement element = null;

	public OperClick(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public void run(String locator) {
		element = (new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		element.click();
	}
}
