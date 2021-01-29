package loginTest.MvnAmicusCloud;

import org.testng.annotations.Test;
import junit.framework.Assert;
import org.testng.annotations.BeforeMethod;
import java.sql.Driver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	WebDriver driver;
	String actual_title_afterLogout = "Amicus Cloud";
 
  @BeforeMethod
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "D:\\SeleniumJars\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.get("https://cloud.amicusattorney.com/");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }
  
  @Test
  public void loginTests() throws InterruptedException {
	  driver.findElement(By.id("tbEmail")).sendKeys("jdee@ggqaj1.1law.pro");
		driver.findElement(By.id("tbPwd")).sendKeys("Password123$");
		
		WebElement remember_Me =  driver.findElement(By.id("cbRememberMe"));
		WebElement keepMeLogin =  driver.findElement(By.id("cbTimeToLive"));
		
		keepMeLogin.click();
		
		Select selection = new Select(driver.findElement(By.id("ddTimeToLive")));
		selection.selectByVisibleText("2 days");
	
		driver.findElement(By.id("btLogin")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		if(!driver.findElements(By.id("lkLogout")).isEmpty()) {
			Assert.assertTrue("user login fail",driver.findElement(By.id("lkLogout")).isDisplayed());
		}
		else if(!driver.findElements(By.xpath("//span[text()='Continue To Login Anyway']")).isEmpty()) {
				driver.findElement(By.xpath("//span[text()='Continue To Login Anyway']")).click();
				Assert.assertTrue("user login fail",driver.findElement(By.id("lkLogout")).isDisplayed());
				
		}
		js.executeScript("arguments[0].click();",driver.findElement(By.id("lkLogout")));
			Assert.assertEquals("user logout fail",driver.getTitle(), actual_title_afterLogout);
			
  }

}
