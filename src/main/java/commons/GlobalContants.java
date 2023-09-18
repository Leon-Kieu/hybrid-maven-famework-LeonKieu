package commons;

import java.io.File;

import org.apache.hc.core5.http.message.StatusLine.StatusClass;

public class GlobalContants {
	public static final String USER_PAGE ="https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE ="https://admin-demo.nopcommerce.com/login";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles"+ File.separator;
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT = PROJECT_PATH + File.separator + "AutoIT";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots"+ File.separator;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "extentReport"+ File.separator;
	
	public static final String DB_DEV_URL =" ";
	public static final String DB_DEV_USER =" ";
	public static final String DB_DEV_PASS =" ";
	
	public static final String DB_TEST_URL =" ";
	public static final String DB_TEST_USER =" ";
	public static final String DB_TEST_PASS =" ";
	
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEXT_FAIL = 3;
	
}
