package stepDefinitions;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountLoginPage;
import pageObjects.HomePage;
import pageObjects.MyAccountPage;

public class Steps {
	
	HomePage homepage;
	AccountLoginPage accLoginPage;
	MyAccountPage myAccPage;
	
	ResourceBundle rs;
	String browser;
	Logger log;
	
	WebDriver driver;
	
	@Before
	public void setUp() {
		rs= ResourceBundle.getBundle("config");
		browser = rs.getString("browser");
		log = LogManager.getLogger(this.getClass());
	}
	
	@After
	public void tearDown(Scenario scenario) {
		System.out.println("Scenario status is --------->" + scenario.getStatus());
		if(scenario.isFailed()) {  // take screenshot if any step fails
			TakesScreenshot ts = (TakesScreenshot)driver;
			byte screenshot[] = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", scenario.getName());
		}
		driver.quit();
	}
	
	
	@Given("User launches browser window")
	public void user_launches_browser_window() {
		if("chrome".equals(browser)) {
			driver = new ChromeDriver();
		} else if("edge".equals(browser)) {
			driver = new EdgeDriver();
		} else {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}

	@Given("opens url {string}")
	public void opens_url(String string) {
		driver.get(string);
		driver.manage().window().maximize();
	}

	@When("User navigates to my account menu")
	public void user_navigates_to_my_account_menu() {
		homepage = new HomePage(driver);
		homepage.clickMyAccount();
		log.info("clicked on my account");
	}

	@When("clicks on login")
	public void clicks_on_login() {
		homepage.clickLogin();
		log.info("clicked on login");
	}

	@When("enters email {string} and password {string}")
	public void enters_email_and_password(String email, String pswd) {
		accLoginPage = new AccountLoginPage(driver);
		log.info("User enters email and password");
		accLoginPage.setEmail(email);
		accLoginPage.setPassword(pswd);
	}

	@When("clicks login button")
	public void clicks_login_button() {
		accLoginPage.clickLogin();
		log.info("User clicks login button");
	}

	@Then("user nagivates in My account page")
	public void user_nagivates_in_my_account_page() {
		myAccPage = new MyAccountPage(driver);
		if(myAccPage.isMyAccountPageExists()) {
			log.info("User successfully login");
			Assert.assertTrue(true);
		} else {
			log.error("login unsuccessful");
			Assert.assertTrue(false);
		}
		
	}


}
