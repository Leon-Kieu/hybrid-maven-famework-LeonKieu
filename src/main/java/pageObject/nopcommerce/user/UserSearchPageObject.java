package pageObject.nopcommerce.user;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.nopcommerce.user.SearchPageUI;

public class UserSearchPageObject extends BasePage{
	private WebDriver driver;

	public UserSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickButtonSearchPage() {
		waitForElementClickable(driver, SearchPageUI.BUTTON_SEARCH_PAGE);
		clickToElement(driver,  SearchPageUI.BUTTON_SEARCH_PAGE);
	}

	public String getTextSearchEmpty() {
		waitForElementVisible(driver, SearchPageUI.ERROR_SEARCH_MESSAGE_WARNING);
		return getElementText(driver, SearchPageUI.ERROR_SEARCH_MESSAGE_WARNING);
	}
	public String getTextSearchNoResults() {
		waitForElementVisible(driver, SearchPageUI.ERROR_SEARCH_MESSAGE_NORESULT);
		return getElementText(driver, SearchPageUI.ERROR_SEARCH_MESSAGE_NORESULT);
	}

	public int getListSearch() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_LIST);
		return getElementSize(driver, SearchPageUI.SEARCH_LIST);
	}

	public String getSearchText() {
		waitForElementVisible(driver, SearchPageUI.SEARCH_LIST);
		return getElementText(driver, SearchPageUI.SEARCH_LIST);
	}

	public void checkedCheckBoxByID(String id) {
		waitForElementVisible(driver, SearchPageUI.DYNAMIC_CHECKED_SEARCH_CHECKBOX,id);
		checkToDefaultCheckboxRadio(driver, SearchPageUI.DYNAMIC_CHECKED_SEARCH_CHECKBOX,id);
	}

	
}
