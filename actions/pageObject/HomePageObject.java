package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.nopcommerce.data.ProductData;

import commons.BasePage;
import pageUIs.HomePageUI;
import pageUIs.MyAccountUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
	}

	public void clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
	}

	public boolean isMyAccountDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, HomePageUI.SEARCH_BUTTON);
		clickToElement(driver, HomePageUI.SEARCH_BUTTON);
	}

	public void verifyErrorMessage() {
		String getAlertText = driver.switchTo().alert().getText();
		Assert.assertEquals(getAlertText, ProductData.alertMessage);
		driver.switchTo().alert().accept();
	}

	public void clickToProductPage() {
		waitForElementClickable(driver, MyAccountUI.PRODUCT_LINK);
		hoverMouseToElemenet(driver, MyAccountUI.PRODUCT_LINK);
		waitForElementClickable(driver, HomePageUI.PRODUCT_NOTEBOOKS);
		clickToElement(driver, HomePageUI.PRODUCT_NOTEBOOKS);
	}

	public void sortByAtoZ() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "5");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='picture']//img");
		// Create list for storing product name by img[alt]
		List<String> productName = new ArrayList<>();
		// get product name and store to productName[List]
		for (int i = 0; i < products.size(); i++) {
			String getName = products.get(i).getAttribute("alt");
			productName.add(getName);
		}
		sleepInSecond(1);
		boolean isSortByDescending = checkAlphabeticalByAtoZ(productName);
		String result = isSortByDescending == true ? "Sorted by A to Z" : "Unsort";
		System.out.println(result);
	}

	public boolean checkAlphabeticalByAtoZ(List<String> product_names) {

		String previous = ""; // empty string

		for (String current : product_names) {
			if (current.compareTo(previous) < 0)
				return false;
			previous = current;
		}

		return true;

	}

	public boolean checkAlphabeticalByZtoA(List<String> product_names) {

		String previous = ""; // empty string

		for (String current : product_names) {
			if (current.compareTo(previous) > 0)
				return false;
			previous = current;
		}

		return true;

	}

	public void sortByZtoA() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "6");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='picture']//img");
		// Create list for storing product name by img[alt]
		List<String> productName = new ArrayList<>();
		// get product name and store to productName[List]
		for (int i = 0; i < products.size(); i++) {
			String getName = products.get(i).getAttribute("alt");
			productName.add(getName);
			System.out.println(getName);
		}
		sleepInSecond(1);
		boolean isSortByaAscending = checkAlphabeticalByZtoA(productName);
		String result = isSortByaAscending == true ? "Sorted by Z to A" : "Unsort";
		System.out.println(result);
	}

	public void sortByLowToHigh() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "10");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='prices']");
		// Create list for storing product price
		List<Integer> productPrice = new ArrayList<>();
		// get product price and store to productName[List]
		for (int i = 0; i < products.size(); i++) {
			String getPrice = products.get(i).getText().replace("$", "").replace(",", "").replace(".00", "");
			int price = Integer.parseInt(getPrice);
			productPrice.add(price);
			System.out.println(price);
		}

		sleepInSecond(1);
		boolean isSort = checkPriceLowToHigh(productPrice);
		String result = isSort == true ? "Sorted by low to high" : "Unsort";
		System.out.println(result);
	}

	private boolean checkPriceLowToHigh(List<Integer> productPrice) {
		for (int i = 0; i < productPrice.size(); i++) {
			if (productPrice.get(i) < productPrice.get(i + 1)) {
				return true;
			}
		}
		return false;
	}

	public void sortByHighToLow() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "11");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='prices']");
		// Create list for storing product price
		List<Integer> productPrice = new ArrayList<>();
		// get product price and store to productName[List]
		for (int i = 0; i < products.size(); i++) {
			String getPrice = products.get(i).getText().replace("$", "").replace(",", "").replace(".00", "");
			int price = Integer.parseInt(getPrice);
			productPrice.add(price);
			System.out.println(price);
		}
		sleepInSecond(1);
		boolean isSort = checkPriceHighToLow(productPrice);
		String result = isSort == true ? "Sorted by high to low" : "Unsort";
		System.out.println(result);
	}

	private boolean checkPriceHighToLow(List<Integer> productPrice) {
		for (int i = 0; i < productPrice.size(); i++) {
			if (productPrice.get(i) > productPrice.get(i + 1)) {
				return true;
			}
		}
		return false;
	}

	public void displayBy3() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-pagesize']", "3");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='prices']");
		Assert.assertTrue(products.size()==3);
	}
	
	public void displayBy6() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-pagesize']", "6");
		// Store all sorted product
		List<WebElement> products = new ArrayList<WebElement>();
		sleepInSecond(1);
		products = getListWebElement(driver, "//div[@class='prices']");
		Assert.assertTrue(products.size()==6);
	}
}
