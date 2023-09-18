package commons;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String osName = System.getProperty("os.name");

	protected final Log log;

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}



	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--headless");
			co.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(co);
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions option = new FirefoxOptions();
			option.addPreference("pdfjs.disabled", true);
			option.setProfile(profile);
			driver = new FirefoxDriver(option);
		} else if (browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions fo = new FirefoxOptions();
			fo.addArguments("--headless");
			fo.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(fo);
		} else if (browserName.equals("ie")) {
			// should use 32bit for quickly
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equals("brave")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(options);
		} else {
			throw new RuntimeException("Invalid browser name");
		}

		driver.manage().timeouts().implicitlyWait(GlobalContants.SHORT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}
	
	protected String getEnvironmentUrl(String environmentName) {
		String enUrl = null;
		EnvironmentList environment = EnvironmentList.valueOf(environmentName.toUpperCase());
		
		switch (environment) {
		case DEV:
			enUrl ="https://demo.nopcommerce.com/";
			break;
		case TESTING:
			enUrl ="https://admin-demo.nopcommerce.com/";
			break;
		case STAGING:
			enUrl ="https://www.facebook.com/";
			break;
		case PRODUCTOIN:
			enUrl ="https://www.youtube.com/";
			break;
		default:
			enUrl = null;
			break;
		}
		
		/*
		 * if (environment == EnvironmentList.DEV) { enUrl
		 * ="https://demo.nopcommerce.com/"; } else if (environment ==
		 * EnvironmentList.TESTING){ enUrl ="https://admin-demo.nopcommerce.com/"; }
		 * else if (environment == EnvironmentList.STAGING){ enUrl
		 * ="https://www.facebook.com/"; } else if (environment ==
		 * EnvironmentList.PRODUCTOIN) { enUrl ="https://www.youtube.com/"; }
		 */
		return enUrl;
	}
	
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected void showBrowserConsoleLogs (WebDriver driver) {
		if (driver.toString().contains("chrome") || driver.toString().contains("edge")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				if (logging.getLevel().toString().toLowerCase().contains("error")) {
					log.info("--------------"+logging.getLevel().toString()+"----------------"+logging.getMessage());
				}
			}
		}
	}

}
