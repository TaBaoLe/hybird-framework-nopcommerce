package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.LoginPageObject;
import pageObject.MyAccountObject;

public class User_03_MyAccount extends BaseTest{
	private WebDriver driver;
	private MyAccountObject myAccountObject;
	private String projectPath = System.getProperty("user.dir");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {	
		driver = MultiBrowser(browserName);
		myAccountObject = new MyAccountObject(driver);
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void MyAccount_01_Customer_Info() {
		System.out.println("MyAccount_01_Step 1 : Update info");
		myAccountObject.updateGender();
		myAccountObject.updateFristName();
		myAccountObject.updateLastName();
		myAccountObject.updateDateOfBrith();
		myAccountObject.updateEmail();
		myAccountObject.updateCompanyName();
		System.out.println("MyAccount_01_Step 2 : Click Save");
		myAccountObject.clickToSave();
		System.out.println("MyAccount_01_Step 3 : Verify info after update");

	}
	
	@Test
	public void MyAccount_02_Address() {
		
	}
	
	@Test
	public void MyAccount_03_Change_Password() {
		
	}
	
	@Test
	public void MyAccount_04_My_Product_Preview() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
