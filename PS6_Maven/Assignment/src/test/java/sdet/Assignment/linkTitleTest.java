package sdet.Assignment;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class linkTitleTest {
	WebDriver driver;
	
	 @BeforeClass
	  public void beforeClass() {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\thula\\Documents\\SDET\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			 driver = new ChromeDriver();
	  }
 
  @Test(dataProvider = "linkData")
  public void Testtitle(String linkText1, String linkText2) throws InterruptedException {
	  driver.get("https://autify.com");
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll']")).click();
	  String originalTab = driver.getWindowHandle();
	  
      WebElement link = driver.findElement(By.linkText("Autify NoCode")); 
      link.click();
      
      Set<String> allWindows = driver.getWindowHandles();
      
      for (String handle : allWindows) {
          if (!handle.equals(originalTab)) {
              driver.switchTo().window(handle); 
              break;
          }
      }
      WebElement link1 = driver.findElement(By.xpath("(//a[text()='"+linkText1+"'])[1]"));
      link1.click();
      String actualTitle = driver.getTitle();
      Assert.assertEquals(actualTitle, "Why Autify");
      Thread.sleep(2000);
      driver.navigate().back();
      
      WebElement link2 = driver.findElement(By.xpath("(//a[text()='"+linkText2+"'])[1]"));
      link2.click();
      String actualTitle1 = driver.getTitle();
      Assert.assertEquals(actualTitle1, "Pricing");
  }

  @DataProvider(name = "linkData")
  public Object[][] linkData() {
	  return new Object[][] {
	       {"Why Autify","Pricing"},
	    };
  }
 
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
