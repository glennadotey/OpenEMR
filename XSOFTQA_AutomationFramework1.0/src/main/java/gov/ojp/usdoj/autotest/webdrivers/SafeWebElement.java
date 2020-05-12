package com.xsoftqa.autotest.webdrivers;

/**
 * @author tahiraka
 *
 */

import com.xsoftqa.autotest.AutoTestGlobals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SafeWebElement extends RemoteWebElement {
private static final Logger log = LogManager.getLogger(SafeWebElement.class);
    
    public enum ActionType {
        CLICK, CLEAR
    }
    
    private WebElement element = null;
    private WebDriver driver = null;
    private By by = null;
    
    public SafeWebElement(WebElement element, WebDriver driver, By by) {
        this.element = element;
        this.driver = driver;
        this.by = by;
    }

    public WebElement getElement() {
        return element;
    }

    public void setElement(WebElement element) {
        this.element = element;
    }
    
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void clear() {
        safeElementAction(ActionType.CLEAR, AutoTestGlobals.WAIT_TIME_NORMAL);
    }
    
    @Override
    public void click() {
        safeElementAction(ActionType.CLICK, AutoTestGlobals.WAIT_TIME_NORMAL);
    }
    
    @Override
    public WebElement findElement(By findBy) {
        log.debug("Find element on: " + by);
        
        WebDriverWait wait = new WebDriverWait(driver, AutoTestGlobals.WAIT_TIME_NORMAL);
        
        RuntimeException exception = null;
        
        for (int i=0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {
            
            log.debug("Trying to find elements on: " + by + " time: " + i);
            try {                
                return new SafeWebElement(element.findElement(findBy), driver, findBy);
            }
            catch(StaleElementReferenceException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("StaleElementReference caught trying to find element on: " + by, e);
                exception = e;
            }
            catch(ElementNotVisibleException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("ElementNotVisible caught trying to find element on: " + by, e);
                exception = e;
            }
            catch(TimeoutException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("TimeoutException caught trying to find element on: " + by, e);
                exception = e;
            }
            
            // something went wrong so try to find it again, but wait until it's present first...
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            
            // find it again
            element = driver.findElement(by);
        }
        
        // if we made it to here the find failed.  Rethrow the last exception

        log.error("Failed to find element on: " + by);
        throw exception;
    }
    
    @Override
    public List<WebElement> findElements(By findBy) {
        log.debug("Find elements on: " + by);
        
        WebDriverWait wait = new WebDriverWait(driver, AutoTestGlobals.WAIT_TIME_NORMAL);
        
        RuntimeException exception = null;
        
        for (int i=0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {
            
            log.debug("Trying to find elements on: " + by + " time: " + i);
            try {
                // need to wrap this in safe elements too
                List<WebElement> nonSafeElements = element.findElements(findBy);
                List<WebElement> safeElements = new ArrayList<WebElement>();
                
                for (WebElement currElement : nonSafeElements) {
                    // maybe be a problem since findBy might return multiple results...
                    safeElements.add(new SafeWebElement(currElement, driver, findBy));
                }
                
                return safeElements;
            }
            catch(StaleElementReferenceException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("StaleElementReference caught trying to find elements on: " + by, e);
                exception = e;
            }
            catch(ElementNotVisibleException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("ElementNotVisible caught trying to find elements on: " + by, e);
                exception = e;
            }
            catch(TimeoutException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("TimeoutException caught trying to find elements on: " + by, e);
                exception = e;
            }
            
            // something went wrong so try to find it again, but wait until it's located first
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        
        // if we made it to here the find failed.  Rethrow the last exception

        log.error("Failed to find elements on: " + by);
        throw exception;
    }
    
    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }
    
    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }
    
    @Override
    public Point getLocation() {
        return element.getLocation();
    }
    
    @Override
    public Dimension getSize() {
        return element.getSize();
    }
    
    @Override
    public String getTagName() {
        return element.getTagName();
    }
    
    @Override
    public String getText() {
        return element.getText();
    }
    
    @Override
    public boolean isDisplayed() {
        RuntimeException exp = null;
        for (int i=0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {
            
            try {
                log.debug("Checking isDisplayed() for element: " + by + " times: " + i);
                return element.isDisplayed(); 
            }
            catch(StaleElementReferenceException e) {
                log.error("Caught StaleElementReferenceException.  Trying again.");
                exp = e;
            }
            
            // find it again
            element = driver.findElement(by);
            new Actions(driver).moveToElement(element).perform();
        }
        
        log.error("Failed to get isDisplay for Element: " + by, exp);
        throw exp;
    }
    
    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }
    
    @Override
    public boolean isSelected() {
        return element.isSelected();
    }
    
    @Override
    public void submit() {
        element.submit();
    }

    private void safeElementAction(ActionType action, long waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, waitTime);
        
        RuntimeException exception = null;
        
        for (int i = 0; i < AutoTestGlobals.DEFAULT_RETRIES; i++) {

            log.debug("Trying to : " + action + " element time: " + i);
            try {
                switch(action) {
                    case CLEAR:
                        wait.until(ExpectedConditions.visibilityOf(element));
                        element.clear();
                        break;
                    case CLICK:
                        wait.until(ExpectedConditions.elementToBeClickable(element));                        
                        element.click();
                        break;
                }
                // must have worked.  Just return
                return;
            }
            catch(StaleElementReferenceException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("StaleElementReference caught clicking: " + by, e);
                exception = e;
            }
            catch(TimeoutException e) {
                // ignore and try again
                log.error("TimeoutException caught clicking: " + by, e);
                exception = e;
            }

            
            // something went wrong so try to re-find it
            element = driver.findElement(by);
        }

        log.error("Failed to : " + action + " " + by + "!!!");
        throw exception;

    }

    @Override
    public void sendKeys(CharSequence... keys) {
        
        log.debug("Sending keys to element: " + by);
        
        WebDriverWait wait = new WebDriverWait(driver, AutoTestGlobals.WAIT_TIME_NORMAL);
        
        int i = 0;
        boolean foundIt = false;
        RuntimeException exception = null;
        
        while (i < AutoTestGlobals.DEFAULT_RETRIES && !foundIt) {
            
            log.debug("Trying to send keys to element: " + by + " time: " + i);
            try {
                
                // shouldn't be possible, but getting an NPE on the wait line...
                if (element != null) {
                    // wait until the element is visible
                    wait.until(ExpectedConditions.visibilityOf(element));
                    element.sendKeys(keys);
                    foundIt = true;
                }
            }
            catch(StaleElementReferenceException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("StaleElementReference caught trying to send keys to element: " + by, e);
                exception = e;
                i++;
            }
            catch(ElementNotVisibleException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("ElementNotVisible caught trying to send keys to element: " + by, e);
                exception = e;
                i++;
            }
            catch(TimeoutException e) {
                //ignore and try again.  if it fails enough times we'll throw this
                log.error("TimeoutException caught trying to send keys to element: " + by, e);
                exception = e;
                i++;
            }
            
            // something went wrong so try to find it again
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            element = driver.findElement(by);
            
        }
        
        if (!foundIt) {
            log.error("Failed to send keys to element: " + by);
            throw exception;
        }
    }

}
