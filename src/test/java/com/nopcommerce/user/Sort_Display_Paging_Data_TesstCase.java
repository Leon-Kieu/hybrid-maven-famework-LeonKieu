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
import pageObject.nopcommerce.user.UserProductPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import pageObject.nopcommerce.user.UserSearchPageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Sort_Display_Paging_Data_TesstCase extends BaseTest {
	private WebDriver driver;
	Environment environment;

	private String email, password;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserSearchPageObject searchPage;
	private UserProductPageObject productPage;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		// Pre-condition
		email = UserData.Register.EMAIL;
		password = UserData.Register.PASSWORD;

		loginPage = homePage.clickToLoginLink();
		loginPage.inputToTextboxByID(driver, "Email", email);
		loginPage.inputToTextboxByID(driver, "Password", password);
		loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAcountLinkDisplay());
		productPage = homePage.clickSubMenuByName(driver, "Computers ", "Notebooks ");
	}

	@Test
	public void TC01_Sort_Name_A_To_Z(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC01_Sort_Name_A_To_Z");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 01: Select A to Z ");
		productPage.selectIntemInProductSortDropdown("Name: A to Z");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 02: Verify Sort A to Z ");
		Assert.assertTrue(productPage.isNameSortAsc());
	}

	@Test
	public void TC02_Sort_Name_Z_To_A(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC02_Sort_Name_Z_To_A");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 01: Select Z to A ");
		productPage.selectIntemInProductSortDropdown("Name: Z to A");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 02: Verify Sort Z to A ");
		Assert.assertTrue(productPage.isNameSortDsc());
	}

	@Test
	public void TC03_Sort_Low_To_High(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC03_Sort_Low_To_High");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 01: Select Low to High ");
		productPage.selectIntemInProductSortDropdown("Price: Low to High");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 02: Verify Low to High ");
		Assert.assertTrue(productPage.isProductPriceSortByAscending());
	}

	@Test
	public void TC04_Sort_High_To_Low(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Sort_High_To_Low");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 01: Select High to Low ");
		productPage.selectIntemInProductSortDropdown("Price: High to Low");
		ExtentTestManager.getTest().log(Status.INFO, "Sort 02: Verify High to Low ");
		Assert.assertTrue(productPage.isProductPriceSortByDescending());
	}

	@Test
	public void TC05_Dispay_3_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC05_Dispay_3_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display 01: Click Display 3 Product ");
		productPage.selectDropdownByName(driver, "products-pagesize", "3");
		ExtentTestManager.getTest().log(Status.INFO, "Display 02: Verify size display");
		Assert.assertEquals(productPage.getSizeListProductDisplay(), 3);
		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify Next icon display");
		Assert.assertTrue(productPage.isNextIconDisplay());
		ExtentTestManager.getTest().log(Status.INFO, "Display 04: Click Next button");
		productPage.clickNextButton();
		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify previous icon display");
		Assert.assertTrue(productPage.isPreviousIconDisplay());
	}

	@Test
	public void TC06_Dispay_6_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC06_Dispay_6_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display 01: Click Display 6 Product ");
		productPage.selectDropdownByName(driver, "products-pagesize", "6");
		ExtentTestManager.getTest().log(Status.INFO, "Display 02: Verify size display");
		Assert.assertEquals(productPage.getSizeListProductDisplay(), 6);
		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify Next icon don't display");
		Assert.assertTrue(productPage.isNextIconUnDisplay());

		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify previous icon don't display");
		Assert.assertTrue(productPage.isPreviousIconUnDisplay());

	}

	@Test
	public void TC07_Dispay_9_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC06_Dispay_9_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Display 01: Click Display 9 Product ");
		productPage.selectDropdownByName(driver, "products-pagesize", "9");

		if (productPage.getSizeListProductDisplay() < 9) {
			ExtentTestManager.getTest().log(Status.INFO, "Display 02: Verify size display");
			Assert.assertEquals(productPage.getSizeListProductDisplay(), 6);
		} else {
			ExtentTestManager.getTest().log(Status.INFO, "Display 02: Verify size display");
			Assert.assertEquals(productPage.getSizeListProductDisplay(), 9);
		}
		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify Next icon don't display");
		Assert.assertTrue(productPage.isNextIconUnDisplay());

		ExtentTestManager.getTest().log(Status.INFO, "Display 03: Verify previous icon don't display");
		Assert.assertTrue(productPage.isPreviousIconUnDisplay());

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
