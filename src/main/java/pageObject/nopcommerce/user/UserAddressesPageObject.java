package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.AddressesPageUI;

public class UserAddressesPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickButtonAddNew() {
		waitForElementClickable(driver, AddressesPageUI.BUTTON_ADD_NEW);
		clickToElement(driver, AddressesPageUI.BUTTON_ADD_NEW);
	}
	
}
