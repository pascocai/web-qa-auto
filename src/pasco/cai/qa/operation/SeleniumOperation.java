package pasco.cai.qa.operation;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pasco.cai.qa.verification.VerifyAlert;
import pasco.cai.qa.verification.VerifyElementExist;
import pasco.cai.qa.verification.VerifyText;

public class SeleniumOperation {
	
	private WebDriver webDriver = null;
	private int browserType = 1;
	private int defaultTimeOut = 30;
	
	OperOpenUrl oou = null;
	OperClick oc = null;
	OperInputText oit = null;
	OperCheckBox ocb = null;
	OperUploadFile ouf = null;
	OperHandleAlert oha = null;
	OperSubmitForm osf = null;
	
	VerifyText vt = null;
	VerifyElementExist vee = null;
	VerifyAlert va = null;
	
	public SeleniumOperation(int type, int timeout) {
		webDriver = setBrowser(type);
		browserType = type;
		defaultTimeOut = timeout;
	}

	public void QuitDriver() {
		webDriver.quit();
	}

	public WebDriver setBrowser(int browserType) {	// chrome
		if(browserType==1){
			File file = new File("D:\\selenium-webdriver\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			return new ChromeDriver();
		} else if(browserType==2){	// firefox
			File pathToBinary = new File("C:\\Documents and Settings\\pascocai\\Local Settings\\Application Data\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
			File profileDirectory = new File("D:\\selenium-webdriver\\ffwebdriver");
			FirefoxProfile profile = new FirefoxProfile(profileDirectory);
			return  new FirefoxDriver(ffBinary, profile);
		} else if(browserType==3){	// ie
			File file = new File("D:\\selenium-webdriver\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			WebDriver driver = new InternetExplorerDriver();
			return driver;
		} else {
			return new ChromeDriver();
		}	
	}
	
	public String run(int fieldType, String fieldName, String fieldValue) {
		String returnStr = null;
		String str = null;
		switch(fieldType) {
			case 0:
				if(oou==null)
					oou = new OperOpenUrl(webDriver, browserType, defaultTimeOut);
				String cookieKey = "";
				String cookieValue = "";
				if(!fieldValue.equals("")){
					cookieKey = fieldValue.substring(0, fieldValue.lastIndexOf(":"));
					cookieValue = fieldValue.substring(fieldValue.lastIndexOf(":")+1);
				}
				oou.run(fieldName, cookieKey, cookieValue);
				break;
			case 1:
				if(oc==null)
					oc = new OperClick(webDriver, browserType, defaultTimeOut);
				oc.run(fieldName);
				break;
			case 2:
				if(oit==null)
					oit = new OperInputText(webDriver, browserType, defaultTimeOut);
				oit.run(fieldName, fieldValue);
				break;
			case 3:
				if(ocb==null)
					ocb = new OperCheckBox(webDriver, browserType, defaultTimeOut);
				ocb.run(fieldName, fieldValue);
				break;
			case 4:
				if(ouf==null)
					ouf = new OperUploadFile(webDriver, browserType, defaultTimeOut);
				ouf.run(fieldName, fieldValue);
				break;
			case 9:
				if(oha==null)
					oha = new OperHandleAlert(webDriver, browserType, defaultTimeOut);
				oha.run(fieldValue);
				break;
			case 10:
				if(osf==null)
					osf = new OperSubmitForm(webDriver, browserType, defaultTimeOut);
				osf.run(fieldName);
				break;
			case 21:
				if(vt==null)
					vt = new VerifyText(webDriver, browserType, defaultTimeOut);
				str = vt.run(fieldName, fieldValue);
				/*
				 *  如果返回為null，表示沒有判斷條件，不作判斷
				 *  如果返回為空字符串，表示判斷ok
				 *  如果返回非空字符串，表示判斷fail，返回實際獲得的text
				 */
				if(str!=null){
					if(str!="")
						returnStr = "expected text:<"+fieldValue+"> but was:<"+str+">";
					else
						returnStr = "";
				}
				break;
			case 22:
				if(vee==null)
					vee = new VerifyElementExist(webDriver, browserType, defaultTimeOut);
				str = vee.run(fieldName);
				/*
				 *  如果返回為null，表示沒有判斷條件，不作判斷
				 *  如果返回為空字符串，表示判斷ok
				 *  如果返回非空字符串，表示判斷fail，返回實際獲得的text
				 */
				if(str!=null){
					if(str!="")
						returnStr = "no such element: <"+str+">";
					else
						returnStr = "";
				}
				break;
			case 23:
				if(va==null)
					va = new VerifyAlert(webDriver, browserType, defaultTimeOut);
				str = va.run(fieldValue);
				/*
				 *  如果返回為null，表示沒有判斷條件，不作判斷
				 *  如果返回為空字符串，表示判斷ok
				 *  如果返回非空字符串，表示判斷fail，返回實際獲得的text
				 */
				if(str!=null){
					if(str!="")
						returnStr = "expected alert:<"+fieldValue+"> but was:<"+str+">";
					else
						returnStr = "";
				}
				break;
		}
		return returnStr;
	}
}
