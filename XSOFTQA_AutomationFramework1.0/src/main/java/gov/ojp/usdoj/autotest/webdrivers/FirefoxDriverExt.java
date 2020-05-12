package gov.ojp.usdoj.autotest.webdrivers;

/**
 * @author tahiraka
 *
 */

import static org.junit.Assert.fail;

import gov.ojp.usdoj.autotest.AutoTestGlobals;
import gov.ojp.usdoj.autotest.stepdefinitions.StepDefinitionsBase;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FirefoxDriverExt extends FirefoxDriver implements WebDriverExt{
	
private static final Logger log = LogManager.getLogger(FirefoxDriverExt.class);
    
	public FirefoxDriverExt(FirefoxProfile profile) {
        //super(profile);
	}

	@Override
	public String getTitle() {
		WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
        wait.until(ec);
        return super.getTitle();
	}
	
	@Override
	public WebElement findElement(By by) {
	    log.debug("Calling parent find element and converting to SafeWebElement: " + by);
        return new SafeWebElement(super.findElement(by), this, by);
	}
	
	@Override
	public void get(String url) {
    	// if the web app is starting for the first time, it may take some extra time, so extend the timeout
    	manage().timeouts().implicitlyWait(WAIT_TIME_EXTENDED, TimeUnit.SECONDS);
    	
    	try {
    	    //Thread.sleep(2000);
    	    super.get(url);

            WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
            wait.until(ec);
    	} 
    	catch (UnhandledAlertException ex) {
            try {
                Alert alert = switchTo().alert();
                if (alert != null) {
                    alert.accept();
                }
                switchTo().defaultContent();
                super.get(url);  // Try again
            }
            catch (Exception e) {} // Just in case popup disappears
            
        }
    	catch (Exception e) {
    	    fail("Exception thrown on get() call "+ e.getMessage());
    	}
    	WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
        wait.until(ec);
        
        // Check if we got the terms and conditions page...
        // And click through, only if we're not trying to go there in the first place
        if (getCurrentUrl().contains(TERMS_AND_CONDITIONS) && !url.contains(TERMS_AND_CONDITIONS)) {
           super.findElement(By.xpath("//input[@name='agree']")).click();
           //super.findElement(By.xpath("//input[contains(@class, 'dcm-form-button-submit')]")).click();
        }
        
        // set the wait time back to normal
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
		return new SafeWebElement (super.findElement(by), this, by);
	}


	@Override
	public WebElement findElement(By by, long timeOutInSeconds) {
        manage().timeouts().implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS);
        WebElement element = super.findElement(by);
        manage().timeouts().implicitlyWait(WAIT_TIME_NORMAL, TimeUnit.SECONDS);
        return new SafeWebElement(element, this, by);
	}
	
	@Override
	public WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition, long timeOutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(this, timeOutInSeconds);
        wait.until(ec);
	    wait.until(expectedCondition);
		return new SafeWebElement(super.findElement(by), this, by);
	}

	@Override
	public WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition) {
	    WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
        wait.until(ec);
	    wait.until(expectedCondition);
		return new SafeWebElement(super.findElement(by), this, by);
	}

    @Override
    public WebElement findVisibleElement(By by) {
        WebDriverWait wait = new WebDriverWait(this, WAIT_TIME_NORMAL);
        wait.until(ec);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return new SafeWebElement(super.findElement(by), this, by);
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
