package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.MyAccountObject;

public class User_03_MyAccount extends BaseTest {
	private WebDriver driver;

	 @Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = MultiBrowser(browserName);
		myAccountObject.loginToMyAccount(driver, GlobalConstants.LOGIN_EMAIL, GlobalConstants.LOGIN_PASSWORD);
	}

	 //@Test
	public void MyAccount_01_Customer_Info() {
		System.out.println("MyAccount_01_Step 1 : Click to my account");
		myAccountObject.clickToMyAccount();

		System.out.println("MyAccount_01_Step 2 : Update info");
		myAccountObject.updateGender();
		myAccountObject.updateFirstName();
		myAccountObject.updateLastName();
		myAccountObject.updateDateOfBrith();
		myAccountObject.updateEmail();
		myAccountObject.updateCompanyName();

		System.out.println("MyAccount_01_Step 3 : Click Save");
		myAccountObject.clickToSave();

		System.out.println("MyAccount_01_Step 4 : Verify info after update");
		myAccountObject.verifyAfterUpdate();
	}

	// @Test
	public void MyAccount_02_Address() {
		System.out.println("MyAccount_02_Step 1 : Click to my adresses");
		myAccountObject.clickToMyAccount();
		myAccountObject.clickToAddresses();

		System.out.println("MyAccount_02_Step 2 : Click to add new");
		myAccountObject.clickToAddNew();

		System.out.println("MyAccount_02_Step 3 : Input new adresses");
		myAccountObject.inputAddressesFirstName();
		myAccountObject.inputAddressesLastName();
		myAccountObject.inputAddressesEmail();
		myAccountObject.inputAddressesCompany();
		myAccountObject.selectAddressesCountry();
		myAccountObject.inputAddressesCity();
		myAccountObject.inputAddressesAdress1();
		myAccountObject.inputAddressesAdress2();
		myAccountObject.inputAddressesPostalCode();
		myAccountObject.inputAddressesPhoneNumber();
		myAccountObject.inputAddressesFaxNumber();

		System.out.println("MyAccount_02_Step 4 : Input new adresses");
		myAccountObject.clickToSaveAddress();

		System.out.println("MyAccount_02_Step 5 : Verify new adresses");
		myAccountObject.verifyAddressesUpdate();
	}

	//@Test
	public void MyAccount_03_Change_Password() throws InterruptedException {
		System.out.println("MyAccount_03_Step 1 : Click to change password");
		myAccountObject.clickToMyAccount();
		myAccountObject.clickToChangePassword();
		
		System.out.println("MyAccount_03_Step 2 : Update new password");
		myAccountObject.updateNewPasswrod();
		
		System.out.println("MyAccount_03_Step 3 : Verify old password login failed");
		//switch to main change password page -> log out
		myAccountObject.clickToChangePassword();
		registerPage.logOut();
		//click to login link
		homePageObject.clickToLoginLink();
		myAccountObject.loginOldPassword();
		String text = "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect";
		Assert.assertEquals(text,loginPageObject.getErrorMessageUnregisterAtEmailTextBox());
		
		System.out.println("MyAccount_03_Step 4 : Verify new password login success");
		myAccountObject.loginNewPassword();
		Assert.assertTrue(homePageObject.isMyAccountDisplayed());
	}

	@Test
	public void MyAccount_04_My_Product_Preview() {
		System.out.println("MyAccount_04_Step 1 : Click to product");
		myAccountObject.clickToMyAccount();
		myAccountObject.clickToProductLink();
		myAccountObject.clickToProductDesktops();
		
		System.out.println("MyAccount_04_Step 2 : Click to product detail");
		myAccountObject.clickToProductDetail();
		
		System.out.println("MyAccount_04_Step 3 : Add product preview");
		myAccountObject.addReview();
		
		System.out.println("MyAccount_04_Step 4 : verify ptoduct preview");
		// back to my account page
		myAccountObject.clickToMyAccount();
		myAccountObject.clickToMyProductReview();	
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
