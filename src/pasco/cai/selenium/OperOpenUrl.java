package pasco.cai.selenium;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class OperOpenUrl extends Operation {

	public OperOpenUrl(WebDriver driver, int type, int timeout) {
		super(driver, type, timeout);
	}

	public void run(String url, String cookieKey,  String cookieValue) {
		webDriver.get(url);
		if(browserType==1){
			if(!cookieKey.equals(""))
				webDriver.manage().addCookie(new Cookie(cookieKey, cookieValue));
		} else if(browserType==3){
			if(!cookieKey.equals(""))
				((JavascriptExecutor) webDriver).executeScript("document.cookie='"+cookieKey+"="+cookieValue+"'");
		}
	}
}
