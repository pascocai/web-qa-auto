package pasco.cai.selenium;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;

public class OperOpenUrl extends Operation {
	
	private int browserType = 1;
	
	public OperOpenUrl(int type) {
		browserType = type;
	}

	public void run(String url, String cookieKey,  String cookieValue) {
		driver.get(url);
		if(browserType==1){
			if(cookieKey!=null)
				driver.manage().addCookie(new Cookie(cookieKey, cookieValue));
		} else if(browserType==3){System.out.println(cookieKey+" "+cookieValue);
			((JavascriptExecutor) driver).executeScript("document.cookie='"+cookieKey+"="+cookieValue+"'");
		}
	}
}
