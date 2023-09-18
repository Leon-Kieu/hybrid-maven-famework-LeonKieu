package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.RegisterPageUserUI;

public class UserRegisterPageObject extends BasePage {
	private WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getRegisterSuccessMessage() {
		waitForAllElementVisible(driver, RegisterPageUserUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUserUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getEmailAlreadyExistsMessage() {
		waitForAllElementVisible(driver, RegisterPageUserUI.EMAIL_ALREADY_EXISTS_MESSAGE);
		return getElementText(driver, RegisterPageUserUI.EMAIL_ALREADY_EXISTS_MESSAGE);
	}

	public String getPasswordLess6CharactersMessage() {
		waitForAllElementVisible(driver, RegisterPageUserUI.PASSWORD_MESSAGE_LESS_6_CHARACTERS);
		return getElementText(driver, RegisterPageUserUI.PASSWORD_MESSAGE_LESS_6_CHARACTERS );
	}

	public String getMessagePassAndConfirmPassNotMath() {
		waitForAllElementVisible(driver, RegisterPageUserUI.PASS_AND_CONFIRMPASS_NOT_MATH_MESSAGE);
		return getElementText(driver, RegisterPageUserUI.PASS_AND_CONFIRMPASS_NOT_MATH_MESSAGE );
	}




}
