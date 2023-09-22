package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.ProductPageUI;
import pageUI.nopcommerce.user.RecentlyPageUI;

public class UserRecentlyPageObject extends BasePage{
	private WebDriver driver;

	public UserRecentlyPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getSizeRecently() {
		waitForElementVisible(driver, RecentlyPageUI.LIST_PRODUCT_NAME);
		return getElementSize(driver, RecentlyPageUI.LIST_PRODUCT_NAME);
	}


	
}
