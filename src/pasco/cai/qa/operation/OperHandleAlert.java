package pasco.cai.qa.operation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OperHandleAlert extends Operation {

	public OperHandleAlert(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public void run(String action) {
		WebDriverWait wait = new WebDriverWait(webDriver, defaultTimeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        if(Integer.parseInt(action)==1) {	// confirm
        	alert.accept();
		} else { // cancel
			alert.dismiss();
		}
	}
}
