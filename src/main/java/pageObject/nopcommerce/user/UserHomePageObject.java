package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.HomePageUserUI;
import pageUI.nopcommerce.user.ProductDetailPageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUserUI.REGISTER_LINK);
		clickToElement(driver, HomePageUserUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUserUI.LOGIN_LINK);
		clickToElement(driver, HomePageUserUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);

	}

	public boolean isMyAcountLinkDisplay() {
		waitForElementVisible(driver, HomePageUserUI.MY_ACCOUNT_LINK);
		return isElementisDisplayed(driver, HomePageUserUI.MY_ACCOUNT_LINK);
	}

	public UserWishlistOageObject clickWishListLink() {
		waitForElementClickable(driver, HomePageUserUI.WISHLIST_LINK);
		clickToElementByJS(driver, HomePageUserUI.WISHLIST_LINK);
		return PageGeneratorManager.getUserWishlistPage(driver);
	}





	

}
