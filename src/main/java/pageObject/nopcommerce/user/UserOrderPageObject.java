package pageObject.nopcommerce.user;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.OrderPageUI;

public class UserOrderPageObject extends BasePage{
	private WebDriver driver;

	public UserOrderPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getOrderNumberfile() throws IOException {
		   FileInputStream fis = new FileInputStream("C:\\\\Fullstack Selenium Java\\\\01 - Software\\\\eclipse-java-2021-12-R-win32-x86_64\\\\workspace\\\\hybrid-maven-famework-LeonKieu\\\\data\\\\orderNumber.txt");
		   DataInputStream dis = new DataInputStream(fis);
		   String orderNumberString = dis.readLine();
		return orderNumberString;
	}
	
	public String getOrderNumberWeb(String OrderNumber) {
		waitForElementVisible(driver,OrderPageUI.DYNAMIC_ORDER_NUMBER, OrderNumber);
		return getElementText(driver,OrderPageUI.DYNAMIC_ORDER_NUMBER, OrderNumber);
	}

	public UserOrderDetailPageObject clickDetailPageByOrder(String orderNumberfile) {
		waitForElementClickable(driver,OrderPageUI.DYNAMIC_ORDER_DETAIL, orderNumberfile);
		clickToElement(driver, OrderPageUI.DYNAMIC_ORDER_DETAIL, orderNumberfile);
		return PageGeneratorManager.getUserOrderDetailPage(driver);
	}


	
}
