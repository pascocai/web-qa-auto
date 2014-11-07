package pasco.cai.qa.verification;

import org.openqa.selenium.WebDriver;

public class Verification {
	
	WebDriver webDriver = null;
	int browserType = 1;
	int defaultTimeOut = 30;
	
	public Verification(WebDriver driver, int type, int timeout) {
		webDriver = driver;
		browserType = type;
		defaultTimeOut = timeout;
	}
}
