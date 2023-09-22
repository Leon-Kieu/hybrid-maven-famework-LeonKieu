package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.ComparePageUI;

public class UserComparePageObject extends BasePage{
	private WebDriver driver;

	public UserComparePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getSizeByClassname(String className) {
		waitForElementVisible(driver, ComparePageUI.SIZE__PRODUCT_COMPARE_BY_CLASS, className);
		return getElementSize(driver, ComparePageUI.SIZE__PRODUCT_COMPARE_BY_CLASS, className);
	}

	public void clickClearlist() {
		waitForElementClickable(driver, ComparePageUI.CLEAR_BUTTON);
		clickToElement(driver, ComparePageUI.CLEAR_BUTTON);
		
	}

	public String getBodyMessage() {
		waitForElementVisible(driver, ComparePageUI.BODY_MESSAGE);
		return getElementText(driver, ComparePageUI.BODY_MESSAGE);
	}


	
	
}
