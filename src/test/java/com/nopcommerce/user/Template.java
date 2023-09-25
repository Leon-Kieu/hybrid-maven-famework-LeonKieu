package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.PageGeneratorManager;
import utilities.Environment;

public class Template extends BaseTest{
	private WebDriver driver;
	Environment environment;
	
	
	@Parameters({"browser","environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		ConfigFactory.setProperty("env", environmentName);
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName,environment.appUrl());
		
	}
	
	@Test
	public void TC01(Method method) {
		
	}
	
	@Test
	public void TC02(Method method) {
		
	}
	
	@Test
	public void TC03(Method method) {
		
	}
	
	@Test
	public void TC04(Method method) {
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
}
