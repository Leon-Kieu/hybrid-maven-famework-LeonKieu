package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.dockerjava.api.model.UpdateConfig;
import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserCustomerInfoPageObject;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserOrderDetailPageObject;
import pageObject.nopcommerce.user.UserOrderPageObject;
import pageObject.nopcommerce.user.UserProductDetailPageObject;
import pageObject.nopcommerce.user.UserProductPageObject;
import pageObject.nopcommerce.user.UserShopingCartPageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Order_TestCase extends BaseTest {
	private WebDriver driver;
	Environment environment;

	private String email, password, firstName, lastName;
	private String invalidEmail,invalidPass, day, month, year, company;
	private String firstName_address, lastName_address, email_addres, company_address, country_address, city_address, address1, zipPostcode, phone;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserProductDetailPageObject productDetailPage;
	private UserShopingCartPageObject shopingCartPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserOrderPageObject orderPage;
	private UserOrderDetailPageObject orderDetailPage;
	
	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		email = UserData.Register.EMAIL;
		password = UserData.ChangePass.NEW_PASSWORD;
		firstName = UserData.Register.FIRST_NAME;
		lastName = UserData.Register.LAST_NAME;
		company = UserData.Register.COMPANY;
		//Address data
		firstName_address = UserData.Address.FIRST_NAME_ADDRESS;
		lastName_address = UserData.Address.LAST_NAME_ADDRESS;
		email_addres = UserData.Address.EMAIL_ADDRESS;
		company_address = UserData.Address.COMPANY_ADDRESS;
		country_address = UserData.Address.COUNTRY_ADDRESS;
		city_address = UserData.Address.CITY_ADDRESS;
		address1 = UserData.Address.ADDRESS1;
		zipPostcode = UserData.Address.ZIP_POSTAL_CODE;
		phone = UserData.Address.PHONE_NUMBER;
		
		loginPage.inputToTextboxByID(driver, "Email", email);
		loginPage.inputToTextboxByID(driver, "Password", password);
		loginPage.clickToLoginButton();
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Desktops ");
		productDetailPage = productPage.clickProductByName("Build your own computer");
		
		
	}

	@Test
	public void TC01_Add_Product_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC01_Add_Product_Cart");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 01: Input info product Processor");
		productDetailPage.selectDropdownByName(driver, "product_attribute_1", UserData.Order_Product.Processor);
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 02: Input info product RAM");
		productDetailPage.selectDropdownByName(driver, "product_attribute_2", UserData.Order_Product.RAM);
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 03: Input info product HDD");
		productDetailPage.checkToRadioCheckBoxById(driver, "product_attribute_3_7");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 04: Input info product OS");
		productDetailPage.checkToRadioCheckBoxById(driver, "product_attribute_4_8");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 05: Input info product Software ");
		productDetailPage.UncheckToRadioCheckBoxById(driver, "product_attribute_5_10");
		productDetailPage.checkToRadioCheckBoxById(driver, "product_attribute_5_11");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 06: Click button add to cart ");
		productDetailPage.clickToButtonByID(driver, "add-to-cart-button-1");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 07: Verify mesage ");
		assertEquals(productDetailPage.getTextMessageBar(), "The product has been added to your shopping cart");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 08: Close Bar message ");
		productDetailPage.closeBarMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 09: Hover Mini Cart ");
		productDetailPage.hoverMiniCart();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 10: Verify add to cart succes ");
		assertEquals(productDetailPage.getTitleMiniCart(), "There are 1 item(s) in your cart.");
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.Processor));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.RAM));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.HDD));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.OS));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.Software));
		assertEquals(productDetailPage.getPriceMiniCart(), UserData.Order_Product.PRICE);
		
	}

	@Test
	public void TC02_Edit_Product_In_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC02_Edit_Product_In_Shopping_Cart");
		ExtentTestManager.getTest().log(Status.INFO, "Edit Product To Cart 01: Click Go To Cart");
		shopingCartPage =  productDetailPage.clickGoToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Edit Product To Cart 02: Click edit Link");
		productDetailPage = shopingCartPage.clickEditLink();
		ExtentTestManager.getTest().log(Status.INFO, "Edit Product To Cart 03: Edit Software  ");
		productDetailPage.UncheckToRadioCheckBoxById(driver, "product_attribute_5_11");
		productDetailPage.checkToRadioCheckBoxById(driver, "product_attribute_5_10");
		ExtentTestManager.getTest().log(Status.INFO, "Edit Product To Cart 04: Input Quanity");
		productDetailPage.inputToTextboxByID(driver, "product_enteredQuantity_1", UserData.Order_Product.QUANITY);
		ExtentTestManager.getTest().log(Status.INFO, "Edit Product To Cart 05: Click update button");
		productDetailPage.clickToButtonByID(driver, "add-to-cart-button-1");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 06: Verify mesage ");
		assertEquals(productDetailPage.getTextMessageBar(), "The product has been added to your shopping cart");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 07: Close Bar message ");
		productDetailPage.closeBarMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 08: Hover Mini Cart ");
		productDetailPage.hoverMiniCart();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product To Cart 09: Verify add to cart succes ");
		assertEquals(productDetailPage.getTitleMiniCart(), "There are 2 item(s) in your cart.");
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.Processor));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.RAM));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.HDD));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.OS));
		assertTrue(productDetailPage.getInfoProductDisplay(UserData.Order_Product.Software_edit));
		assertEquals(productDetailPage.getPriceMiniCart(), UserData.Order_Product.PRICE_EDIT);
		
	}

	@Test
	public void TC03_Remove_From_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC03_Remove_From_Cart");
		ExtentTestManager.getTest().log(Status.INFO, "Remove From Cart 01: Click Go To Cart");
		shopingCartPage =  productDetailPage.clickGoToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Remove From Cart 02: Remove cart");
		shopingCartPage.clickRemoveLink();
		ExtentTestManager.getTest().log(Status.INFO, "Remove From Cart 03: Verify message");
		assertEquals(shopingCartPage.getMessageEmtyCart(), "Your Shopping Cart is empty!");
	}

	@Test
	public void TC04_Update_Shopping_Cart(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Update_Shopping_Cart");
		ExtentTestManager.getTest().log(Status.INFO, "Update Shoping Cart 01: Click Home");
		homePage = shopingCartPage.clickHome();
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Desktops ");
		productDetailPage = productPage.clickProductByName("Lenovo IdeaCentre 600 All-in-One PC");
		ExtentTestManager.getTest().log(Status.INFO, "Update Shoping Cart  02: Click button add to cart ");
		productDetailPage.clickToButtonByID(driver, "add-to-cart-button-3");
		ExtentTestManager.getTest().log(Status.INFO, "Update Shoping Cart 03: Close Bar message ");
		productDetailPage.closeBarMessage();
		ExtentTestManager.getTest().log(Status.INFO, "AUpdate Shoping Cart 04: Hover Mini Cart ");
		productDetailPage.hoverMiniCart();
		ExtentTestManager.getTest().log(Status.INFO, "AUpdate Shoping Cart 05: Click Go To Cart");
		shopingCartPage =  productDetailPage.clickGoToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "AUpdate Shoping Cart 06: Input quanity 5");
		shopingCartPage.inputToTextboxQty("5");
		ExtentTestManager.getTest().log(Status.INFO, "AUpdate Shoping Cart 07: Click update cart button");
		shopingCartPage.clickToButtonByID(driver, "updatecart");
		ExtentTestManager.getTest().log(Status.INFO, "AUpdate Shoping Cart 08: Verify total price");
		assertEquals(shopingCartPage.getTotalPrice(), "$2,500.00");
	}
	
	@Test
	public void TC05_CheckOut_Order(Method method) throws IOException {
		ExtentTestManager.startTest(method.getName(), "TC05_CheckOut_Order");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 02: Select Gift wrapping");
		shopingCartPage.selectDropdownByName(driver, "checkout_attribute_1", "Yes [+$10.00]");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 03: Check I agree checkbox");
		shopingCartPage.checkToRadioCheckBoxById(driver, "termsofservice");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 04: Click checkout button");
		shopingCartPage.clickToButtonByID(driver, "checkout");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 05: Click Continue button");
		shopingCartPage.clickContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 06: Click Continue Shiping button");
		shopingCartPage.clickContinueShipingButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 07: Click Continue Payment button");
		shopingCartPage.clickContinuePaymentButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 08: Click Continue Payment Info button");
		shopingCartPage.clickContinuePaymentInfoButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 09: Verify confirm Billing Adress");
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("email"), "Email: "+ email_addres);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("phone"), "Phone: "+phone);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("company"), company_address);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("address1"), address1);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 09: Verify confirm Shipping Adress");
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("email"), "Email: "+ email_addres);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("phone"), "Phone: "+phone);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("company"), company_address);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("address1"), address1);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 10: Verify Product in cart");
		assertEquals(shopingCartPage.getProductNameOderConfirm(), "Lenovo IdeaCentre 600 All-in-One PC");
		assertEquals(shopingCartPage.getProductQuanityOderConfirm(), "5");
		assertEquals(shopingCartPage.getProductTotalOderConfirm(), "$2,500.00");
		assertEquals(shopingCartPage.getGiftWrapingOderConfirm(), "Gift wrapping: Yes [+$10.00]");
		assertEquals(shopingCartPage.getSubTotalOderConfirm(), "$2,510.00");
		assertEquals(shopingCartPage.getShippingOderConfirm(), "$0.00");
		assertEquals(shopingCartPage.getTaxOderConfirm(), "$0.00");
		assertEquals(shopingCartPage.getTotalOderConfirm(), "$2,510.00");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 11: Click Confirm button");
		shopingCartPage.clickConfirmButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 12: Verify order success message");
		assertEquals(shopingCartPage.getOrderSuccessMsg(), "Your order has been successfully processed!");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 13: Save order number");
		shopingCartPage.saveOrderNumber();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 14: Click My Account");
		customerInfoPage =  shopingCartPage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 15: Click Orders Page");
		orderPage = (UserOrderPageObject) customerInfoPage.openPageAtMyAccountPageByName(driver, "Orders");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 16: Verify order number");
		assertEquals(orderPage.getOrderNumberWeb(orderPage.getOrderNumberfile()).substring(14), orderPage.getOrderNumberfile());
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 17: Click Deatail oder");
		orderDetailPage = orderPage.clickDetailPageByOrder(orderPage.getOrderNumberfile());
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 17: Verify infomation Billing order");
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("email"), "Email: "+ email_addres);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("phone"), "Phone: "+phone);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("company"), company_address);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("address1"), address1);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 18: Verify infomation Shipping order");
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("email"), "Email: "+ email_addres);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("phone"), "Phone: "+phone);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("company"), company_address);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("address1"), address1);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout 19: Verify Product in Detail order");
		assertEquals(orderDetailPage.getProductNameOderDetail(), "Lenovo IdeaCentre 600 All-in-One PC");
		assertEquals(orderDetailPage.getProductQuanityOderDetail(), "5");
		assertEquals(orderDetailPage.getProductTotalOderDetail(), "$2,500.00");
		assertEquals(orderDetailPage.getGiftWrapingOderDetail(), "Gift wrapping: Yes [+$10.00]");
		assertEquals(orderDetailPage.getSubTotalOderDetail(), "$2,510.00");
		assertEquals(orderDetailPage.getShippingOderDetail(), "$0.00");
		assertEquals(orderDetailPage.getTaxOderDetail(), "$0.00");
		assertEquals(orderDetailPage.getTotalOderDetail(), "$2,510.00");

	}
	
	@Test
	public void TC06_CheckOut_Order_Visa_Master(Method method) throws IOException {
		ExtentTestManager.startTest(method.getName(), "TC06_CheckOut_Order_Visa_Master");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 01: Click Home");
		homePage = orderDetailPage.clickHome();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 02: Click Menu bar and sub menu");
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Desktops ");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 03: Open product detail");
		productDetailPage = productPage.clickProductByName("Lenovo IdeaCentre 600 All-in-One PC");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 04: Click button add to cart ");
		productDetailPage.clickToButtonByID(driver, "add-to-cart-button-3");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 05: Close Bar message ");
		productDetailPage.closeBarMessage();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 06: Hover Mini Cart ");
		productDetailPage.hoverMiniCart();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 07: Click Go To Cart");
		shopingCartPage =  productDetailPage.clickGoToCartButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 08: Input quanity 5");
		shopingCartPage.inputToTextboxQty("5");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 09: Click update cart button");
		shopingCartPage.clickToButtonByID(driver, "updatecart");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 10: Select Gift wrapping");
		shopingCartPage.selectDropdownByName(driver, "checkout_attribute_1", "Yes [+$10.00]");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 11: Check I agree checkbox");
		shopingCartPage.checkToRadioCheckBoxById(driver, "termsofservice");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 12: Click checkout button");
		shopingCartPage.clickToButtonByID(driver, "checkout");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 13: Click Continue button");
		shopingCartPage.clickContinueButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 14: Click Continue Shiping button");
		shopingCartPage.clickContinueShipingButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 15: Check radio Creadit card");
		shopingCartPage.checkToRadioCheckBoxById(driver, "paymentmethod_1");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 16: Click Continue Payment button");
		shopingCartPage.clickContinuePaymentButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 17: Input Card Name");
		shopingCartPage.inputToTextboxByID(driver, "CardholderName", UserData.CARD.NAME);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 18: Input Card Number");
		shopingCartPage.inputToTextboxByID(driver, "CardNumber", UserData.CARD.NUMBER);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 19: Select day");
		shopingCartPage.selectDropdownByName(driver, "ExpireMonth", UserData.CARD.DATE);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 20: Select Year");
		shopingCartPage.selectDropdownByName(driver, "ExpireYear", UserData.CARD.YEAR);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 21: Input code");
		shopingCartPage.inputToTextboxByID(driver, "CardCode", UserData.CARD.PIN);
		shopingCartPage.sleepInsecond(6);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 22: Click Continue Payment Info button");
		shopingCartPage.clickContinuePaymentInfoButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 23: Verify confirm Billing Adress");
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("email"), "Email: "+ email_addres);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("phone"), "Phone: "+phone);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("company"), company_address);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("address1"), address1);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(shopingCartPage.getTextConfirmAddressByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 24: Verify confirm Shipping Adress");
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("email"), "Email: "+ email_addres);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("phone"), "Phone: "+phone);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("company"), company_address);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("address1"), address1);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(shopingCartPage.getTextConfirmBillingByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 25: Verify Product in cart");
		assertEquals(shopingCartPage.getProductNameOderConfirm(), "Lenovo IdeaCentre 600 All-in-One PC");
		assertEquals(shopingCartPage.getProductQuanityOderConfirm(), "5");
		assertEquals(shopingCartPage.getProductTotalOderConfirm(), "$2,500.00");
		assertEquals(shopingCartPage.getGiftWrapingOderConfirm(), "Gift wrapping: Yes [+$10.00]");
		assertEquals(shopingCartPage.getSubTotalOderConfirm(), "$2,510.00");
		assertEquals(shopingCartPage.getShippingOderConfirm(), "$0.00");
		assertEquals(shopingCartPage.getTaxOderConfirm(), "$0.00");
		assertEquals(shopingCartPage.getTotalOderConfirm(), "$2,510.00");
		shopingCartPage.sleepInsecond(10);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 26: Click Confirm button");
		shopingCartPage.clickConfirmButton();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 27: Verify order success message");
		assertEquals(shopingCartPage.getOrderSuccessMsg(), "Your order has been successfully processed!");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 28: Save order number");
		shopingCartPage.saveOrderNumber();
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 29: Click My Account");
		customerInfoPage =  shopingCartPage.clickMyAccountLink(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 30: Click Orders Page");
		orderPage = (UserOrderPageObject) customerInfoPage.openPageAtMyAccountPageByName(driver, "Orders");
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 31: Verify order number");
		assertEquals(orderPage.getOrderNumberWeb(orderPage.getOrderNumberfile()).substring(14), orderPage.getOrderNumberfile());
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 32: Click Deatail oder");
		orderDetailPage = orderPage.clickDetailPageByOrder(orderPage.getOrderNumberfile());
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 33: Verify infomation Billing order");
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("email"), "Email: "+ email_addres);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("phone"), "Phone: "+phone);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("company"), company_address);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("address1"), address1);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(orderDetailPage.getTextConfirmAddressByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 34: Verify infomation Shipping order");
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("name"), firstName_address + " "+lastName_address);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("email"), "Email: "+ email_addres);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("phone"), "Phone: "+phone);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("company"), company_address);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("address1"), address1);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("city-state-zip"), city_address+","+zipPostcode);
		assertEquals(orderDetailPage.getTextConfirmBillingByClass("country"), country_address);
		ExtentTestManager.getTest().log(Status.INFO, "Checkout Visa 35: Verify Product in Detail order");
		assertEquals(orderDetailPage.getProductNameOderDetail(), "Lenovo IdeaCentre 600 All-in-One PC");
		assertEquals(orderDetailPage.getProductQuanityOderDetail(), "5");
		assertEquals(orderDetailPage.getProductTotalOderDetail(), "$2,500.00");
		assertEquals(orderDetailPage.getGiftWrapingOderDetail(), "Gift wrapping: Yes [+$10.00]");
		assertEquals(orderDetailPage.getSubTotalOderDetail(), "$2,510.00");
		assertEquals(orderDetailPage.getShippingOderDetail(), "$0.00");
		assertEquals(orderDetailPage.getTaxOderDetail(), "$0.00");
		assertEquals(orderDetailPage.getTotalOderDetail(), "$2,510.00");
	}
	

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		//closeBrowserDriver();
	}
}
