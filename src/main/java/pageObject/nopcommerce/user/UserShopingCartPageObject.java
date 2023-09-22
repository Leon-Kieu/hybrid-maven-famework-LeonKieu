package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.user.ShopingCartUI;
import pageUI.nopcommerce.user.WishlistPageUI;

public class UserShopingCartPageObject extends BasePage{
	private WebDriver driver;

	public UserShopingCartPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public int getSizeShopingCart() {
		waitForElementVisible(driver, ShopingCartUI.LIST_PRODUCT_SHOPING_CART);
		return getElementSize(driver, ShopingCartUI.LIST_PRODUCT_SHOPING_CART);
	}

	public String getQuantityWishlist() {
		waitForElementVisible(driver, ShopingCartUI.QUALITY_WISHLIST);
		return getElementText(driver, ShopingCartUI.QUALITY_WISHLIST);
	}
}
