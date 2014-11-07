package pasco.cai.qa.verification;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyText extends Verification {

	private WebElement element = null;

	public VerifyText(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public String run(String locator, String fieldValue) {
		element = (new WebDriverWait(webDriver, defaultTimeOut)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		if(fieldValue=="")
			return null;
		if(!element.getText().equals(fieldValue))
			return element.getText();
		else
			return "";
	}
}
