package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.AddressesPageUI;
import pageUI.nopcommerce.user.CustomerInfoPageUI;

public class UserAddressesPageObject extends BasePage {
	private WebDriver driver;

	public UserAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickButtonAddNew() {
		waitForElementClickable(driver, AddressesPageUI.BUTTON_ADD_NEW);
		clickToElement(driver, AddressesPageUI.BUTTON_ADD_NEW);
	}

	public void clickButtonSaveAddress() {
		waitForElementClickable(driver, AddressesPageUI.BUTTON_SAVE);
		clickToElement(driver, AddressesPageUI.BUTTON_SAVE);
	}

	public String getTextTitleAddress() {
		waitForElementVisible(driver, AddressesPageUI.ADDRESS_TITLE);
		return getElementText(driver, AddressesPageUI.ADDRESS_TITLE);
	}

	public String getTextInfoAddressByClass(String ClassName) {
		waitForElementVisible(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO, ClassName);
		return getElementText(driver, AddressesPageUI.DYNAMIC_ADDRESS_INFO, ClassName);
	}

	public UserChangePasswordPageObject clickChangePasswordLink() {
		waitForElementVisible(driver, AddressesPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, AddressesPageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}
	
	
	
}
