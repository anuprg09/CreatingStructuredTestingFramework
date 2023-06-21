package TestNGFramework;

import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;

///<Summary>
///This is A login Test case
///</summary>

public class LoginTestCase {
//WebDriver _driver;
ThreadLocal<RemoteWebDriver> _driver = new ThreadLocal<>();
	///<Summary>
	///This is TestMethod to verify Login with Valid Data
	///</summary>
  @Test(description = "This is TestMethod to verify Login with Valid Data")
  @Parameters({"Uname", "Upass"})
  public void LoginWithValidData(String userName, String userPassword) {
	  _driver.findElement(By.id("loginLink")).click();
	  _driver.findElement(By.id("UserName")).sendKeys(userName);
	  _driver.findElement(By.id("Password")).sendKeys(userPassword);
	  _driver.findElement(By.xpath("//input[@value='Log in']")).click();
	  assertEquals("Hello admin!", _driver.findElement(By.xpath("//a[@title='Manage']")).getText());
  }

	///<Summary>
	///This is TestMethod to verify Login with InValid Data
	///</summary>

  @Test(description = "This is TestMethod to verify Login with InValid Data")
  @Parameters({"Uname"})
  public void LoginWithInValidData(String Uname) {
	  _driver.findElement(By.id("loginLink")).click();
	  _driver.findElement(By.id("UserName")).sendKeys(Uname);
	  _driver.findElement(By.id("Password")).sendKeys("password");
	  _driver.findElement(By.xpath("//input[@value='Log in']")).click();
	  assertNotEquals("Hello Admin!", _driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][contains(., 'Invalid login attempt.')]")).getText());
  
  }

	///<Summary>
	///This is Method to Initialize browser
	///</summary>

  @BeforeTest
  @Parameters({"browser"})
  public void InitBrowser(String browser) {
	  if(browser.equals("chrome"))
	  {
		  _driver = new ChromeDriver();
	  }
	  else if(browser.equals("firefox"))
	  {
	  _driver = new FirefoxDriver();
	  }
	  else if(browser.equals("edge"))
	  {
	  _driver = new EdgeDriver();
	  }

	  _driver.manage().window().maximize();
	  _driver.get("http://eaapp.somee.com/");
  }

  	///<Summary>
	///This is Method to Close the browser
	///</summary>

  @AfterTest
  public void ExitBrowser() {
	  _driver.close();
  }

}
