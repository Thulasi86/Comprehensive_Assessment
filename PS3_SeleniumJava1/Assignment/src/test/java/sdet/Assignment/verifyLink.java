package sdet.Assignment;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class verifyLink {
	WebDriver driver;

  @BeforeClass
  public void beforeMethod() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\thula\\Documents\\SDET\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		 driver = new ChromeDriver();
  }

  @Test(dataProvider = "linkData",priority = 0)
  public void link(String linkText1, String linkText2, String buttonText1, String buttonText2) throws InterruptedException {
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
      Thread.sleep(2000);
      WebElement link1 = driver.findElement(By.xpath("(//a[text()='"+linkText1+"'])[1]"));
      if (link1.isDisplayed()) {
          System.out.println("Why Autify link is displayed on the page.");
      } else {
          System.out.println("Why Autify link is not displayed on the page.");
      }
      Thread.sleep(2000);
      WebElement link2 = driver.findElement(By.xpath("(//a[text()='"+linkText2+"'])[1]"));
      if (link2.isDisplayed()) {
          System.out.println("Pricing link is displayed on the page.");
      } else {
          System.out.println("Pricing link is not displayed on the page.");
      }
       
      WebElement button1 = driver.findElement(By.xpath("//a[text()='"+buttonText1+"']"));
      if (button1.isEnabled()) {
          System.out.println("Start free trial button is enabled");
      } else {
          System.out.println("Start free trial button is disabled");
      }
      
      driver.findElement(By.xpath("(//a[text()='Log in'])[1]")).click();
    
    WebElement button2 = driver.findElement(By.xpath("//a[@class='"+buttonText2+"']"));
    if (button2.isEnabled()) {
        System.out.println("Sign in button is enabled");
    } else {
        System.out.println("Sign in button is disabled");
    }
  }
 
  @AfterClass
  public void afterMethod() {
	  driver.quit();
  }


  @DataProvider(name = "linkData")
  public Object[][] linkData() {
    return new Object[][] {
       {"Why Autify","Pricing","Start Free Trial","auth-btn"},
    };
  }
 
}
