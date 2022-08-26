package commons;

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
		
		return driver;
	}
	
	
}
