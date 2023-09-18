package com.nopcommerce.user;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
	public void TC_01() {
		
	}
	
	@Test
	public void TC_02() {
		
	}
	
	@Test
	public void TC_03() {
		
	}
	
	@Test
	public void TC_04() {
		
	}
	
	
}
