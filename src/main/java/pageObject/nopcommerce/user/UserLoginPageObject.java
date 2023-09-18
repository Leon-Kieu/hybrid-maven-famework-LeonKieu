package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.LoginPageUserUI;

public class UserLoginPageObject extends BasePage {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUserUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUserUI.LOGIN_BUTTON);
	}

	public String getErrorMessageLogin() {
		waitForElementVisible(driver, LoginPageUserUI.LOGIN_UNREGISTERED_EMAIL_MESSAGE);
		return getElementText(driver, LoginPageUserUI.LOGIN_UNREGISTERED_EMAIL_MESSAGE);
	}
	
	
}
