package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.MyAccountObject;
import pageObject.RegisterPageObject;

public class BaseTest {
	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	protected HomePageObject homePageObject;
	protected RegisterPageObject registerPage;
	protected LoginPageObject loginPageObject;
	protected MyAccountObject myAccountObject;
	protected String firstName, invalidEmail, lastName, passwordLessThan6, randomEmail;
	protected String unRegisterEmail, wrongPassword;



	protected WebDriver MultiBrowser(String browserName) {
		switch (browserName) {
		case "firefox":
			System.out.println("run on " + browserName);
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "chrome":
			System.out.println("run on " + browserName);
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "edge":
			System.out.println("run on " + browserName);
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			throw new RuntimeException("Brose name invalid");
		}
		driver.get("https://demo.nopcommerce.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePageObject = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		loginPageObject = new LoginPageObject(driver);
		myAccountObject = new MyAccountObject(driver);
		
		firstName = "Automation";
		lastName = "FC";
		passwordLessThan6 = "123";
		invalidEmail = "email";
		randomEmail = "test" + generateRandomNumber() + "@gmail.com";
		
		unRegisterEmail = "abc@gmail.com";
		wrongPassword = "zxccvvvva";
		return driver;
	}
	
	public int generateRandomNumber() {
		Random random = new Random();
		return random.nextInt(9999);
	}
}
