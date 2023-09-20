package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.MyProductReviewPageUI;

public class UserMyproductReviewPageObject extends BasePage{
	private WebDriver driver;

	public UserMyproductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTextReviewTitle() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TITLE);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TITLE);
	}

	public String getTextReviewText() {
		waitForElementVisible(driver, MyProductReviewPageUI.REVIEW_TEXT);
		return getElementText(driver, MyProductReviewPageUI.REVIEW_TEXT);
	}
	
}
