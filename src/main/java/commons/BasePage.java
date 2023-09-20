package commons;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.nopcommerce.user.UserAddressesPageObject;
import pageObject.nopcommerce.user.UserChangePasswordPageObject;
import pageObject.nopcommerce.user.UserCustomerInfoPageObject;
import pageObject.nopcommerce.user.UserProductPageObject;
import pageUI.nopcommerce.user.AddressesPageUI;
import pageUI.nopcommerce.user.BasePageUserUI;
import pageUI.nopcommerce.user.CustomerInfoPageUI;
import pageUI.nopcommerce.user.HomePageUserUI;




public class BasePage {
	
	private long longTimeout = 30;
	private long shortTimeout = 5;
	
	
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	//Hàm mở link url bất kỳ
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	//Hàm lấy title của page
	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	//Hàm lấy url của Page
	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	//Hàm lấy source code của page
	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	//Hàm trở về page trước đó (<-)
	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	//Hàm tiến về page phía trước (->)
	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	//Hàm load lại page (Icon load)
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	//Hàm đợi xuất hiện thông báo Alert
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	//Hàm chấp nhận thông báo alert
	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	//Hàm cancel alert
	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	//Hàm lấy text alert
	protected void getAlertText(WebDriver driver) {
		waitForAlertPresence(driver).getText();
	}
	
