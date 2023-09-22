package pageObject.nopcommerce.user;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.WishlistPageUI;

public class UserWishlistOageObject extends BasePage{
	private WebDriver driver;

	public UserWishlistOageObject(WebDriver driver) {
		this.driver = driver;
	}

	public int getSizeWishlist() {
		waitForElementVisible(driver, WishlistPageUI.LIST_PRODUCT_WISHLIST);
		return getElementSize(driver, WishlistPageUI.LIST_PRODUCT_WISHLIST);
	}

	public void clickLinkShareWishlist() {
		waitForElementClickable(driver, WishlistPageUI.LINK_WISHLIST);
		clickToElement(driver, WishlistPageUI.LINK_WISHLIST);
	}

	public String getTextShareTitle() {
		waitForElementVisible(driver, WishlistPageUI.SHARE_WISHLIST_TITLE);
		return getElementText(driver, WishlistPageUI.SHARE_WISHLIST_TITLE);
	}

	public void clickCheckBoxAddTocartByName(String productName) {
		waitForElementClickable(driver, WishlistPageUI.DYNAMIC_CHECKBOX_ADDTOCART_BY_NAME, productName);
		clickToElement(driver, WishlistPageUI.DYNAMIC_CHECKBOX_ADDTOCART_BY_NAME, productName);
	}

	public UserShopingCartPageObject clickButtonAddtocart() {
		waitForElementClickable(driver, WishlistPageUI.BUTTON_ADDTOCART_WISHLIST);
		clickToElement(driver, WishlistPageUI.BUTTON_ADDTOCART_WISHLIST);
		return PageGeneratorManager.getUserShopingCartPage(driver);
	}

	public UserHomePageObject clickHome() {
		waitForElementClickable(driver, WishlistPageUI.HOME_IMG);
		clickToElement(driver, WishlistPageUI.HOME_IMG);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void clickRemove() {
		waitForElementClickable(driver, WishlistPageUI.REMOVE_BUTTON);
		clickToElement(driver, WishlistPageUI.REMOVE_BUTTON);
	}

	public String getWishlistEmptymessage() {
		waitForElementVisible(driver, WishlistPageUI.WISLIST_EMPTY);
		return getElementText(driver, WishlistPageUI.WISLIST_EMPTY);
	}


	
}
