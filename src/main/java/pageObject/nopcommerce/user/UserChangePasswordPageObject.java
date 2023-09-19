package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.ChangePasswordPageUI;

public class UserChangePasswordPageObject extends BasePage{
	private WebDriver driver;

	public UserChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickChangePasswordButton() {
		waitForElementClickable(driver, ChangePasswordPageUI.CHANGE_PASS_BUTTON);
		clickToElement(driver, ChangePasswordPageUI.CHANGE_PASS_BUTTON);
	}

	public UserHomePageObject clickLogoutLink() {
		waitForElementClickable(driver, ChangePasswordPageUI.LOGOUT_LINK);
		clickToElementByJS(driver, ChangePasswordPageUI.LOGOUT_LINK);
		//clickToElement(driver, ChangePasswordPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	
	
	
	
}
