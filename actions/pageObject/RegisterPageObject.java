package pageObject;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nopcommerce.data.ProductData;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	private WebDriver driver;
	private RegisterPageUI registerPageUI = new RegisterPageUI();
	

	public RegisterPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public String getErrorMessageAtLastnameTextbox() {
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERRORTEXT);
	}

	public String getErrorMessageAtFristnameTextbox() {
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERRORTEXT);
	}

	public String getErrorMessageAtEmailTextbox() {
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_TEXT);
	}

	public String getErrorMessageAtPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_TEXT);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_TEXT);
	}

	public void inputToFirstnameTextBox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_FIRST_NAME, string);
	}

	public void inputToLastnameTextBox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_LAST_NAME, string);

	}

	public void inputToEmailTextBox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_EMAIL, string);
	}

	public void inputToPasswordTextBox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_PASSWORD, string);
	}

	public void inputToConfirmPasswordTextBox(String string) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_CONFIRM_PASSWORD, string);
	}

	public String getRegisterSuccessMessage() {
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public String getExitsErrorMessageAtEmailTextbox() {
		sleepInSecond(1);
		return getElementText(driver, RegisterPageUI.EXITS_EMAIL_ERROR_TEXT);
	}

	public void logOut() throws InterruptedException {
		Thread.sleep(1000);
		clickToElement(driver,RegisterPageUI.LOG_OUT_LINK);
	}

	public String getErrorMessageAtPasswordLessThan6() {
		String text = getElementText(driver,RegisterPageUI.PASSWORD_FIRST_LINE_TEXT)
				.concat(" " + getElementText(driver,RegisterPageUI.PASSWORD_SECOND_LINE_TEXT));
		return text;
	}

	public void verifyMessgaeDisplayed() {
		Assert.assertEquals(getErrorMessageAtFristnameTextbox(), "First name is required.");
		Assert.assertEquals(getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	public void inputDataToSearchField(String data) {
		sendkeyToElement(driver, RegisterPageUI.INPUT_SEARCH_FIELD,data);

	}

}
