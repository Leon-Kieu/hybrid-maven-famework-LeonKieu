package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserComparePageObject;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserProductDetailPageObject;
import pageObject.nopcommerce.user.UserProductPageObject;
import pageObject.nopcommerce.user.UserRecentlyPageObject;
import pageObject.nopcommerce.user.UserShopingCartPageObject;
import pageObject.nopcommerce.user.UserWishlistOageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Wishlist_Compare_RecentView_TestCase extends BaseTest {
	private WebDriver driver;
	Environment environment;
	private String email, password, firstName, lastName;
	private UserLoginPageObject loginPage;
	private UserHomePageObject homePage;
	private UserProductPageObject productPage;
	private UserProductDetailPageObject productDetailPage;
	private UserWishlistOageObject wishlistPage;
	private UserShopingCartPageObject shopingCartPage;
	private UserComparePageObject comparePage;
	private UserRecentlyPageObject recentlyPage;
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		loginPage = homePage.clickToLoginLink();
		email = UserData.Register.EMAIL;
		password = UserData.Register.PASSWORD;
		firstName = UserData.Register.FIRST_NAME;
		lastName = UserData.Register.LAST_NAME;
		loginPage.inputToTextboxByID(driver, "Email", email);
		loginPage.inputToTextboxByID(driver, "Password", password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAcountLinkDisplay());
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Notebooks ");
		productDetailPage = productPage.clickProductByName("Apple MacBook Pro 13-inch");
		
	}
	
	@Test
	public void TC01_Add_to_wishlist(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC01_Add_to_wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 01: Click wishlist button");
		productDetailPage.clickToButtonByID(driver, "add-to-wishlist-button-4");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 02: Verify message");
		Assert.assertEquals(productPage.getTextMessageBar(), "The product has been added to your wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 03: Click WishList link");
		wishlistPage = productDetailPage.clickWishlistLink();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 04: Verify wishlist");
		Assert.assertEquals(wishlistPage.getSizeWishlist(), 1);
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 05: Click link wishlist");
		wishlistPage.clickLinkShareWishlist();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 06: Click Title");
		Assert.assertEquals(wishlistPage.getTextShareTitle(), "Wishlist of"+" "+firstName+" "+lastName);
	}
	
	@Test
	public void TC02_Add_Product_To_Cart_From_WishList(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC02_Add_Product_To_Cart_From_WishList");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 00: Click Home");
		homePage = wishlistPage.clickHome();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 01: Click wishlistlink");
		wishlistPage = homePage.clickWishListLink();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 02: Click checkbox");
		wishlistPage.clickCheckBoxAddTocartByName("Apple MacBook Pro 13-inch");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 03: Click button Add to cart");
		shopingCartPage = (UserShopingCartPageObject) wishlistPage.clickButtonAddtocart();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 04: Verify product add to cart success");
		assertEquals(shopingCartPage.getSizeShopingCart(), 1);
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Wishlist 05: Verify product wish list");
		assertEquals(shopingCartPage.getQuantityWishlist(), "(0)");
	}
	
	@Test
	public void TC03_Remove_Product_in_wishlist(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC03_Remove_Product_in_wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 00: Click Home");
		homePage = wishlistPage.clickHome();
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 01: Open Product List");
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Notebooks ");
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 03: Open Product Deatail");
		productDetailPage = productPage.clickProductByName("Apple MacBook Pro 13-inch");
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 04: Click wishlist button in detail page");
		productDetailPage.clickToButtonByID(driver, "add-to-wishlist-button-4");
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 05: Verify message");
		Assert.assertEquals(productPage.getTextMessageBar(), "The product has been added to your wishlist");
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 06: Click WishList link");
		wishlistPage = productDetailPage.clickWishlistLink();
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 07: Click remove");
		wishlistPage.clickRemove();
		ExtentTestManager.getTest().log(Status.INFO, "Remove Product Wishlist 09: Verify message");
		assertEquals(wishlistPage.getWishlistEmptymessage(), "The wishlist is empty!");
	}
	
	@Test
	public void TC04_Add_Product_To_Compare(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Add_Product_To_Compare");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 00: Click Home");
		homePage = wishlistPage.clickHome();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 01: Open Product List");
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Notebooks ");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 02: Click Compare 2 product");
		productPage.clickCompareProductByName("Apple MacBook Pro 13-inch");
		productPage.clickCompareProductByName("Asus N551JK-XO076H Laptop");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 03: Verify message");
		Assert.assertEquals(productPage.getTextMessageBar(), "The product has been added to your product comparison");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 04: Click Compare Link");
		comparePage= (UserComparePageObject) productPage.openPageAtFooterByName(driver, "Compare products list");
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 04: Verify product");
		assertEquals(comparePage.getSizeByClassname("remove-product"), 2);
		assertEquals(comparePage.getSizeByClassname("product-name"), 2);
		assertEquals(comparePage.getSizeByClassname("product-price"), 2);
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 05: Clear list");
		comparePage.clickClearlist();
		ExtentTestManager.getTest().log(Status.INFO, "Add Product Compare 06: Verify message");
		assertEquals(comparePage.getBodyMessage(), "You have no items to compare.");
	}
	
	@Test
	public void TC05_Recently_viewd_product(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC05_Recently_viewd_product");
		ExtentTestManager.getTest().log(Status.INFO, "Recently 00: Click Home");
		homePage = wishlistPage.clickHome();
		ExtentTestManager.getTest().log(Status.INFO, "Recently 01: Open Product List");
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Notebooks ");
		ExtentTestManager.getTest().log(Status.INFO, "Recently 02: Click Link product");
		productPage.clickLinkProduct(UserData.Product.PRODUCTNAME);
		ExtentTestManager.getTest().log(Status.INFO, "Recently 03: Click Recently viewed products");
		recentlyPage =  (UserRecentlyPageObject) productPage.openPageAtFooterByName(driver, "Recently viewed products");
		ExtentTestManager.getTest().log(Status.INFO, "Recently 04: Verify product");
		assertEquals(recentlyPage.getSizeRecently(), 3);
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
