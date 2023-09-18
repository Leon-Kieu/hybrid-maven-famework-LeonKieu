package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserAddressesPageObject clickAddressesLink() {
		waitForElementClickable(driver, CustomerInfoPageUI.ADDRESSES_LINK);
		clickToElement(driver, CustomerInfoPageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}
	
	
	
}
