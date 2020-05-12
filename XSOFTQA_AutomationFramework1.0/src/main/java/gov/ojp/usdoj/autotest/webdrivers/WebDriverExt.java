package gov.ojp.usdoj.autotest.webdrivers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.functions.ExpectedCondition;

/**
 * @author tahiraka
 *
 */
public interface WebDriverExt extends WebDriver{
	// the following wait times are in seconds
    public static final int WAIT_TIME_NORMAL = 75;
    public static final int WAIT_TIME_EXTENDED = 120;
    public static final String TERMS_AND_CONDITIONS = "terms-and-conditions";
    
    public ExpectedCondition<Boolean> ec = new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
           
            Object pageLoaded = ((JavascriptExecutor)d).executeScript("return document.pageLoaded");
            // There are a few instances where this won't be defined because we are switching to a new frame
            if (pageLoaded != null) {
                return ((JavascriptExecutor)d).executeScript("return document.pageLoaded").equals("finished");
            }
            else {
                if (((JavascriptExecutor)d).executeScript("return document.readyState").equals("complete")) {
                    // Try again for page loaded
                    pageLoaded = ((JavascriptExecutor)d).executeScript("return document.pageLoaded");
                    if (pageLoaded != null) {
                        return ((JavascriptExecutor)d).executeScript("return document.pageLoaded").equals("finished");
                    }
                    else {
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
        }
        
   };
	/**
	 * Find visible element and enable to be clicked
	 * @param by
	 * @return
	 */
	WebElement findClickableElement(By by);
	
	/**
     * Wait until the element is visible
     * @param by
     * @param expectedCondition
     * @return
     */
    WebElement findVisibleElement(By by);
	
	/**
	 * Wait until the element is found depend on the ExpectedConditions
	 * @param by
	 * @param expectedCondition
	 * @return
	 */
	WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition);
	
	/**
	 * Wait until the element is found depend on the ExpectedConditions and how long to wait in seconds
	 * @param by
	 * @param expectedCondition
	 * @param timeOutInSeconds
	 * @return
	 */
	WebElement findElement(By by, ExpectedCondition<WebElement> expectedCondition, long timeOutInSeconds);
	
	/**
     * Wait until the element is found depend on the ExpectedConditions and how long to wait in seconds
     * @param by
     * @param timeOutInSeconds
     * @return
     */
    WebElement findElement(By by, long timeOutInSeconds);
    
    public void get(String url, By expectedElement);

	WebElement findElement(By by, org.openqa.selenium.support.ui.ExpectedCondition<WebElement> expectedCondition,
			long timeOutInSeconds);

	WebElement findElement(By by, org.openqa.selenium.support.ui.ExpectedCondition<WebElement> expectedCondition);

}
