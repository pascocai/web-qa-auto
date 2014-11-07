package pasco.cai.qa.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyElementExist extends Verification {

	public VerifyElementExist(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public String run(String locator) {
		if(locator.equals(""))
			return null;
		try {
			(new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		} catch (Exception e) {
			return locator;
		}
		
		return "";
	}
}
