package com.orangehrm.pageobject;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.orangehrm.baseclass.BaseClass;

public class LoginPage extends BaseClass {

	WebDriver driver;
	WebDriverWait wait;

	// Define the username field using @FindBy annotation
	@FindBy(xpath = "//input[@id ='user-name']") // Adjust this locator to match the actual HTML element
	WebElement usernameField;

	@FindBy(xpath = "//input[@id ='password']") // Adjust this locator for the password field
	WebElement passwordField;

	@FindBy(xpath = "//input[@id ='login-button']") // Adjust this locator for the login button
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		PageFactory.initElements(driver, this); // Initialize elements using PageFactory
	}

	public void enterUserName(String username) {
		// Wait for the username field to be visible and then enter the username
		wait.until(ExpectedConditions.visibilityOf(usernameField));
		usernameField.sendKeys(username);
	}

	public void enterPassWord(String password) {
		wait.until(ExpectedConditions.visibilityOf(passwordField));
		passwordField.sendKeys(password);
	}

	public void clickLoginButton() {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		loginButton.click();
	}

	public void verifyLoginSuccess(String expectedTitle) {
		log.info("Verifying login success");

		// Check the page title
		String actualTitle = driver.getTitle();
		System.out.println("Verifying page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);

		// Assert to compare actual and expected titles
		Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
	}

	// Method to verify the page title
	public void verifyTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		System.out.println("Verifying page title. Expected: " + expectedTitle + ", Actual: " + actualTitle);

		// Assert to compare actual and expected titles
		Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
	}

	// Method to verify the CurrentUrl
	public void currentUrl(String expectedUrl) {
		String actualUrl = driver.getCurrentUrl();
		System.out.println("Verifying CurrentUrl. Expected: " + expectedUrl + ", Actual: " + actualUrl);

		// Assert to compare actual and expected Url
		Assert.assertEquals(actualUrl, expectedUrl, "Current Url does not match!");
	}

	
		
	}


