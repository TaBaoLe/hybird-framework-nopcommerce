package com.nopcommerce.data;

import java.util.Random;

public class UserData {
	
	public static String email = "automationfc@gmail.com";
	public static String companyName = "automation";
	public static String firstName = "automation";
	public static String lastName = "fc";
	public static String company = "Automation";
	public static String address1 = "123/24 le lai";
	public static String address2 = "24 nguyen hue";
	public static String countryByValue ="82";
	public static String countryByText = "Viet Nam";
	public static String city = "hcm";
	public static String postalCode = "55000";
	public static String phoneNumber = "0988000777";
	public static String faxNumber = "123456789";
	
	public static String passwordLessThan6 = "123";
	public static String invalidEmail = "email";
	public static String randomEmail = "test" + generateRandomNumber() + "@gmail.com";
	
	public static String unRegisterEmail = "abc@gmail.com";
	public static String wrongPassword = "zxccvvvva";

	public static String title = "review";
	public static String reviewText = "gia thanh vua phai";
	
	public static int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
