package pageObject.nopcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUI.nopcommerce.user.ProductPageUI;

public class UserProductPageObject extends BasePage{
	private WebDriver driver;

	public UserProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserProductDetailPageObject clickProductByName(String ProductName) {
		waitForElementClickable(driver, ProductPageUI.DYNAMIC_LINK_PRODUCT_BY_NAME, ProductName);
		clickToElement(driver, ProductPageUI.DYNAMIC_LINK_PRODUCT_BY_NAME, ProductName);
		return PageGeneratorManager.getUserProductDetailPage(driver);
	}


	
}
