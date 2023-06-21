package TestNGFramework;

import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;

public class RegisterTestcase {

	WebDriver _driver;

	///<Summary>
	///This is TestMethod to verify registration with Valid Data
	///</summary>
	@Test
	@Parameters({"Uname"})
  public void RegisterWithValidData(String Uname) {
		_driver.findElement(By.id("registerLink")).click();
		_driver.findElement(By.id("UserName")).sendKeys(Uname);
		_driver.findElement(By.id("Password")).sendKeys("P7894TTYYWER");
		_driver.findElement(By.id("ConfirmPassword")).sendKeys("P7894TTYYWER");
		_driver.findElement(By.id("Email")).sendKeys("P7894TTYYWER@gmail.com");

		_driver.findElement(By.xpath("//input[@value='Register']")).click();
		assertNotNull(_driver.findElement(By.xpath("//a[@title='Manage']")));
	}
	
	///<Summary>
	///This is TestMethod to verify registration with Blank Data
	///</summary>
	@Test
  public void RegisterWithBlankData() {
		_driver.findElement(By.id("registerLink")).click();
		_driver.findElement(By.id("UserName")).sendKeys("");
		_driver.findElement(By.id("Password")).sendKeys("");
		_driver.findElement(By.id("ConfirmPassword")).sendKeys("");
		_driver.findElement(By.id("Email")).sendKeys("");

		_driver.findElement(By.xpath("//input[@value='Register']")).click();
		assertNotNull(_driver.findElement(By.xpath("//div[contains(@class, 'text-danger')][contains(., 'The UserName field is required.')]")));  
	}


	///<Summary>
	///This is Method to Initialize browser
	///</summary>

@BeforeTest
public void InitBrowser() {
	  _driver = new EdgeDriver();
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
