package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.data.UserData;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;

public class User_02_Login extends BaseTest{
	private WebDriver driver;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {	
		driver = MultiBrowser(browserName);
		homePageObject.clickToLoginLink();
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
		loginPageObject.inputEmail("test");
		
		System.out.println("Login 02-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login 02-Step 3: Verify error messgae");
		Assert.assertEquals("Wrong email",loginPageObject.getErrorMessageAtEmailTextBox());
	}
		
	@Test
	public void Login_03_Login_With_Unregister_Email() {
		System.out.println("Login 03-Step 1: Input unregister email");
		loginPageObject.inputEmail(UserData.unRegisterEmail);
		
		System.out.println("Login 03-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login 03-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	
	@Test
	public void Login_04_Login_With_Empty_Password() {
		System.out.println("Login 04-Step 1: Input email");
		loginPageObject.inputEmail(GlobalConstants.LOGIN_EMAIL);
		
		System.out.println("Login 04-Step 2: Click to login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login 04-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	
	@Test
	public void Login_05_Login_With_Wrong_Password() {
		System.out.println("Login 05-Step 1: Input email");
		loginPageObject.inputEmail(GlobalConstants.LOGIN_EMAIL);
		
		System.out.println("Login 05-Step 2: Input wrong password");
		loginPageObject.inputPassword(UserData.wrongPassword);
		
		System.out.println("Login 05-Step 3: Click to login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login 05-Step 3: Verify error messgae");
		String text = "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
	}
	//Regist user first
	@Test
	public void Login_06_Login_With_Correct_Data() {
		System.out.println("Login 06-Step 1: Input email");		
		loginPageObject.inputEmail(GlobalConstants.LOGIN_EMAIL);
		
		System.out.println("Login 06-Step 2: Input wrong password");
		loginPageObject.inputPassword(GlobalConstants.LOGIN_PASSWORD);
		
		System.out.println("Login 06-Step 3: Click to login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login 06-Step 4: Verify login success direc to homepage");
		Assert.assertTrue(homePageObject.isMyAccountDisplayed());
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
