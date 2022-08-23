package com.nopcommerce.user;

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
import pageObject.LoginPageObject;

public class User_02_Login extends BasePage{
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private LoginPageObject loginPageObject;
	private String validEmail, unRegisterEmail, correctPassword, wrongPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
		switch (browserName) {
		case "firefox":
			System.out.println("Run on" + browserName);
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.out.println("run on " +  browserName);
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.out.println("run on " +  browserName);
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			break;
		}
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginPageObject = new LoginPageObject(driver);
		validEmail = "test@gmail.com";
		unRegisterEmail = "abc@gmail.com";
		correctPassword = "1qaz2wsx";
		wrongPassword = "zxccvvvva";
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
	}

	@Test
	public void Login_01_Login_With_Empty_Data() {
		System.out.println("Login 01-Step 1: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 01-Step 2: Verify error message at email text box");
		Assert.assertEquals("Please enter your email",loginPageObject.getErrorMessageAtEmailTextBox());
	}
	
	@Test
	public void Login_02_Login_With_Invalid_Email() {
		System.out.println("Login 02-Step 1: Input invalid email");
		loginPageObject.inputInvalidEmail("test");
		System.out.println("Login 02-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 02-Step 3: Verify error messgae");
		Assert.assertEquals("Wrong email",loginPageObject.getErrorMessageAtEmailTextBox());
	}
		
	@Test
	public void Login_03_Login_With_Unregister_Email() {
		System.out.println("Login 03-Step 1: Input unregister email");
		loginPageObject.inputInvalidEmail(unRegisterEmail);
		System.out.println("Login 03-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 03-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	
	@Test
	public void Login_04_Login_With_Empty_Password() {
		System.out.println("Login 04-Step 1: Input email");
		loginPageObject.inputInvalidEmail(validEmail);
		System.out.println("Login 04-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 04-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	
	@Test
	public void Login_05_Login_With_Wrong_Password() {
		System.out.println("Login 05-Step 1: Input email");
		loginPageObject.inputInvalidEmail(validEmail);
		System.out.println("Login 05-Step 2: Input wrong password");
		loginPageObject.inputWrongPassword(wrongPassword);
		System.out.println("Login 05-Step 3: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 05-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	//Regist user first
	@Test
	public void Login_06_Login_With_Correct_Data() {
		System.out.println("Login 06-Step 1: Input email");
		loginPageObject.inputInvalidEmail(validEmail);
		System.out.println("Login 06-Step 2: Input wrong password");
		loginPageObject.inputWrongPassword(correctPassword);
		System.out.println("Login 06-Step 3: Click to login button");
		loginPageObject.clickToLoginButton();
		System.out.println("Login 06-Step 4: Verify login success direc to homepage");
		Assert.assertEquals(driver.getCurrentUrl(),"https://demo.nopcommerce.com/");
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
