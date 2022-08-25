package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;

public class User_01_Register extends BaseTest {
	private WebDriver driver;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName, invalidEmail, lastName, passwordLessThan6, randomEmail;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = MultiBrowser(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		firstName = "Automation";
		lastName = "FC";
		passwordLessThan6 = "123";
		invalidEmail = "email";
		driver.get("https://demo.nopcommerce.com");
		randomEmail = "test" + generateRandomNumber() + "@gmail.com";
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_01-Step 02: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01-Step 03: Verify error message displayed");
		registerPage.verifyMessgaeDisplayed();
		
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_02-Step 02: Input detail");
		registerPage.inputToFirstnameTextBox(firstName);
		registerPage.inputToLastnameTextBox(lastName);
		registerPage.inputToEmailTextBox(invalidEmail);
		registerPage.inputToPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);
		registerPage.inputToConfirmPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);

		System.out.println("Register_02-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02-Step 04: Verify email error message");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_03-Step 02: Input detail");
		registerPage.inputToFirstnameTextBox(firstName);
		registerPage.inputToLastnameTextBox(lastName);
		registerPage.inputToEmailTextBox(randomEmail);
		registerPage.inputToPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);
		registerPage.inputToConfirmPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);

		System.out.println("Register_03-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03-Step 04: Verify register successfull message");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Register_03-Step 05: Log out");
		registerPage.logOut();
	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_04-Step 02: Input detail");
		registerPage.inputToFirstnameTextBox(firstName);
		registerPage.inputToLastnameTextBox(lastName);
		registerPage.inputToEmailTextBox(GlobalConstants.LOGIN_EMAIL);
		registerPage.inputToPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);
		registerPage.inputToConfirmPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);

		System.out.println("Register_04-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04-Step 04: Verify email error message");
		Assert.assertEquals(registerPage.getExitsErrorMessageAtEmailTextbox(), "The specified email already exists");
	}

	@Test
	public void Register_05_Password_Less_Than_6_Char() {
		System.out.println("Register_05-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_05-Step 02: Input detail");
		registerPage.inputToFirstnameTextBox(firstName);
		registerPage.inputToLastnameTextBox(lastName);
		registerPage.inputToEmailTextBox(randomEmail);
		registerPage.inputToPasswordTextBox(passwordLessThan6);
		registerPage.inputToConfirmPasswordTextBox(passwordLessThan6);

		System.out.println("Register_05-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05-Step 04: Verify password error message");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordLessThan6(),
				"Password must meet the following rules: must have at least 6 characters");
	}

	@Test
	public void Register_06_Invalid_Confrim_Password() {
		System.out.println("Register_06-Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_06-Step 02: Input detail");
		registerPage.inputToFirstnameTextBox(firstName);
		registerPage.inputToLastnameTextBox(lastName);
		registerPage.inputToEmailTextBox(randomEmail);
		registerPage.inputToPasswordTextBox(GlobalConstants.LOGIN_PASSWORD);
		registerPage.inputToConfirmPasswordTextBox(passwordLessThan6);

		System.out.println("Register_06-Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06-Step 04: Verify register successfull message");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
