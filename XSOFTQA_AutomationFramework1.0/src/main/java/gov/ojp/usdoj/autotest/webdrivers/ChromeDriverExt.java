package gov.ojp.usdoj.autotest.webdrivers;

import gov.ojp.usdoj.autotest.AutoTestGlobals;
import gov.ojp.usdoj.autotest.stepdefinitions.StepDefinitionsBase;
import gov.ojp.usdoj.autotest.webdrivers.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class ChromeDriverExt extends ChromeDriver implements WebDriverExt {
	
	public ChromeDriverExt() {
	      super();
	  }

	  @Override
	  public String getTitle() {
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      return super.getTitle();
	  }
	  
	  @Override
	  public WebElement findElement(By by) {
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      return super.findElement(by);
	  }
	  
	  @Override
	  public WebElement findElement(By by, long timeOutInSeconds) {
	      manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
	      WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
	      wait.until(ec);
	      WebElement element = super.findElement(by);
	      manage().timeouts().implicitlyWait(WAIT_TIME_NORMAL, TimeUnit.SECONDS);
	      return element;
	  }
	  
	  @Override
	  public void get(String url) {
	      // if the web app is starting for the first time, it may take some extra time, so extend the timeout
	      manage().timeouts().implicitlyWait(WAIT_TIME_EXTENDED, TimeUnit.SECONDS);
	      
	      try {
	          Thread.sleep(2000);
	          super.get(url);
	      } 
	      catch (Exception e) {
	          super.get(url);
	      }
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      
	      // Check if we got the terms and conditions page...
	      // And click through, only if we're not trying to go there in the first place
	      if (getCurrentUrl().contains(TERMS_AND_CONDITIONS) && !url.contains(TERMS_AND_CONDITIONS)) {
	         super.findElement(By.xpath("//input[@name='agree']")).click();
	         //super.findElement(By.xpath("//input[contains(@class, 'dcm-form-button-submit')]")).click();
	      }
	      
	      // set the timeout back to normal
	      manage().timeouts().implicitlyWait(WAIT_TIME_NORMAL, TimeUnit.SECONDS);
	  }
	  
	  @Override
	  public void get(String url, By expectedElement) {
	      WebDriverWait wait = new WebDriverWait(this, AutoTestGlobals.WAIT_TIME_NORMAL);
	       
	       // loop times and try getting the page.  Wait 45 seconds for the expected element to be
	       // clickable.  If it isn't clickable try again until
	       for (int i = 0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {
	           super.get(url);
	           try {
	               wait.until(ExpectedConditions.elementToBeClickable(expectedElement));
	               // element is clickable!
	               break;
	           }
	           catch(TimeoutException e) {
	               // timeout waiting for it to be clickable.
	           }
	       }
	}
	  
	  @Override
	  public WebElement findClickableElement(By by) {
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      wait.until(ExpectedConditions.elementToBeClickable(by));
	      return super.findElement(by);
	  }

	  @Override
	  public WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition, long timeOutInSeconds) {
	      WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
	      wait.until(ec);
	      wait.until(expectedCondition);
	      return super.findElement(by);
	  }

	  @Override
	  public WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition) {
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      wait.until(expectedCondition);
	      return super.findElement(by);
	  }

	  @Override
	  public WebElement findVisibleElement(By by) {
	      WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
	      wait.until(ec);
	      wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	      return super.findElement(by);
	  }

	public WebElement findElement1(By by,
			io.appium.java_client.functions.ExpectedCondition<WebElement> expectedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	public WebElement findElement1(By by,
			io.appium.java_client.functions.ExpectedCondition<WebElement> expectedCondition, long timeOutInSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by,
			io.appium.java_client.functions.ExpectedCondition<WebElement> expectedCondition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by,
			io.appium.java_client.functions.ExpectedCondition<WebElement> expectedCondition, long timeOutInSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

}
