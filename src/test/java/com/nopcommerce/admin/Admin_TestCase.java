package com.nopcommerce.admin;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.nopcommerce.data.AdminData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.admin.AdminCatalogProductsObject;
import pageObject.nopcommerce.admin.AdminDashboardObject;
import pageObject.nopcommerce.admin.AdminLoginPageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Admin_TestCase extends BaseTest{
	private WebDriver driver;
	Environment environment;
	
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardObject dashboardPage;
	private AdminCatalogProductsObject catalogProductPage;
	
	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.adminUrl());
		adminLoginPage = PageGeneratorManager.getAdminDLoginPage(driver);
		//Pre-condition
		adminLoginPage.inputToTextboxByID(driver, "Email", environment.adminUser());
		adminLoginPage.inputToTextboxByID(driver, "Password", environment.adminPass());
		dashboardPage =  adminLoginPage.clickButtonLogin();
	}
	
	@Test
	public void TC01_Search_Product_Name(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC01_Search_Product_Name");
		ExtentTestManager.getTest().log(Status.INFO, "Search 01: Open Catalog Products");
		dashboardPage.clickMenuAdminByName(driver,"Catalog");
		catalogProductPage = (AdminCatalogProductsObject) dashboardPage.openAdminPageAtMenuNameAndSubMenuName(driver, "Catalog", "Products");
		ExtentTestManager.getTest().log(Status.INFO, "Search 02: Input Product Name");
		catalogProductPage.sleepInsecond(2);
		catalogProductPage.inputToTextboxByID(driver, "SearchProductName", AdminData.Search_Product.PRODUCT_NAME);
		ExtentTestManager.getTest().log(Status.INFO, "Search 03: Click button Search");
		catalogProductPage.clickToButtonByID(driver, "search-products");
		ExtentTestManager.getTest().log(Status.INFO, "Search 04: Verify");
		assertEquals(catalogProductPage.getProductSearchName(), AdminData.Search_Product.PRODUCT_NAME);
		assertEquals(catalogProductPage.getProductSearchSKU(), AdminData.Search_Product.SKU);
		assertEquals(catalogProductPage.getProductSearchPrice(), AdminData.Search_Product.PRICE);
		assertEquals(catalogProductPage.getProductSearchQuantity(), AdminData.Search_Product.QUANTITY);
		assertTrue(catalogProductPage.isPublishDisplayed());
	}
	
	@Test
	public void TC02_Search_Product_Name_ParentCategory_Uncheck(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC02_Search_Product_Name_ParentCategory_Uncheck");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check Category 01: Select dopdown Category ");
		catalogProductPage.selectDropdownByName(driver, "SearchCategoryId", AdminData.Search_Product.CATEGORY);
		ExtentTestManager.getTest().log(Status.INFO, "Search Check Category 02: Click button Search");
		catalogProductPage.clickToButtonByID(driver, "search-products");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check Category 03: Verrify");
		assertEquals(catalogProductPage.getNodataSearchMsg(), "No data available in table");
	}
	
	@Test
	public void TC03_Search_Product_Name_ParentCategory_Check(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC03_Search_Product_Name_ParentCategory_Check");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check SubCategory 01: Check SubCategory");
		catalogProductPage.checkToRadioCheckBoxById(driver, "SearchIncludeSubCategories");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check SubCategory 02: Click button Search");
		catalogProductPage.clickToButtonByID(driver, "search-products");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check SubCategory 03: Verrify");
		assertEquals(catalogProductPage.getSizeProductSearchDisplayed(), 1);
	}
	@Test
	public void TC04_Search_Product_Name_ChildCategory(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Search_Product_Name_ChildCategory");
		ExtentTestManager.getTest().log(Status.INFO, "Search Chill Category 01: Select dopdown Category");
		catalogProductPage.selectDropdownByName(driver, "SearchCategoryId", AdminData.Search_Product.CHILL_CATEGORY);
		ExtentTestManager.getTest().log(Status.INFO, "Search Chill Category 01: UnCheck SubCategory");
		catalogProductPage.UncheckToRadioCheckBoxById(driver, "SearchIncludeSubCategories");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check SubCategory 03: Click button Search");
		catalogProductPage.clickToButtonByID(driver, "search-products");
		ExtentTestManager.getTest().log(Status.INFO, "Search Check SubCategory 04: Verrify");
		assertEquals(catalogProductPage.getSizeProductSearchDisplayed(), 1);
	}
	
	
	@Test
	public void TC05_Search_Product_Name_Manufacturer(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC05_Search_Product_Name_Manufacturer");
		ExtentTestManager.getTest().log(Status.INFO, "Search Manufacturer 01: Select all category");
		catalogProductPage.selectDropdownByName(driver, "SearchCategoryId", "All");
		ExtentTestManager.getTest().log(Status.INFO, "Search Manufacturer 02: Select Manufacturer");
		catalogProductPage.selectDropdownByName(driver, "SearchManufacturerId",  AdminData.Search_Product.Manufacturer);
		ExtentTestManager.getTest().log(Status.INFO, "Search Manufacturer 03: Click button Search");
		catalogProductPage.clickToButtonByID(driver, "search-products");
		ExtentTestManager.getTest().log(Status.INFO, "Search Manufacturer 04: Verrify");
		assertEquals(catalogProductPage.getNodataSearchMsg(), "No data available in table");
	}
	
	@Test
	public void TC06_Go_Directly_SKU_Product(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC06_Go_Directly_SKU_Product");
		ExtentTestManager.getTest().log(Status.INFO, "Directly SKU Product 01:Clear Product Name");
		catalogProductPage.inputToTextboxByID(driver, "SearchProductName", " ");
		ExtentTestManager.getTest().log(Status.INFO, "Search Manufacturer 02: Select Manufacturer");
		catalogProductPage.selectDropdownByName(driver, "SearchManufacturerId",  "All");
		ExtentTestManager.getTest().log(Status.INFO, "Directly SKU Product 02: Input SKU");
		catalogProductPage.inputToTextboxByID(driver, "GoDirectlyToSku", AdminData.Search_Product.SKU);
		ExtentTestManager.getTest().log(Status.INFO, "Directly SKU Product 03: Click Go");
		catalogProductPage.clickToButtonByID(driver, "go-to-product-by-sku");
		assertEquals(catalogProductPage.getElementAttributeByID(driver, "value", "Name"), AdminData.Search_Product.PRODUCT_NAME);
	}
	
	@Test
	public void TC07_Create_New_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC07_Create_New_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC08_Search_Customer_With_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC08_Search_Customer_With_Email");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC09_Search_Customer_With_FirstName_LastName(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC09_Search_Customer_With_FirstName_LastName");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC10_Search_Customer_With_Company(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC10_Search_Customer_With_Company");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC11_Search_Customer_Full_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC11_Search_Customer_Full_Data");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC12_Edit_Customer(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC12_Edit_Customer");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC13_Add_New_Address_Customer_Deatail(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC13_Add_New_Address_Customer_Deatail");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC14_Edit_Address_CustomerDetail(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC14_Edit_Address_CustomerDetail");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	@Test
	public void TC15_Delete_Address_Customer_Detail(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC15_Delete_Address_Customer_Detail");
		ExtentTestManager.getTest().log(Status.INFO, "");
	}
	
	
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
	
}
