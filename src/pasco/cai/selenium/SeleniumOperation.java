package pasco.cai.selenium;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SeleniumOperation {
	
	private WebDriver webDriver = null;
	private Operation oper = null;
	private int browserType = 1;
	private int defaultTimeOut = 30;
	
	public SeleniumOperation(int type, int timeout) {
		webDriver = setBrowser(type);
		browserType = type;
		defaultTimeOut = timeout;
	}

	public void QuitDriver() {
		webDriver.quit();
	}

	public WebDriver setBrowser(int browserType) {	// cherome
		if(browserType==1){
			File file = new File("D:\\selenium_driver_win32\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			WebDriver driver = new ChromeDriver();
			return driver;
		} else if(browserType==2){	// firefox
			File pathToBinary = new File("C:\\Documents and Settings\\pascocai\\Local Settings\\Application Data\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			//FirefoxProfile profile = new FirefoxProfile();
			FirefoxProfile profile = new ProfilesIni().getProfile("default");
			profile.setPreference("network.cookie.cookieBehavior", 2);
			WebDriver driver = new FirefoxDriver(ffBinary, profile);
			return driver;
		}  else if(browserType==3){	// ie
			File file = new File("D:\\selenium_driver_win32\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			WebDriver driver = new InternetExplorerDriver();
			return driver;
		} else {
			WebDriver driver = new ChromeDriver();
			return driver;
		}	
	}
	
	public void run(int fieldType, String fieldName, String fieldValue) {
		switch(fieldType) {
			case 0:
				oper = new OperOpenUrl(browserType);
				OperOpenUrl oou = (OperOpenUrl) oper;
				oou.driver = webDriver;
				String cookieKey = fieldValue.substring(0, fieldValue.lastIndexOf(":"));
				String cookieValue = fieldValue.substring(fieldValue.lastIndexOf(":")+1);
				oou.run(fieldName, cookieKey, cookieValue);
				break;
			case 1:
				oper = new OperClick(browserType, defaultTimeOut);
				OperClick oc = (OperClick) oper;
				oc.driver = webDriver;
				oc.run(fieldName);
				break;
			case 2:
				oper = new OperInputText(browserType, defaultTimeOut);
				OperInputText oit = (OperInputText) oper;
				oit.driver = webDriver;
				oit.run(fieldName, fieldValue);
				break;
			case 3:
				oper = new OperCheckBox(browserType, defaultTimeOut);
				OperCheckBox ocb = (OperCheckBox) oper;
				ocb.driver = webDriver;
				ocb.run(fieldName, fieldValue);
				break;
			case 10:
				oper = new OperSubmitForm(browserType, defaultTimeOut);
				OperSubmitForm osf = (OperSubmitForm) oper;
				osf.driver = webDriver;
				osf.run(fieldName);
				break;
		}
	}
}
