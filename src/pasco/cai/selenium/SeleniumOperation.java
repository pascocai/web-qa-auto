package pasco.cai.selenium;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
			File file = new File("D:\\selenium-webdriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			WebDriver driver = new ChromeDriver();
			return driver;
		} else if(browserType==2){	// firefox
			File pathToBinary = new File("C:\\Documents and Settings\\pascocai\\Local Settings\\Application Data\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			File profileDirectory = new File("D:\\selenium-webdriver\\ffwebdriver");
			FirefoxProfile profile = new FirefoxProfile(profileDirectory);
			WebDriver driver = new FirefoxDriver(ffBinary, profile);
			return driver;
		} else if(browserType==3){	// ie
			File file = new File("D:\\selenium-webdriver\\IEDriverServer.exe");
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
				oper = new OperOpenUrl(webDriver, browserType, defaultTimeOut);
				OperOpenUrl oou = (OperOpenUrl) oper;
				String cookieKey = "";
				String cookieValue = "";
				if(!fieldValue.equals("")){
					cookieKey = fieldValue.substring(0, fieldValue.lastIndexOf(":"));
					cookieValue = fieldValue.substring(fieldValue.lastIndexOf(":")+1);
				}
				oou.run(fieldName, cookieKey, cookieValue);
				break;
			case 1:
				oper = new OperClick(webDriver, browserType, defaultTimeOut);
				OperClick oc = (OperClick) oper;
				oc.run(fieldName);
				break;
			case 2:
				oper = new OperInputText(webDriver, browserType, defaultTimeOut);
				OperInputText oit = (OperInputText) oper;
				oit.run(fieldName, fieldValue);
				break;
			case 3:
				oper = new OperCheckBox(webDriver, browserType, defaultTimeOut);
				OperCheckBox ocb = (OperCheckBox) oper;
				ocb.run(fieldName, fieldValue);
				break;
			case 4:
				oper = new OperUploadFile(webDriver, browserType, defaultTimeOut);
				OperUploadFile ouf = (OperUploadFile) oper;
				ouf.run(fieldName, fieldValue, defaultTimeOut);
				break;
			case 9:
				oper = new OperHandleAlert(webDriver, browserType, defaultTimeOut);
				OperHandleAlert oha = (OperHandleAlert) oper;
				oha.run();
				break;
			case 10:
				oper = new OperSubmitForm(webDriver, browserType, defaultTimeOut);
				OperSubmitForm osf = (OperSubmitForm) oper;
				osf.run(fieldName);
				break;
		}
	}
}
