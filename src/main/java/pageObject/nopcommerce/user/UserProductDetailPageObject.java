package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import freemarker.core.ReturnInstruction.Return;
import pageUI.nopcommerce.user.ProductDetailPageUI;

public class UserProductDetailPageObject extends BasePage{
	private WebDriver driver;

	public UserProductDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserProductReviewPageObject clickAddYourReview() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		clickToElement(driver, ProductDetailPageUI.ADD_YOUR_REVIEW_LINK);
		return PageGeneratorManager.getUserProductReviewPage(driver);
	}

	public UserWishlistOageObject clickWishlistLink() {
		waitForElementClickable(driver, ProductDetailPageUI.WISHLIST_LINK);
		clickToElementByJS(driver, ProductDetailPageUI.WISHLIST_LINK);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}
	
}