	//Hàm sendkey alert
	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}
	
	//Hàm chuyển màn hình/tab bằng ID
	protected void switchwindowByID(WebDriver driver, String ortherID) {
		Set<String> allParentID = driver.getWindowHandles();
		for (String idtab : allParentID) {
			if ( ! idtab.equals(ortherID)) {
				driver.switchTo().window(idtab);
				break;
			}
		}
	}
	
	//Hàm chuyển màn hình/tab bằng tilte
	protected void switchwindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allParentIDs = driver.getWindowHandles();
		for (String idtab : allParentIDs) {
			//Switch qua từng ID trước
			driver.switchTo().window(idtab);
			//Lấy ra Title từng page
			String actuallTitle = driver.getTitle();
			if (actuallTitle.equals(expectedPageTitle)) {
				
				break;
			}
		}
	}
	
	//Hàm đóng màn hình/tab
	protected void closeallwindowwithoutparent(WebDriver driver, String parentID) {
		Set<String> allParentID = driver.getWindowHandles();
		for (String idtab : allParentID) {
			if ( ! idtab.equals(parentID)) {
				driver.switchTo().window(idtab);
				driver.close();
				
			}
		}
		driver.switchTo().window(parentID);
	}
	
	
	//hàm getXpath (Phục vụ lấy element)
	protected By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator); 
	}
	
	private By getByLocator(String locatorType) {
		By by = null;
		
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=") ) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=") ){
			by = By.className(locatorType.substring(6));
		}else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") ){
			by = By.name(locatorType.substring(5));
		}else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") ){
			by = By.cssSelector(locatorType.substring(4));
		}else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") ){
			by = By.xpath(locatorType.substring(6));
		}else {
			throw new RuntimeException("Locator type is not support in famework");
		}
		
		return by;
	}
	

	private String getDynamicXpath(String locatorType, String... values) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") ) {
			locatorType = String.format(locatorType,(Object[]) values);
		}
		return locatorType;
	}
	//Hàm get Element
	protected WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}
	
	//Hàm lấy ra list element
	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}
	//Hàm click element
	protected void clickToElement(WebDriver driver, String locatorType ) {
		getWebElement(driver, locatorType).click();
	}
	protected void clickToElement(WebDriver driver, String locatorType, String... dynamicValues ) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}
	
	//Hamd send key nhập text element
	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}
	protected void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	//Hàm slect chọn default dropdown
	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByVisibleText(textItem);
	}
	protected void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	//Hàm lấy ra default dropdown đã chọn
	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}
	
	protected String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	//Hàm xác minh dropdown Multiple
	protected boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}
	
	//Hàm select chọn custome dropdown
	protected void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath, String expectedItem) {
		
		getWebElement(driver, parentXpath).click();
		sleepInsecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems =  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));	
		for (WebElement items : allItems) {
			String itemtext = items.getText();		
			if (itemtext.trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", items);
				sleepInsecond(1);
				items.click();
				break;
			}
		}
	}
	

	
	
	//Hàm sleep đợi theo s
	public void sleepInsecond (long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	//Hàm lấy ra giá trị Attribute trên html nếu k có text
	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}
	protected String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}
	//Hàm lấy ra text của element
	protected String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}
	protected String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}
	//Hàm lấy ra giá trị css
	protected String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}
	//Hàm chuyển từ Hexa sang RGBA
	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex().toUpperCase();
	}
	//Hàm lấy ra size của element
	protected int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}
	protected int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}
	//Hàm check default check radio or check box
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (! element.isSelected()) {
			element.click();
		}
	}
	
	protected void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (! element.isSelected()) {
			element.click();
		}
	} 
	//Hàm bỏ check default  check box
	protected void UncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	protected void UncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	//Hàm check element hiển thị
	protected boolean isElementisDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}
	protected boolean isElementisDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}
	//Hàm set lại timeout
	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	//Hàm check element UnDisplayed
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideGlobalTimeout(driver, longTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideGlobalTimeout(driver, shortTimeout);
		if (elements.size() == 0) {
			//System.out.println("Element not in DOM");
			return true;
		} else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			//System.out.println("Element in DOM but not visible/ displayed");
			return true;
		}else {
			//System.out.println("Element in DOM and visible");
			return false;
		}
	}
	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideGlobalTimeout(driver, longTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideGlobalTimeout(driver, shortTimeout);
		if (elements.size() == 0) {
			return true;
		} else if(elements.size() > 0 && !elements.get(0).isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
	//Hàm check element enable
	protected boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}
	//Hàm check element được chọn
	protected boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}
	protected boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}
	//Hàm chuyển sang Frame or Iframe thao tác
	protected void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}
	
	//Hàm chuyển về Main để thao tác
	protected void switchToDefaultContent(WebDriver driver, String locatorType) {
		driver.switchTo().defaultContent();
	}
	
	//Hàm hover chuột vào element
	protected void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}
	
	protected void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	//Ham click phim
	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}
	
	protected void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}
	
	//Hàm uploadfile
	public void uploadMultipleFiles(WebDriver driver, String...fileNames) {
		String filePath = GlobalContants.UPLOAD_FILE_FOLDER;
		String fullFileName ="";
		for (String file : fileNames) {
			fullFileName = fullFileName+filePath+file+"\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageJqueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}
	//JS
	
	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	protected void scrollToElement(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}


	protected void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}

	protected boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
	    WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	protected boolean isImageLoaded(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	protected boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver,getDynamicXpath(locatorType, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	//Wait
	
	protected void waitForElementVisible(WebDriver driver, String locatorType) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}
	protected void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForAllElementVisible(WebDriver driver, String locatorType) {
		 WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		 explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}
	protected void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		 WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		 explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForElementInVisible(WebDriver driver, String locatorType) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}
	protected void waitForElementInVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForAllElementInVisible(WebDriver driver, String locatorType) {
		 WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		 explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}
	protected void waitForAllElementInVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		 WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		 explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}
	
	protected void waitForElementClickable(WebDriver driver, String locatorType) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}
	protected void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, longTimeout);
		  explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}
	//Ham wait element undisplayed in DOM and not in DOM
	protected void waitForElementUnDisplayed(WebDriver driver, String locatorType) {
		  WebDriverWait	explicitWait = new WebDriverWait(driver, shortTimeout);
		  overrideGlobalTimeout(driver, shortTimeout);
		  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		  overrideGlobalTimeout(driver, longTimeout);
	}
	//ham luu cookie
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	public void setCookie(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInsecond(3);
	}
	//======================================================================//
	//Hàm Dynamic textbox send key
	public void inputToTextboxByID(WebDriver driver, String textBoxID, String textValue) {
		waitForAllElementVisible(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
		sendkeyToElement(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, textValue, textBoxID);	
	}
	//Hàm Dynamic dropdow 
	public void selectDropdownByName(WebDriver driver, String DropdownName, String value) {
		waitForElementClickable(driver, BasePageUserUI.DYNAMMIC_DROPDOW_BY_NAME, DropdownName);
		selectItemInDefaultDropdown(driver, BasePageUserUI.DYNAMMIC_DROPDOW_BY_NAME, value, DropdownName);
	}
	//Hàm Dynamic radio 
	public void checkToRadioCheckBoxById(WebDriver driver, String RadioID) {
		waitForElementClickable(driver, BasePageUserUI.DYNAMIC_RADIO_CHECKBOX_BY_ID, RadioID);
		checkToDefaultCheckboxRadio(driver, BasePageUserUI.DYNAMIC_RADIO_CHECKBOX_BY_ID, RadioID);
	}
	//Hàm Dynamic click button
	public void clickToButtonByID(WebDriver driver, String IdButton) {
		waitForElementClickable(driver, BasePageUserUI.DYNAMIC_BUTTON_BY_ID, IdButton);
		clickToElement(driver, BasePageUserUI.DYNAMIC_BUTTON_BY_ID, IdButton);
	}
	//Hàm get error message 
	public String getErrorMessageByID(WebDriver driver, String IdMessage) {
		waitForAllElementVisible(driver, BasePageUserUI.DYNAMIC_ERROR_MESSAGE_BY_ID, IdMessage);
		return getElementText(driver, BasePageUserUI.DYNAMIC_ERROR_MESSAGE_BY_ID, IdMessage);
	}
	
	//Ham Dynamic getText Text box by ID
	public String getTextTextboxByID(WebDriver driver, String textBoxID) {
		waitForAllElementVisible(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
		return getElementText(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
	}
	
	//Ham Dynamic getElementAttribute Text box by ID
	public String getElementAttributeByID(WebDriver driver,String AttributeName, String textBoxID) {
		waitForAllElementVisible(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, textBoxID);
		return getElementAttribute(driver, BasePageUserUI.DYNAMIC_TEXTBOX_BY_ID, AttributeName ,textBoxID);
	}
	
	
	//Ham Dynamic getSelected Dropdown By Name
	public String getSelectedDropdownByName(WebDriver driver, String DropdownName) {
		waitForElementClickable(driver, BasePageUserUI.DYNAMMIC_DROPDOW_BY_NAME, DropdownName);
		return getSelectedItemDefaultDropdown(driver, BasePageUserUI.DYNAMMIC_DROPDOW_BY_NAME, DropdownName);
	}
	
	//Ham Dynamic check Selected Radio By ID
	
	public boolean isSelectedRadioCheckBoxByID(WebDriver driver, String RadioID) {
		waitForElementVisible(driver,BasePageUserUI.DYNAMIC_RADIO_CHECKBOX_BY_ID, RadioID);
		return isElementSelected(driver,BasePageUserUI.DYNAMIC_RADIO_CHECKBOX_BY_ID, RadioID);
	}
	
	//Ham Dynamic click subMenu
	public UserProductPageObject clickSubMenuByName(WebDriver driver,String TopMenuName, String SubMenuName) {
		waitForElementClickable(driver, BasePageUserUI.DYNAMIC_TOPMENU_LINK_BY_NAME, TopMenuName);
		hoverMouseToElement(driver, BasePageUserUI.DYNAMIC_TOPMENU_LINK_BY_NAME, TopMenuName);
		waitForElementVisible(driver, BasePageUserUI.DYNAMIC_SUBMENU_LINK_BY_NAME, TopMenuName, SubMenuName);
		clickToElement(driver, BasePageUserUI.DYNAMIC_SUBMENU_LINK_BY_NAME, TopMenuName,SubMenuName);
		return PageGeneratorManager.getUserProductPage(driver);
	}
	
	//Cac ham click Menu Header
	public UserCustomerInfoPageObject clickMyAccountLink(WebDriver driver) {
		waitForElementClickable(driver, HomePageUserUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUserUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}
	//Ham Dynamic chuyen Page
	public BasePage openPageAtMyAccountPageByName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageUserUI.DYNAMIC_MY_ACCOUNT_PAGE,pageName);
		clickToElement(driver, BasePageUserUI.DYNAMIC_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressesPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		default:
			throw new RuntimeException("Invalid Page Name at My Account Page");
		}
	}
	
}
