package gov.ojp.usdoj.autotest;

import java.util.concurrent.TimeUnit; 

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/**
 * @author tahiraka
 *
 */

public class AutoTestUtils {
	private static final Logger log = LogManager.getLogger(AutoTestUtils.class);

    // Wait till page is fully loaded.  This will wait until the create token call in
    // the js on the data generator page returns a valid value.  That should be a good
    // indicator that the page has loaded.
    
	/* public static void waitForDataGenerator(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_NORMAL);
        wait.until( new Predicate<WebDriver>() {
            public boolean apply(WebDriver driver) {
                driver.manage().timeouts().setScriptTimeout(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);

                Object result = ((JavascriptExecutor) driver).executeAsyncScript(
                        "var callback = arguments[arguments.length - 1];" +
                        "credentialsManager.createToken(callback);");

                return result != null;
            }
        });
    } */
    
 /*    public static long makeServiceCall(WebDriver webDriver, String script) {

        for (int i=0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {

            log.debug("Making call to scrit: " + script + " time: " + i);
            Object result = ((JavascriptExecutor)webDriver).executeAsyncScript(script);

            long retVal = ((Long)result).longValue();

            if (retVal != -1) {
                return retVal;
            }
            else {
                log.error("Failed to make web service call with script: " + script + " will retry!");
                // reload data generator and wait for it to fully load.
                webDriver.get(webDriver.getCurrentUrl());
                AutoTestUtils.waitForDataGenerator(webDriver);
            }
        }

        log.error("Failed to run script: " + script + " after retries!");
        return -1;
    } */
    
   /* public static boolean makeServiceBooleanCall(WebDriver webDriver, String script) {

        for (int i=0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {

            log.debug("Making call to script: " + script + " time: " + i);
            Object result = ((JavascriptExecutor)webDriver).executeAsyncScript(script);
            
            boolean retVal = new Boolean(result.toString());               
    
            if (retVal){
                return retVal;
            }
            else {
                log.error("Failed to make web service call with script: " + script + " will retry!");
                // reload data generator and wait for it to fully load.
                webDriver.get(webDriver.getCurrentUrl());
                AutoTestUtils.waitForDataGenerator(webDriver);
            }
        }

        log.error("Failed to run script: " + script + " after retries!");
        return false;
    } */

}
