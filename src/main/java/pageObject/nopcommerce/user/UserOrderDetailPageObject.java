package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.OrderDetailPageUI;
import pageUI.nopcommerce.user.ShopingCartUI;

public class UserOrderDetailPageObject extends BasePage{
	private WebDriver driver;

	public UserOrderDetailPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getTextConfirmBillingByClass(String className) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_CONFIRM_BILLING_ADDRESS_BY_CLASS, className);
		return getElementText(driver, OrderDetailPageUI.DYNAMIC_CONFIRM_BILLING_ADDRESS_BY_CLASS, className);
	}

	public String getTextConfirmAddressByClass(String className) {
		waitForElementVisible(driver, OrderDetailPageUI.DYNAMIC_CONFIRM_ODERDETAIL_ADDRESS_BY_CLASS, className);
		return getElementText(driver, OrderDetailPageUI.DYNAMIC_CONFIRM_ODERDETAIL_ADDRESS_BY_CLASS, className);
	}

	public String getProductNameOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.NAME_DETAIL_ODER);
		return getElementText(driver, OrderDetailPageUI.NAME_DETAIL_ODER);
	}
	
	public String getProductQuanityOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.QUANITY_DETAIL_ODER);
		return getElementText(driver, OrderDetailPageUI.QUANITY_DETAIL_ODER);
	}

	public String getProductTotalOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.TOTAL_DETAIL_PRODUCT);
		return getElementText(driver, OrderDetailPageUI.TOTAL_DETAIL_PRODUCT);
	}

	public String getGiftWrapingOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.GIFT_WRAPPING_DETAIL);
		return getElementText(driver, OrderDetailPageUI.GIFT_WRAPPING_DETAIL);
	}

	public String getSubTotalOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.SUB_TOTAL_DETAIL);
		return getElementText(driver, OrderDetailPageUI.SUB_TOTAL_DETAIL);
	}

	public String getShippingOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.SHIPPING_DETAIL);
		return getElementText(driver, OrderDetailPageUI.SHIPPING_DETAIL);
	}

	public String getTaxOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.TAX_DETAIL);
		return getElementText(driver, OrderDetailPageUI.TAX_DETAIL);
	}

	public String getTotalOderDetail() {
		waitForElementVisible(driver, OrderDetailPageUI.TOTAL_DETAIL);
		return getElementText(driver, OrderDetailPageUI.TOTAL_DETAIL);
	}

	public UserHomePageObject clickHome() {
		waitForElementClickable(driver, OrderDetailPageUI.HOME_ICON);
		clickToElement(driver, OrderDetailPageUI.HOME_ICON);
		return PageGeneratorManager.getUserHomePage(driver);
	}


	
	
}
