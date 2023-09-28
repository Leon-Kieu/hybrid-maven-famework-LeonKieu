package pageObject.nopcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopcommerce.admin.CatalogProductPageUI;

public class AdminCatalogProductsObject extends BasePage{
	private WebDriver driver;

	public AdminCatalogProductsObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getProductSearchName() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_NAME_SEARCH);
		return getElementText(driver, CatalogProductPageUI.PRODUCT_NAME_SEARCH);
	}

	public String getProductSearchSKU() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_SKU_SEARCH);
		return getElementText(driver, CatalogProductPageUI.PRODUCT_SKU_SEARCH);
	}

	public String getProductSearchQuantity() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_QUANTITY_SEARCH);
		return getElementText(driver, CatalogProductPageUI.PRODUCT_QUANTITY_SEARCH);
	}

	public String getProductSearchPrice() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_PRICE_SEARCH);
		return getElementText(driver, CatalogProductPageUI.PRODUCT_PRICE_SEARCH);
	}

	public boolean isPublishDisplayed() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_PUBLISHED_SEARCH);
		return isElementisDisplayed(driver, CatalogProductPageUI.PRODUCT_PUBLISHED_SEARCH);
	}

	public String getNodataSearchMsg() {
		waitForElementVisible(driver, CatalogProductPageUI.MSG_NODATA_SEARCH);
		return getElementText(driver, CatalogProductPageUI.MSG_NODATA_SEARCH);
	}

	public int getSizeProductSearchDisplayed() {
		waitForElementVisible(driver, CatalogProductPageUI.PRODUCT_NAME_SEARCH);
		return getElementSize(driver, CatalogProductPageUI.PRODUCT_NAME_SEARCH);
	}
	
}
