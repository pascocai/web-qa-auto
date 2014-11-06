package pasco.cai.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperHandleAlert extends Operation {

	public OperHandleAlert(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public void run() {
		WebDriverWait wait = new WebDriverWait(webDriver, defaultTimeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        alert.accept();
	}
}
