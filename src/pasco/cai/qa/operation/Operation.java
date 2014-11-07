package pasco.cai.qa.operation;

import org.openqa.selenium.WebDriver;

public class Operation {
	
	WebDriver webDriver = null;
	int browserType = 1;
	int defaultTimeOut = 30;
	
	public Operation(WebDriver driver, int type, int timeout) {
		webDriver = driver;
		browserType = type;
		defaultTimeOut = timeout;
	}
}
