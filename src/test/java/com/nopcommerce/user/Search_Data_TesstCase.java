package com.nopcommerce.user;

import java.lang.reflect.Method;

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
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import pageObject.nopcommerce.user.UserSearchPageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Search_Data_TesstCase extends BaseTest{
	private WebDriver driver;
	Environment environment;
	
	private String email, password;
	private Register_Login_MyAccount_TestCase Pre_condition;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserSearchPageObject searchPage;
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName,environment.appUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		//Pre-condition
		email = UserData.Register.EMAIL;
		password = UserData.Register.PASSWORD;
		
		loginPage = homePage.clickToLoginLink();
		loginPage.inputToTextboxByID(driver, "Email", email);
		loginPage.inputToTextboxByID(driver, "Password", password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAcountLinkDisplay());
		searchPage =(UserSearchPageObject) homePage.openPageAtFooterByName(driver, "Search");
	}
	
	@Test
	public void TC01_Search_Empty_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC01_Search_Empty_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Verify message");
		Assert.assertEquals(searchPage.getTextSearchEmpty(), "Search term minimum length is 3 characters");
		
	}
	
	@Test
	public void TC02_Search_Data_Not_Exist(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC02_Search_Data_Not_Exist");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data not exist");
		searchPage.inputToTextboxByID(driver, "q", "Macbook Pro 2015");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 03:Verify message");
		Assert.assertEquals(searchPage.getTextSearchNoResults(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC03_Search_Prouct_Name_Tuongdoi(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC03_Search_Prouct_Name_Tuongdoi");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data tuong doi");
		searchPage.inputToTextboxByID(driver, "q", "Lenovo");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Verify product");
		Assert.assertEquals(searchPage.getListSearch(), 2);
	}
	
	
	@Test
	public void TC04_Search_Prouct_Name_Tuyetdoi(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Search_Prouct_Name_Tuyetdoi");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data tuyet doi");
		searchPage.inputToTextboxByID(driver, "q", "ThinkPad X1 Carbon");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Verify product");
		Assert.assertEquals(searchPage.getListSearch(), 1);
		Assert.assertEquals(searchPage.getSearchText(), "Lenovo Thinkpad X1 Carbon Laptop");
	}
	
	@Test
	public void TC05_Search_Parent_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC05_Search_Parent_Categories");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data ");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Checked Advanced search ");
		searchPage.checkedCheckBoxByID("advs");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Select dropdow Category ");
		searchPage.selectDropdownByName(driver, "cid", "Computers");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 05: Verify message");
		Assert.assertEquals(searchPage.getTextSearchNoResults(), "No products were found that matched your criteria.");

	}
	
	@Test
	public void TC06_Search_Sub_Categories(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC06_Search_Sub_Categories");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data ");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Checked Advanced search ");
		searchPage.checkedCheckBoxByID("advs");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Select dropdow Category ");
		searchPage.selectDropdownByName(driver, "cid", "Computers");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04: Checked Automatically search sub categories ");
		searchPage.checkedCheckBoxByID("isc");
		ExtentTestManager.getTest().log(Status.INFO, "Search 05: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 06: Verify product");
		Assert.assertEquals(searchPage.getListSearch(), 1);
		Assert.assertEquals(searchPage.getSearchText(), "Apple MacBook Pro 13-inch");
	}
	
	@Test
	public void TC07_Search_Incorrect_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC07_Search_Incorrect_Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data ");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Checked Advanced search ");
		searchPage.checkedCheckBoxByID("advs");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Select dropdow Category ");
		searchPage.selectDropdownByName(driver, "cid", "Computers");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04: Checked Automatically search sub categories ");
		searchPage.checkedCheckBoxByID("isc");
		ExtentTestManager.getTest().log(Status.INFO, "Search 05: Select dropdow Manufacturer ");
		searchPage.selectDropdownByName(driver, "mid", "HP");
		ExtentTestManager.getTest().log(Status.INFO, "Search 06: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 07: Verify message");
		Assert.assertEquals(searchPage.getTextSearchNoResults(), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC08_Search_Correct_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC08_Search_Correct_Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data ");
		searchPage.inputToTextboxByID(driver, "q", "Apple MacBook Pro");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Checked Advanced search ");
		searchPage.checkedCheckBoxByID("advs");
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Select dropdow Category ");
		searchPage.selectDropdownByName(driver, "cid", "Computers");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04: Checked Automatically search sub categories ");
		searchPage.checkedCheckBoxByID("isc");
		ExtentTestManager.getTest().log(Status.INFO, "Search 05: Select dropdow Manufacturer ");
		searchPage.selectDropdownByName(driver, "mid", "Apple");
		ExtentTestManager.getTest().log(Status.INFO, "Search 06: Click Button Search");
		searchPage.clickButtonSearchPage();
		ExtentTestManager.getTest().log(Status.INFO, "Search 07: Verify product");
		Assert.assertEquals(searchPage.getListSearch(), 1);
		Assert.assertEquals(searchPage.getSearchText(), "Apple MacBook Pro 13-inch");
	}
	
	@Test
	public void TC09_Sort_Name_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC08_Search_Correct_Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Input data ");
	}
	
	
	public void TC10_Sort_Name_Z_To_A(Method method) {
		
	}
	
	
	public void TC11_Sort_Low_To_High(Method method) {
		
	}
	
	
	public void TC12_Sort_High_To_Low(Method method) {
		
	}
	
	
	public void TC13_Dispay_3_Product(Method method) {
		
	}
	
	
	public void TC14_Dispay_6_Product(Method method) {
		
	}
	
	
	public void TC15_Dispay_9_Product(Method method) {
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
