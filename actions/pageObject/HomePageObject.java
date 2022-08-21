package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage{
	private WebDriver driver;
	private HomePageUI homePageUI = new HomePageUI();
	
	public HomePageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}


	public void clickToRegisterLink() {
		waitForElementClickable(driver, homePageUI.REGISTER_LINK);
		clickToElement(driver, homePageUI.REGISTER_LINK);
	}

}
