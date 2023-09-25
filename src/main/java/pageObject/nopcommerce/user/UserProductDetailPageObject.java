package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import freemarker.core.ReturnInstruction.Return;
import pageUI.nopcommerce.user.ProductDetailPageUI;
import pageUI.nopcommerce.user.ProductPageUI;

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
	
	public String getTextMessageBar() {
		waitForElementVisible(driver, ProductDetailPageUI.BAR_MESSAGE);
		return getElementText(driver, ProductDetailPageUI.BAR_MESSAGE);
	}

	public String getTitleMiniCart() {
		waitForElementVisible(driver, ProductDetailPageUI.TITLE_MINI_CART);
		return getElementText(driver, ProductDetailPageUI.TITLE_MINI_CART);
	}

	public boolean getInfoProductDisplay(String text) {
		waitForElementVisible(driver, ProductDetailPageUI.DYNAMIC_INFO_PRODUCT_MINI_CART,text);
		return isElementisDisplayed(driver, ProductDetailPageUI.DYNAMIC_INFO_PRODUCT_MINI_CART, text);
	}

	public String getPriceMiniCart() {
		waitForElementVisible(driver, ProductDetailPageUI.PRICE_MINI_CART);
		return getElementText(driver, ProductDetailPageUI.PRICE_MINI_CART);
	}

	public void hoverMiniCart() {
		waitForElementVisible(driver, ProductDetailPageUI.SHOPPING_CART_LINK);
		hoverMouseToElement(driver,  ProductDetailPageUI.SHOPPING_CART_LINK);
	}

	public void closeBarMessage() {
		waitForElementClickable(driver, ProductDetailPageUI.CLOSE_MASSAGE_BAR);
		clickToElement(driver, ProductDetailPageUI.CLOSE_MASSAGE_BAR);
	}

	public UserShopingCartPageObject clickGoToCartButton() {
		waitForElementClickable(driver, ProductDetailPageUI.GO_TO_CART_BUTTON);
		clickToElement(driver, ProductDetailPageUI.GO_TO_CART_BUTTON);
		return PageGeneratorManager.getUserShopingCartPage(driver);
	}


	
}
