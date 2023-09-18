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
import com.github.javafaker.Company;
import com.nopcommerce.data.UserData;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopcommerce.user.UserAddressesPageObject;
import pageObject.nopcommerce.user.UserCustomerInfoPageObject;
import pageObject.nopcommerce.user.UserHomePageObject;
import pageObject.nopcommerce.user.UserLoginPageObject;
import pageObject.nopcommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.Environment;

public class Register_Login_MyAccount_TestCase extends BaseTest {
	// Khai bao bien
	private WebDriver driver;
	Environment environment;
	private String emailAddress, invalidEmail, firstName, lastName, password,invalidPass, day, month, year, company;

	// Khai bao trang
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressesPageObject addressesPageObject;

	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appUrl());

		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = UserData.Register.FIRST_NAME;
		lastName = UserData.Register.LAST_NAME;
		emailAddress = UserData.Register.EMAIL + generateFakeNumber() + "@gmail.vn";
		invalidEmail = UserData.Register.INVALIDEMAIL;
		password = UserData.Register.PASSWORD;
		invalidPass = UserData.Register.INVALID_PASSWORD;
		day = UserData.Register.DATE;
		month = UserData.Register.MONTH;
		year = UserData.Register.YEAR;
		company = UserData.Register.COMPANY;
	}

	@Test
	public void TC01_Register_emtry_data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_01_Register_emtry_data");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Verify message");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "FirstName-error"), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "LastName-error"), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "Email-error"), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "Password-error"), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "ConfirmPassword-error"), "Password is required.");
	}

	@Test
	public void TC02_Register_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_02_Register_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Check to radio checkbox");
		registerPage.checkToRadioCheckBoxById(driver, "gender-male");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Input to First Name");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 04: Input to Last Name");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 05: Send key to Day value ");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		ExtentTestManager.getTest().log(Status.INFO, "Register 06: Send key to Month");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		ExtentTestManager.getTest().log(Status.INFO, "Register 07: Send key to Year");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		ExtentTestManager.getTest().log(Status.INFO, "Register 08: Input to Email");
		registerPage.inputToTextboxByID(driver, "Email", invalidEmail);
		ExtentTestManager.getTest().log(Status.INFO, "Register 09: Input to Company");
		registerPage.inputToTextboxByID(driver, "Company", company);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to Password");
		registerPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to ConfirmPassword");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 11: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 12: Verify message");
		Assert.assertEquals(registerPage.getErrorMessageByID(driver, "Email-error"), "Wrong email");

	}

	@Test
	public void TC03_Register_Success(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC_03_Register_Success");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Check to radio checkbox");
		registerPage.checkToRadioCheckBoxById(driver, "gender-male");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Input to First Name");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 04: Input to Last Name");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 05: Send key to Day value ");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		ExtentTestManager.getTest().log(Status.INFO, "Register 06: Send key to Month");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		ExtentTestManager.getTest().log(Status.INFO, "Register 07: Send key to Year");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		ExtentTestManager.getTest().log(Status.INFO, "Register 08: Input to Email");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Register 09: Input to Company");
		registerPage.inputToTextboxByID(driver, "Company", company);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to Password");
		registerPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to ConfirmPassword");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 11: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 12: Verify message success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void TC04_Register_Email_already_exists(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC04_Register_Email_already_exists");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Check to radio checkbox");
		registerPage.checkToRadioCheckBoxById(driver, "gender-male");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Input to First Name");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 04: Input to Last Name");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 05: Send key to Day value ");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		ExtentTestManager.getTest().log(Status.INFO, "Register 06: Send key to Month");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		ExtentTestManager.getTest().log(Status.INFO, "Register 07: Send key to Year");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		ExtentTestManager.getTest().log(Status.INFO, "Register 08: Input to Email");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Register 09: Input to Company");
		registerPage.inputToTextboxByID(driver, "Company", company);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to Password");
		registerPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 11: Input to ConfirmPassword");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 12: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 13: Verify message success");
		Assert.assertEquals(registerPage.getEmailAlreadyExistsMessage(), "The specified email already exists");
	}
	
	@Test
	public void TC05_Register_Pass_Less_6_Characters(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC05_Register_Pass_Less_6_Characters");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Check to radio checkbox");
		registerPage.checkToRadioCheckBoxById(driver, "gender-male");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Input to First Name");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 04: Input to Last Name");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 05: Send key to Day value ");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		ExtentTestManager.getTest().log(Status.INFO, "Register 06: Send key to Month");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		ExtentTestManager.getTest().log(Status.INFO, "Register 07: Send key to Year");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		ExtentTestManager.getTest().log(Status.INFO, "Register 08: Input to Email");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Register 09: Input to Company");
		registerPage.inputToTextboxByID(driver, "Company", company);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to Password");
		registerPage.inputToTextboxByID(driver, "Password", invalidPass);
		ExtentTestManager.getTest().log(Status.INFO, "Register 11: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 12: Verify message");
		Assert.assertEquals(registerPage.getPasswordLess6CharactersMessage(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC06_Register_Pass_And_ConfirmPass_Not_Math(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC06_Register_Pass_And_ConfirmPass_Not_Math");
		ExtentTestManager.getTest().log(Status.INFO, "Register 01: Open register page");
		registerPage = homePage.clickToRegisterLink();
		ExtentTestManager.getTest().log(Status.INFO, "Register 02: Check to radio checkbox");
		registerPage.checkToRadioCheckBoxById(driver, "gender-male");
		ExtentTestManager.getTest().log(Status.INFO, "Register 03: Input to First Name");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 04: Input to Last Name");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		ExtentTestManager.getTest().log(Status.INFO, "Register 05: Send key to Day value ");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		ExtentTestManager.getTest().log(Status.INFO, "Register 06: Send key to Month");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		ExtentTestManager.getTest().log(Status.INFO, "Register 07: Send key to Year");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		ExtentTestManager.getTest().log(Status.INFO, "Register 08: Input to Email");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Register 09: Input to Company");
		registerPage.inputToTextboxByID(driver, "Company", company);
		ExtentTestManager.getTest().log(Status.INFO, "Register 10: Input to Password");
		registerPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Register 11: Input to ConfirmPassword");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", invalidPass);
		ExtentTestManager.getTest().log(Status.INFO, "Register 12: Click to Register Button");
		registerPage.clickToButtonByID(driver, "register-button");
		ExtentTestManager.getTest().log(Status.INFO, "Register 13: Verify message");
		Assert.assertEquals(registerPage.getMessagePassAndConfirmPassNotMath(), "The password and confirmation password do not match.");
	}
	
	@Test
	public void TC07_Login_Emtry_Data(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC07_Login_Emtry_Data");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageByID(driver, "Email-error"), "Please enter your email");
	}
	
	@Test
	public void TC08_Login_Invalid_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC08_Login_Invalid_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Input invalid Email");
		loginPage.inputToTextboxByID(driver, "Email", invalidEmail);
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 04: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageByID(driver, "Email-error"), "Wrong email");
	}
	
	@Test
	public void TC09_Login_Unregistered_Email(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC09_Login_Unregistered_Email");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Input Email");
		loginPage.inputToTextboxByID(driver, "Email", "AnhIT999@gmail.com");
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Input Password");
		loginPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Login 04: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 05: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageLogin(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC10_Login_EmailValid_Emtry_Pass(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC10_Login_EmailValid_Emtry_Pass");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Input Email");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 04: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageLogin(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC11_Login_EmailValid_WrongPass(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC11_Login_EmailValid_WrongPass");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Input Email");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Input Password");
		loginPage.inputToTextboxByID(driver, "Password", invalidPass);
		ExtentTestManager.getTest().log(Status.INFO, "Login 04: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 05: Verify error message");
		Assert.assertEquals(loginPage.getErrorMessageLogin(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC12_Login_EmailValid_PassValid(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC12_Login_EmailValid_PassValid");
		ExtentTestManager.getTest().log(Status.INFO, "Login 01: Open Login page");
		loginPage = homePage.clickToLoginLink();
		ExtentTestManager.getTest().log(Status.INFO, "Login 02: Input Email");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		ExtentTestManager.getTest().log(Status.INFO, "Login 03: Input Password");
		loginPage.inputToTextboxByID(driver, "Password", password);
		ExtentTestManager.getTest().log(Status.INFO, "Login 04: Click Login button");
		loginPage.clickToLoginButton();
		ExtentTestManager.getTest().log(Status.INFO, "Login 05: Verify Login Success");
		Assert.assertTrue(homePage.isMyAcountLinkDisplay());
	}
	
	@Test
	public void TC13_Customer_Info(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC13_Customer_Info");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 01: Open My account -Customer info");
		customerInfoPage = homePage.clickMyAccountLink();
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 02: Update Gender");
		customerInfoPage.checkToRadioCheckBoxById(driver, "gender-female");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 03: Update First Name");
		customerInfoPage.inputToTextboxByID(driver, "FirstName", "Anh01");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 04: Update Last Name");
		customerInfoPage.inputToTextboxByID(driver, "LastName", "IT01");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 05: Update Day");
		customerInfoPage.selectDropdownByName(driver, "DateOfBirthDay", "1");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 06: Update Company");
		customerInfoPage.inputToTextboxByID(driver, "Company", "AnhIT");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 07: Verify Information Customer Info");
		Assert.assertTrue(customerInfoPage.isSelectedRadioCheckBoxByID(driver, "gender-female"));
		Assert.assertEquals(customerInfoPage.getTextTextboxByID(driver, "FirstName"), "Anh01");
		Assert.assertEquals(customerInfoPage.getTextTextboxByID(driver, "LastName"), "IT01");
		Assert.assertEquals(customerInfoPage.getTextDropdownByName(driver, "DateOfBirthDay"), "1");
		Assert.assertEquals(customerInfoPage.getTextTextboxByID(driver, "Company"), "AnhIT");
		
	}
	
	@Test
	public void TC14_Addresses(Method method) {
		ExtentTestManager.startTest(method.getName(), "TC14_Addresses");
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 01: Open My account -Address");
		addressesPageObject = customerInfoPage.clickAddressesLink();
		ExtentTestManager.getTest().log(Status.INFO, "CustomerInfo 02: Click Button Add New Address");
		addressesPageObject.clickButtonAddNew();
		
	}
	
	@Test
	public void TC15_(Method method) {
		
	}
	
	@Test
	public void TC16_(Method method) {
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
