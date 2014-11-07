package pasco.cai.qa.verification;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerifyAlert extends Verification {

	public VerifyAlert(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public String run(String fieldValue) {
		WebDriverWait wait = new WebDriverWait(webDriver, defaultTimeOut);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        //System.out.println(alert.getText());
        if(fieldValue=="")
			return null;
		if(!alert.getText().equals(fieldValue))
			return alert.getText();
		else
			return "";
	}
}
