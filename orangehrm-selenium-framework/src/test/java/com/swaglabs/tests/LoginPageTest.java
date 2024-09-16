package com.swaglabs.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.swaglabs.baseclass.BaseClass;
import com.swaglabs.pageobject.LoginPage;

public class LoginPageTest extends BaseClass {

	LoginPage loginPage;

	@BeforeMethod
	public void setUp() throws Throwable {
		super.setUp(); // Ensure WebDriver is initialized
		loginPage = new LoginPage(getDriver()); // Pass WebDriver to LoginPage
	}

	@Test(priority = 2)
	public void logintest() throws Throwable {
		String username = "standard_user";
		String password = "secret_sauce";

		log.info("Start loginTest with username: " + username + " and password: " + password);
		loginPage.enterUserName(username);
		Thread.sleep(2000);
		loginPage.enterPassWord(password);
		Thread.sleep(2000);
		loginPage.clickLoginButton();
		Thread.sleep(2000);
		log.info("Login test completed.");
	}

	@Test(priority = 1)
	// Method to verify the page title
	public void verifyTitle() {
		// Verify the page title
		String expectedTitle = "Swag Labs"; // Expected title
		loginPage.verifyTitle(expectedTitle);
	}

	@Test(priority = 0)
	// Method to verify CurrentUrl
	public void verifyCurrentUrl() {
		// Verify CurrentUrl
		String ExpectedUrl = "https://www.saucedemo.com/v1/"; // Expected Url
		loginPage.currentUrl(ExpectedUrl);
	}

	@Test(priority = 3)

	// Method To Verify LoginSuccessFull
	public void verifyLoginSuccessefull() {
		String ExpectedTitle = "Swag Labs"; // Expected title after login
		loginPage.verifyLoginSuccess(ExpectedTitle);
	}
}
