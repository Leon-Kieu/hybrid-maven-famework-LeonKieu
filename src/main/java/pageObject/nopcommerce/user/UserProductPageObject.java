package pageObject.nopcommerce.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public void selectIntemInProductSortDropdown(String textItem) {
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropdown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
		sleepInsecond(2);
	}

	public boolean isNameSortAsc() {
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.PRODUCT_NAME);
		List<String> productNames = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedName = new ArrayList<String>(productNames);
		Collections.sort(sortedName);
		return productNames.equals(sortedName);
	}

	public boolean isNameSortDsc() {
		List<WebElement> elementList = getListWebElement(driver, ProductPageUI.PRODUCT_NAME);
		List<String> productNames = elementList.stream().map(n -> n.getText()).collect(Collectors.toList());
		List<String> sortedName = new ArrayList<String>(productNames);
		Collections.sort(sortedName);
		Collections.reverse(sortedName);
		return productNames.equals(sortedName);
	}
	
	public boolean isProductPriceSortByAscending() {
		ArrayList<Float> productUIlist = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
		for (WebElement productName : productPriceText) {
			productUIlist.add(Float.parseFloat(productName.getText().replace(",", ".").replace("$", "").replace(".00", "").trim()));
		}
		
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIlist) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		
		return productSortList.equals(productUIlist);
	}

	public boolean isProductPriceSortByDescending() {
		ArrayList<Float> productUIlist = new ArrayList<Float>();
		List<WebElement> productPriceText = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE);
		for (WebElement productName : productPriceText) {
			productUIlist.add(Float.parseFloat(productName.getText().replace(",", ".").replace("$", "").replace(".00", "").trim()));
		}
		
		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productUIlist) {
			productSortList.add(product);
		}
		
		Collections.sort(productSortList);
		Collections.reverse(productSortList);
		return productSortList.equals(productUIlist);
	};
	
}
