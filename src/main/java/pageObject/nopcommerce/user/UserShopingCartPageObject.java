package pageObject.nopcommerce.user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.GlobalContants;
import commons.PageGeneratorManager;
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

	public UserProductDetailPageObject clickEditLink() {
		waitForElementClickable(driver, ShopingCartUI.EDIT_LINK_CART);
		clickToElement(driver, ShopingCartUI.EDIT_LINK_CART);
		sleepInsecond(2);
		return PageGeneratorManager.getUserProductDetailPage(driver);
	}

	public void clickRemoveLink() {
		waitForElementClickable(driver, ShopingCartUI.REMOVE_LINK_CART);
		clickToElement(driver,ShopingCartUI.REMOVE_LINK_CART);
	}
	
	
	public String getMessageEmtyCart() {
		waitForElementVisible(driver, ShopingCartUI.EMPTY_CART_MESSAGE);
		return getElementText(driver, ShopingCartUI.EMPTY_CART_MESSAGE);
	}

	public String getTotalPrice() {
		waitForElementVisible(driver, ShopingCartUI.TOTAL_PRICE);
		return getElementText(driver, ShopingCartUI.TOTAL_PRICE);
	}

	public void clickButtonApply() {
		waitForElementClickable(driver, ShopingCartUI.APPLY_BUTTON_POPUP);
		clickToElement(driver,ShopingCartUI.APPLY_BUTTON_POPUP);
	}

	public void clickContinueButton() {
		waitForElementClickable(driver, ShopingCartUI.CONTINUE_BUTTON_ADDRESS);
		clickToElement(driver,ShopingCartUI.CONTINUE_BUTTON_ADDRESS);
		
	}

	public void clickContinueShipingButton() {
		waitForElementClickable(driver, ShopingCartUI.CONTINUE_BUTTON_SHIPING);
		clickToElement(driver,ShopingCartUI.CONTINUE_BUTTON_SHIPING);
	}

	public void clickContinuePaymentButton() {
		waitForElementClickable(driver, ShopingCartUI.CONTINUE_BUTTON_PAYMENT);
		clickToElement(driver,ShopingCartUI.CONTINUE_BUTTON_PAYMENT);
	}

	public void clickContinuePaymentInfoButton() {
		waitForElementClickable(driver, ShopingCartUI.CONTINUE_BUTTON_PAYMENT_INFO);
		clickToElement(driver,ShopingCartUI.CONTINUE_BUTTON_PAYMENT_INFO);
	}

	public Object getTextConfirmAddressByClass(String className) {
		waitForElementVisible(driver, ShopingCartUI.DYNAMIC_CONFIRM_ODER_ADDRESS_BY_CLASS, className);
		return getElementText(driver, ShopingCartUI.DYNAMIC_CONFIRM_ODER_ADDRESS_BY_CLASS, className);
	}

	public String getTextConfirmBillingByClass(String className) {
		waitForElementVisible(driver, ShopingCartUI.DYNAMIC_CONFIRM_BILLING_ADDRESS_BY_CLASS, className);
		return getElementText(driver, ShopingCartUI.DYNAMIC_CONFIRM_BILLING_ADDRESS_BY_CLASS, className);
	}

	public String getProductNameOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.NAME_CONFIRM_ODER);
		return getElementText(driver, ShopingCartUI.NAME_CONFIRM_ODER);
	}

	public String getProductQuanityOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.QUANITY_CONFIRM_ODER);
		return getElementText(driver, ShopingCartUI.QUANITY_CONFIRM_ODER);
	}

	public String getProductTotalOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.TOTAL_CONFIRM_ODER);
		return getElementText(driver, ShopingCartUI.TOTAL_CONFIRM_ODER);
	}

	public String getGiftWrapingOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.GIFT_WRAPPING);
		return getElementText(driver, ShopingCartUI.GIFT_WRAPPING);
	}

	public String getSubTotalOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.SUB_TOTAL);
		return getElementText(driver, ShopingCartUI.SUB_TOTAL);
	}

	public String getShippingOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.SHIPPING);
		return getElementText(driver, ShopingCartUI.SHIPPING);
	}

	public String getTaxOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.TAX);
		return getElementText(driver, ShopingCartUI.TAX);
	}

	public String getTotalOderConfirm() {
		waitForElementVisible(driver, ShopingCartUI.TOTAL);
		return getElementText(driver, ShopingCartUI.TOTAL);
	}

	public void clickConfirmButton() {
		waitForElementClickable(driver, ShopingCartUI.CONFIRM_BUTTON);
		clickToElement(driver, ShopingCartUI.CONFIRM_BUTTON);
	}

	public String getOrderSuccessMsg() {
		waitForElementVisible(driver, ShopingCartUI.ORDER_SUCCESS_MSG);
		return getElementText(driver, ShopingCartUI.ORDER_SUCCESS_MSG);
	}

	public void saveOrderNumber() throws IOException {
		String order = getElementText(driver, ShopingCartUI.ORDER_NUMBER).substring(14);
		FileOutputStream orderNumber = new FileOutputStream("C:\\Fullstack Selenium Java\\01 - Software\\eclipse-java-2021-12-R-win32-x86_64\\workspace\\hybrid-maven-famework-LeonKieu\\data\\orderNumber.txt");
		DataOutputStream dos = new DataOutputStream(orderNumber);
		dos.writeBytes(order);
	}

	public UserHomePageObject clickHome() {
		waitForElementClickable(driver, ShopingCartUI.HOME_ICON);
		clickToElement(driver, ShopingCartUI.HOME_ICON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void inputToTextboxQty(String qty) {
		waitForElementVisible(driver, ShopingCartUI.INPUT_QTY_RPODUCT);
		sendkeyToElement(driver,  ShopingCartUI.INPUT_QTY_RPODUCT, qty);
	}

	
	
	
	
	
}
