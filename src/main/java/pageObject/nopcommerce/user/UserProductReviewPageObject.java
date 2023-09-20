package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.ProductReviewPageUI;

public class UserProductReviewPageObject extends BasePage{
	private WebDriver driver;

	public UserProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSubmitReview() {
		waitForElementClickable(driver, ProductReviewPageUI.SUBMIT_REVEW_BUTTON);
		clickToElement(driver, ProductReviewPageUI.SUBMIT_REVEW_BUTTON);
	}

	public void inputToTextareaReview(String textReview) {
		waitForElementClickable(driver, ProductReviewPageUI.REVIEW_TEXTAREA);
		sendkeyToElement(driver, ProductReviewPageUI.REVIEW_TEXTAREA, textReview);
	}
	
}
