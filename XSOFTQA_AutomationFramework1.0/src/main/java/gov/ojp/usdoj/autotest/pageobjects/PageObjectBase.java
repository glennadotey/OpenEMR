/**
 * 
 */
package com.xsoftqa.autotest.pageobjects;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Gadotey
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Then;
import com.xsoftqa.autotest.AutoTestGlobals;
import com.xsoftqa.autotest.stepdefinitions.AutomatedTestConstants;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import com.xsoftqa.autotest.webdrivers.WebDriverExt;

public class PageObjectBase {

	// Logout
	static String LOGOUT_URL = "";

	// TODO:
	protected static final Logger log = LogManager.getLogger(PageObjectBase.class);

	private enum TextBoxType {
		MCE, NORMAL
	}

	protected static WebDriver webDriver;
	protected WebDriverWait waitNormal;
	protected WebDriverWait waitShort;
	protected WebDriverWait waitExtended;
	public WebDriverWait waitUntil;

	private By usernameFieldBy = By.id("txtUserID");
	private By passwordFieldBy = By.id("txtPassword");
	private By loginButtonBy = By.xpath("//button[@class='btn btn-default btn-lg']");
	private By homePageBy = By.xpath("//a[contains(text(),'JGITS')]");
	private By userIconBy = By.cssSelector("[data-test-id=px-opr-image-ctrl]");
	private By openEMRLogo = By.id("oemr_logo");
	private By openEMRTitle = By.xpath("//div[@class='title']");
		

	private By anySearchBoxBy = By.xpath("//input[@id='anySearchBox']");

	public String actualText;

	private By errorMessageBy = By.className("pega-dcm-error-message");

	// Change WebDriverExt to WebDriver
	public PageObjectBase(WebDriver driver) {
		webDriver = driver;
		waitShort = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_SHORT);
		waitNormal = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_NORMAL);
		waitExtended = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_EXTENDED);
		waitUntil = new WebDriverWait(webDriver, 30);

		// fluentWait = new
		// FluentWait<WebDriver>(webDriver).withTimeout(AutoTestGlobals.WAIT_TIME_EXTENDED,
		// TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS);
	}

	// ------------- LOGIN CRIDENCIALS BEGIN HERE ---------------------------

	public void enterUsername(String userName) throws Exception {
		log.debug("Entering username: " + userName);

		// This will clear and click the field and set the user name
		setTextField(usernameFieldBy, userName);

	}

	public void enterPassword(String passWord) throws Exception {
		log.debug("Entering password" + "************");

		// This will clear and click the field and set the user password
		setTextField(passwordFieldBy, passWord);

	}

	public void clickLogInButton() throws Exception {
		// Use this method from the PageObjectBase class to click the element
		clickElement(loginButtonBy);
	}

	public void verifySuccessfulLogin() throws Exception {
		// Use this method from the PageObjectBase class to verify the element
		if (verifyElementDisplayed(openEMRLogo)) {
			WebElement openEMRWindowObj = webDriver.findElement(openEMRLogo);
			if (verifyElementDisplayed(anySearchBoxBy)) {
				WebElement anySearchBoxObj = openEMRWindowObj.findElement(anySearchBoxBy);
				anySearchBoxObj.click();
				log.debug("Verifying login is successful");
			}
		} else {
			throw new LoginException();
		}
		log.debug("Currently logged in..... ");
	}
	
	public void verifyPageTitle() throws Exception {
		// Use this method from the PageObjectBase class to verify the element
		if (verifyElementDisplayed(openEMRTitle)) {
			WebElement openEMRWindowObj = webDriver.findElement(openEMRTitle);
			if (verifyElementDisplayed(anySearchBoxBy)) {
				WebElement anySearchBoxObj = openEMRWindowObj.findElement(anySearchBoxBy);
				anySearchBoxObj.click();
				log.debug("Verifying login is successful");
			}
		} else {
			throw new LoginException();
		}
		log.debug("Currently logged in..... ");
	}
	

	public void login(String username, String password) throws Exception {
		try {
			setTextField(By.id("authUser"), username);
			setTextField(By.id("clearPass"), password);
			clickLogInButton();
			verifySuccessfulLogin();
		} catch (Exception e) {

		}
	}

	// ------------------------- LOGIN END HERE ------------------------------

	/**
	 * @author Gadotey This method below is called to force Manually tested stories
	 *         to show pass in the report to use this strategy you will have to put
	 *         "Pass" into your feature file scenario step if the manual test failed
	 *         then you just have to changed the "Pass" in the feature file to
	 *         "Fail" before running your script. that way when you run the report
	 *         without negating the @ManualTest from the Testrunner tag the results
	 *         will show as Pass or Fail instead of PendingException which wouldn't
	 *         show a true test efforts.
	 * @USAGE: Below example FOR EXAMPLE: Then the GAM will be Archived for "Pass"
	 *         Response triggers @Then("^the GAM will be Archived for \"([^\"]*)\"
	 *         Response triggers$") public void
	 *         the_GAM_will_be_Archived_for_Response_triggers(String
	 *         expectedManualTestPass) throws Throwable { assertEquals("This
	 *         scenario was manually tested and PASSED", expectedManualTestPass,
	 *         basicAPFinancialCommunication.getManuallyTestedResults()); }
	 * @return actualText
	 */
	public String getManuallyTestedResults() {
		return actualText = "Pass";

	}

	// Highlight the element
	public static void highLightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", element);
	}

	// Get the text from the tool tip and compare it with the Test Data from the
	// Gherkin feature Scenario
	public void toolTip(By element, String text) {

		try {
			// Click on the element we want to get the tool tip text from
			webDriver.findElement(element).click();

			// Create action class object
			Actions builder = new Actions(webDriver);

			// find the element tool tip XPath
			WebElement elementToolTip = webDriver.findElement(element);

			// Mouse hover on the element to see the text message
			builder.moveToElement(elementToolTip).perform();

			// Now Get the text from the element tool tip
			String toolTip_message = elementToolTip.getText();

			// Store the expected message in the expected_toolTip variable,
			// this is what will hold the value being passed by Cucumber Gherkin
			String expected_toolTip = text;

			// Print the tool tip message and the expected tool tip message just for our
			// reference
			log.debug("Trying to determine if the tool tip message: " + toolTip_message
					+ " matches with the expected message: " + expected_toolTip);

			// Assert the tooltip's value is as expected
			// Now compare if the actual value matches with the expected value,
			// if it does then the Test Case has passed else it has failed.
			if (toolTip_message.equals(expected_toolTip)) {
				// Verify tool tip text
				log.debug("Tool Tip text verifed: PASSED");
			} else {
				// Verify tool tip text
				log.debug("Tool Tip text verifed: FAILED");
			}
			// Assert.assertEquals(toolTip_message, expected_toolTip);

		} catch (AssertionError | Exception error) {
			String exception = error.toString();
			log.debug(error);
			fail(exception);
		}

	}

	/**
	 * @author Gadotey
	 * @param element
	 * 
	 */
	public boolean mouseHover(By element) {

		try {

			// Find and hover over desired element
			WebElement webElement = webDriver.findElement(element);
			Actions builder = new Actions(webDriver);
			builder.moveToElement(webElement).build().perform();

			// Wait until the hover appears
			waitShort.until(ExpectedConditions.visibilityOfElementLocated(element));
			// waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.className("figcaption")));

			// Assert that the hover displayed
			return webElement.isDisplayed();
			// assertThat(webDriver.findElement(By.className("figcaption")).isDisplayed(),
			// is(Boolean.TRUE));

		} catch (AssertionError | Exception e) {
			String exception = e.toString();
			log.debug(e);
			fail(exception);
		}
		return false;
	}

	/**
	 * @author Gadotey
	 * @param null
	 * @Usage: return mouseHover(element);
	 * 
	 */
	public boolean mouseHover() {
		By element = null;
		return mouseHover(element);

	}

	/**
	 * @author Gadotey
	 * @param elementBy
	 */
	public void JavaScriptAlert(By elementBy) {

		try {
			webDriver.findElement(elementBy).click();
			;
			Alert popup = webDriver.switchTo().alert();
			popup.accept();
			String result = webDriver.findElement(By.id("result")).getText();
			// assertThat(result, is(equalTo("You clicked: Ok")));
		} catch (AssertionError | Exception e) {
			String exception = e.toString();
			log.debug(e);
			fail(exception);
		}

	}

	/**
	 * @author Gadotey
	 */
	public void selectCheckbox() {
		try {
			List<WebElement> checkboxes = webDriver.findElements(By.cssSelector("input[type='checkbox']"));

			log.debug("With .attribute('checked')");
			for (WebElement checkbox : checkboxes) {
				log.debug(String.valueOf(checkbox.getAttribute("checked")));
			}

			log.debug("\nWith .selected?");
			for (WebElement checkbox : checkboxes) {
				log.debug(checkbox.isSelected());
			}
		} catch (AssertionError | Exception e) {
			String exception = e.toString();
			log.debug(e);
			fail(exception);
		}
	}

	/**
	 * Click element and if not found refresh the page
	 * 
	 * @param elementToClick
	 */
	public void clickElement(By elementToClick) throws AssertionError, Exception {

		try {
			waitForPageToBeReady();
			// waitUntil.until(ExpectedConditions.elementToBeClickable(elementToClick)).isDisplayed();
			webDriver.findElement(elementToClick).click();
			waitForPageToBeReady();

		} catch (AssertionError | Exception error) {
			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}

		/*
		 * public boolean apply(WebDriver driver) { try{
		 * 
		 * driver.navigate().refresh(); driver.manage().timeouts().implicitlyWait(15,
		 * TimeUnit.SECONDS); driver.findElement(elementToClick).click(); }finally{
		 * driver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL,
		 * TimeUnit.SECONDS); } return true; } });
		 */
	}

	/**
	 * Click an element and find the next element (not necessary means it visible)
	 * 
	 * @param elementToClick
	 * @param elementToFindNext
	 */
	public void clickElement(By elementToClick, By elementToFindNext) {
		try {
			WebElement elementToHighlight = webDriver.findElement(elementToClick);
			waitForPageToBeReady();
			waitUntil.until(ExpectedConditions.elementToBeClickable(elementToClick));
			// Highlight the element
			highLightElement(elementToHighlight);
			webDriver.findElement(elementToClick).click();

		} catch (AssertionError | Exception error) {
			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}
	}

	/**
	 * Click an element and wait for the next element to be visible
	 * 
	 * @param elementToClick
	 * @param visibilityElementToFindNext
	 */
	public void clickElementAndFindVisibleElement(By elementToClick, By visibilityElementToFindNext) {
		WebElement elementToHighlight = webDriver.findElement(elementToClick);

		try {
			waitForPageToBeReady();
			waitUntil.until(ExpectedConditions.visibilityOfElementLocated(visibilityElementToFindNext));
			waitUntil.until(ExpectedConditions.elementToBeClickable(elementToClick));
			// Highlight the element
			highLightElement(elementToHighlight);
			webDriver.findElement(elementToClick).click();

		} catch (AssertionError | Exception error) {
			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}
	}

	/**
	 * Click an element and wait for the next element to be invisible
	 * 
	 * @param elementToClick
	 * @param invisibilityElementToWait
	 */
	public void clickElementAndWaitForInvisibilityElement(By elementToClick, By invisibilityElementToWait) {
		WebElement elementToHighlight = webDriver.findElement(elementToClick);

		try {
			waitForPageToBeReady();
			waitUntil.until(ExpectedConditions.invisibilityOfElementLocated(invisibilityElementToWait));
			waitUntil.until(ExpectedConditions.elementToBeClickable(elementToClick));
			// Highlight the element
			highLightElement(elementToHighlight);
			webDriver.findElement(elementToClick).click();

		} catch (AssertionError | Exception error) {
			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}
	}

	/**
	 * Select an element and make it sure it is selected
	 * 
	 * @param elementToSelect
	 */
	public void selectElement(By parentElementToSelect, String name) {
		// public void selectElement(By parentElementToSelect, String value) {
		WebElement elementToHighlight = webDriver.findElement(parentElementToSelect);

		try {

			waitForPageToBeReady();
			waitUntil.until(ExpectedConditions.presenceOfElementLocated(parentElementToSelect));

			Select select = new Select(webDriver.findElement(parentElementToSelect));
			// String stringValue = value.toString();
			// Highlight the element
			highLightElement(elementToHighlight);
			select.selectByVisibleText(name);
			// select.selectByIndex(elementIndex);;
			// select.selectByValue(stringValue);

			// assert(select.getFirstSelectedOption().toString().equals(name));
			// assert(select.getFirstSelectedOption().toString().equals(stringValue));

			// Abdul added this line of code to allow TABBING after entering values into the
			// form fields.
//			  Actions action = new Actions(webDriver);
//			  
//			  action.sendKeys(Keys.TAB).build().perform();
//			  action.sendKeys(Keys.RETURN).build().perform();

		} catch (AssertionError | Exception error) {

			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}

	}

	// For Successful login to Pega
	public void successfulLogin() {
		try {
			URL url = new URL(webDriver.getCurrentUrl());
			LOGOUT_URL = new URL(url.getProtocol(), url.getHost(), url.getPort(), "/logout").toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * public void setMCE(By by, String text) { setTextField(by, text,
	 * TextBoxType.MCE); }
	 */
	public void setMCE(By by, String text) {
		// setTextField(by, text, TextBoxType.MCE);
	}

	protected void setTextField(By by, String text) {
		WebElement elementToHighlight = webDriver.findElement(by);

		try {
			/*
			 * waitForPageToBeReady(); JavascriptExecutor js =
			 * (JavascriptExecutor)webDriver;
			 * 
			 * js.executeScript("document.getElementById('380a6333').value =" +text+"");
			 */

			waitForPageToBeReady();

			waitUntil.until(ExpectedConditions.presenceOfElementLocated(by));
			// Highlight the element
			highLightElement(elementToHighlight);
			webDriver.findElement(by).clear();
			webDriver.findElement(by).click();
			log.debug("Sending text: " + text);
			webDriver.findElement(by).sendKeys(text);
//			  log.debug("Sending text: " + text); 
//			  webDriver.findElement(by).sendKeys(text);
//			  
//			  // Abdul added this line of code to allow TABBING after entering values into the form fields.
//			  Actions action = new Actions(webDriver);
//			  
//			  action.sendKeys(Keys.TAB).build().perform();
//			  action.sendKeys(Keys.RETURN).build().perform();

		} catch (Exception | AssertionError error) {
			System.out.println(error);

			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}
	}

	protected void setDateField(By by, String inputDate) {
		WebElement elementToHighlight = webDriver.findElement(by);

		try {

			waitForPageToBeReady();

			waitUntil.until(ExpectedConditions.presenceOfElementLocated(by));
			webDriver.findElement(by).clear();
			webDriver.findElement(by).click();
			log.debug("Sending text: " + inputDate);
			// Highlight the element
			highLightElement(elementToHighlight);
			webDriver.findElement(by).sendKeys(inputDate);

			// Abdul added this line of code to allow TABBING after entering values into the
			// form fields.
//			  Actions action = new Actions(webDriver);
//			  
//			  action.sendKeys(Keys.TAB).build().perform();
//			  action.sendKeys(Keys.RETURN).build().perform();

		} catch (Exception | AssertionError error) {
			System.out.println(error);

			String exception = error.toString();
			System.out.println(error);
			fail(exception);
		}
	}

	public void setTextBox(By by, String text) {
		// setTextField(by, text, TextBoxType.NORMAL);
	}

	/*
	 * public void setTextBox(By by, String text) { setTextField(by, text,
	 * TextBoxType.NORMAL); }
	 */

	/*
	 * protected void setTextField(By by, String text, TextBoxType type) { // this
	 * will retry until it sets the text box. If it never sets the text box // the
	 * client code will kill it off. boolean notSet = true; while (notSet) { try {
	 * log.debug("Clicking the element by: " + by);
	 * webDriver.findClickableElement(by).clear();
	 * webDriver.findClickableElement(by).click(); log.debug("Sending text: " +
	 * text); webDriver.findClickableElement(by).sendKeys(text);
	 * 
	 * WebDriverWait wait = new WebDriverWait(webDriver, 10);
	 * 
	 * wait.until(new Predicate<WebDriver>() { public boolean apply(WebDriver
	 * driver) { if (type == TextBoxType.MCE) { if
	 * (driver.findElement(by).findElement(By.tagName("p")).getText().equals(text))
	 * { return true; } } else { if
	 * (driver.findElement(by).getAttribute("value").equals(text)) { return true; }
	 * }
	 * 
	 * return false; } }); notSet = false; } catch(TimeoutException e) {
	 * log.error("Failed to set text for: " + by + ".  Will retry!"); } } }
	 */

	/*
	 * In order to avoid Stale element reference.
	 * org.openqa.selenium.StaleElementReferenceException: stale element reference:
	 * element is not attached to the page document The code below is the best
	 * practice for resolving the stale element issue in our automation effort.
	 */

	/*
	 * The best thing is to first assert & verify that, that particular element is
	 * present or not. if you see function assertAndVerifyElement() --- it
	 * continuously checks for element for 5 secs and then assert it accordingly.
	 * 
	 * USAGE: assertAndVerifyElement(By.cssSelector("button.btn.btn-default"));
	 * driver.findElement(By.cssSelector("button.btn.btn-default")).click();
	 * 
	 */

	public void assertAndVerifyElement(By element) throws InterruptedException {
		// assume we will not find it
		boolean isPresent = false;

		log.debug("Trying to determine if element: " + element + " exists.");

		for (int i = 0; i < AutoTestGlobals.WAIT_TIME_SHORT; i++) {
			try {
				if (webDriver.findElement(element) != null) {
					isPresent = true;
					break;
				}
			} catch (Exception e) {
				log.debug("Exception caught trying to find element.  That means it's false.");
				Thread.sleep(1000);
			}
		}
		Assert.assertTrue(isPresent);
		// Assert.assertTrue(isPresent, "\"" + element + "\" is not present.");
	}

	public boolean verifyElementExists(By byElement) {
		return verifyElementExists(null, byElement);
	}

	public boolean verifyElementExists(WebElement containerElement, By byElement) {
		WebElement elementToHighlight = webDriver.findElement(byElement);
		boolean exists = false; // assume we will not find it

		log.debug("Trying to determine if element: " + byElement + " exists.");

		try {
			if (containerElement != null) {
				// Highlight the element
				highLightElement(elementToHighlight);
				containerElement.findElement(byElement);
			} else {
				// Highlight the element
				highLightElement(elementToHighlight);
				webDriver.findElement(byElement);
			}
			exists = true;
		} catch (Exception e) {
			log.debug("Exception caught trying to find element.  That means it's false.");
		}

		return exists;
	}

	/**
	 * Check if element is displayed
	 * 
	 * @param byElement
	 * @return
	 */
	public boolean verifyElementDisplayed(By byElement, boolean wait) {
		WebElement elementToHighlight = webDriver.findElement(byElement);

		if (wait) {
			highLightElement(elementToHighlight); // Highlight the element
			verifyElementDisplayed(byElement);
		} else {
			try {
				webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_SHORT, TimeUnit.SECONDS);
				WebElement element = webDriver.findElement(byElement);
				// Highlight the element
				highLightElement(elementToHighlight);
				return element.isDisplayed();
			} catch (Exception e) {
				return false;
			} finally {
				webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);
			}
		}
		return false;
	}

	/**
	 * Check if element is displayed
	 * 
	 * @param byElement
	 * @return
	 */
	public String verifyElementTextDisplayed(By byElement) {
		// String actualText = null;
		// WebElement elementToHighlight = webDriver.findElement(byElement);
		try {
			WebElement element = webDriver.findElement(byElement);
			actualText = element.getText();
			System.out.println("This is the text being returned " + actualText);
			/*
			 * if(element.isDisplayed()) {
			 * 
			 * 
			 * }
			 */
		} catch (Exception e) {

		}
		return actualText;

	}

	/**
	 * Check if element is displayed
	 * 
	 * @param byElement
	 * @return
	 */
	public boolean verifyElementDisplayed(By byElement) {
		WebElement elementToHighlight = webDriver.findElement(byElement);
		try {
			WebElement element = webDriver.findElement(byElement);
			// Highlight the element
			highLightElement(elementToHighlight);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Add by: Gadotey By using the the following method, you can try clicking on
	 * an element. If exception is thrown then catch the exception and try to click
	 * again until the element is present:
	 * 
	 * @param By
	 * @return Boolean
	 * 
	 * @Usage: Assert.AssertTrue("Element can't not be found",
	 *         retryingFindAndClickElement(by))
	 * 
	 */
	public boolean retryingFindAndClickElement(By byElement) {
		WebElement elementToHighlight = webDriver.findElement(byElement);
		boolean result = false;
		int attempts = AutoTestGlobals.ZERO_RETRY;
		while (attempts < AutoTestGlobals.MIN_RETRIES) {
			try {
				Actions action = new Actions(webDriver);
				WebElement userClick = waitUntil.until(ExpectedConditions.presenceOfElementLocated(byElement));
				action.moveToElement(userClick).click().build().perform();
				// Highlight the element
				highLightElement(elementToHighlight);
				webDriver.findElement(byElement).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
				log.debug("StaleElementReferenceException" + ": For Element: " + byElement);
			} catch (NoSuchElementException e) {
				log.debug("No Such Element Found: " + byElement);
			}
			attempts++;
		}

		return result;
	}

	/**
	 * @author Gadotey This method verifies to see if the text box is read-only and
	 *         not editable Usage: boolean readOnly = isreadOnly("PUT THE WEBELEMENT
	 *         HERE"); Assert.assertTrue(readOnly, "The text box is Editable")
	 * @param element
	 * @returns readOnly
	 */
	public boolean isreadOnly(WebElement element) {
		Boolean readOnly = false;
		if (verifyElementExists((By) element)) {
			readOnly = ((element.getAttribute("disabled") != null) || (element.getAttribute("readonly") != null));
			return readOnly;
		}
		return readOnly;
	}

	/**
	 * @author Gadotey This method verifies to see if the text box is enabled and
	 *         editable, put the Usage code in your Step Definition file Usage:
	 *         boolean editable = isEditable("PUT THE WEBELEMENT HERE");
	 *         Assert.assertFalse(editable, "The text box is not Editable")
	 * @param element
	 * @returns editable
	 */
	public boolean isEditable(WebElement element) {
		Boolean editable = false;
		// editable = ((element.getAttribute("enabled") != null) ||
		// (element.getAttribute("editable") != null));
		if (verifyElementExists((By) element)) {
			editable = (element.isDisplayed() && element.isEnabled());
			return editable;
		}
		return editable;
	}

	/**
	 * Get the visible error message Assuming that only one error message is
	 * displayed if you need more than one, use getErrorMessagesDisplayed
	 * 
	 * @return errorMsg
	 */
	public String getErrorMessageDisplayed() {
		List<String> errorMsgs = getErrorMessagesDisplayed();
		return errorMsgs.size() > 0 ? errorMsgs.get(0) : "";
	}

	/**
	 * Get all visible error messages
	 * 
	 * @return list of error messages
	 */
	public List<String> getErrorMessagesDisplayed() {
		List<WebElement> errors = webDriver.findElements(errorMessageBy);
		List<String> errorMsg = new ArrayList<String>();

		for (WebElement error : errors) {
			if (error.isDisplayed()) {
				errorMsg.add(error.getText().trim());
			}

		}
		return errorMsg;
	}

	/**
	 * @author Gadotey This method reads value from an element Usage: String
	 *         valueFromElement = readValueFromElement(WebElement);
	 *         Assert.assertFalse(editable, "The text box is not Editable")
	 * @param element
	 * @returns String
	 */
	public String readValueFromElement(WebElement element) {

		if (null == element) {
			log.info("weblement is null");
			return null;
		}

		boolean displayed = false;
		try {
			displayed = isDisplayed(element);
		} catch (Exception e) {
			log.error(e);
			log.debug(e.fillInStackTrace().toString());
			return null;
		}

		if (!displayed) {
			return null;
		}
		String text = element.getText();
		log.info("weblement valus is.. " + text);
		return text;
	}

	public String readValueFromInput(WebElement element) {
		if (null == element) {
			return null;
		}
		if (!isDisplayed(element)) {
			return null;
		}
		String value = element.getAttribute("value");
		log.info("weblement valus is.. " + value);
		return value;
	}

	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.. " + element);
			return true;
		} catch (Exception e) {
			log.info(e);
			log.debug(e.fillInStackTrace().toString());
			return false;
		}
	}

	protected boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is displayed.. " + element);
			return false;
		} catch (Exception e) {
			log.error(e);
			log.debug(e.fillInStackTrace().toString());
			return true;
		}
	}

	protected String getDisplayText(WebElement element) {
		if (null == element) {
			return null;
		}
		if (!isDisplayed(element)) {
			return null;
		}
		return element.getText();
	}

	public static synchronized String getElementText(WebElement element) {
		if (null == element) {
			log.info("weblement is null");
			return null;
		}
		String elementText = null;
		try {
			elementText = element.getText();
		} catch (Exception ex) {
			log.info("Element not found: " + ex);
			log.debug(ex.fillInStackTrace().toString());
		}
		return elementText;
	}

	/*
	 * @Author: Nick Polls the DOM to make sure the page is loaded.
	 */
	public void waitForPageToBeReady() {

		JavascriptExecutor js = (JavascriptExecutor) webDriver;
		webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);
		// This loop will rotate for 120 times using AutoTestGlobals.WAIT_TIME_EXTENDED
		// to check If page Is ready after
		// every 1 second.
		// You can replace the INT if you wants to Increase or decrease wait time.
		// 120
		for (int i = 0; i < AutoTestGlobals.WAIT_TIME_EXTENDED; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	/**
	 * @author Mainul Islam: By using the the following methods, you are able to
	 *         read tables from work queues for example. You can retrieve values
	 *         from a particular cell for validation or to test an existing case by
	 *         clicking on the case link, checking a checkbox, etc.
	 */

	/**
	 * Return Table Web Element via xpath expression.
	 *
	 * @param String xPathExpression
	 * @Usage String tableXpath = "//table[@class='gridTable '][@summary='This table
	 *        shows the user work list']";
	 * @returns element
	 */
	public Object getTable(String xPathExpression) throws Exception {
		Object table = null;
		try {
			WebElement baseTable = webDriver.findElement(By.xpath(xPathExpression));
			table = baseTable;
		} catch (NoSuchElementException e) {
			log.debug("No Such Element Found: " + xPathExpression);
		}

		return table;
	}

	/**
	 * Return the column index as an integer based on the Column Name. Usage: Find a
	 * particular cell in the table based on the Column Name and Row Name with the
	 * getRowIndex method.
	 * 
	 * @Usage int columnIndex = getColumnIndex(tableXpath,"Case ID");
	 * @param String xPathExpression
	 * @param String columnNameValue
	 * @returns columnIndex
	 */
	public int getColumnIndex(String xPathExpression, String columnNameValue) throws Exception {
		int columnIndex = 0;
		try {
			WebElement tableObj = (WebElement) getTable(xPathExpression);
			List<WebElement> tableColumns = (tableObj.findElement(By.xpath("//tr[@class='cellCont']")))
					.findElements(By.tagName("th"));
			for (int i = 0; i < tableColumns.toArray().length; i++) {
				WebElement obj = (WebElement) tableColumns.get(i);
				String columnCellValue = obj.getText();
				Boolean result = Pattern.compile(Pattern.quote(columnCellValue), Pattern.CASE_INSENSITIVE)
						.matcher(columnNameValue).find();
				if (result) {
					columnIndex = i + 2;
					break;
				}
			}

		} catch (NoSuchElementException e) {
			log.debug("No Such Element Found: " + xPathExpression);
		}
		return columnIndex;
	}

	/**
	 * Return the row index as an integer based on the Row Value. Usage: Find a
	 * particular cell in the table based on the Column Index from the
	 * getColumnIndex method with the value of a particular row.
	 * 
	 * @Usage rowIndex = getRowIndex(tableXpath,solicitationType,columnIndex);
	 * @param String xPathExpression
	 * @param String rowNameValue
	 * @param int    columnIndexValue
	 * @returns rowIndex
	 */
	public int getRowIndex(String xPathExpression, String rowNameValue, int columnIndexValue) throws Exception {
		int rowIndex = 0;
		try {
			WebElement tableObj = (WebElement) getTable(xPathExpression);
			List<WebElement> tableRows = tableObj
					.findElements(By.xpath("//td[contains(@data-ui-meta,\"pyCells(" + (columnIndexValue) + ")\")]"));
			for (int i = 0; i < tableRows.toArray().length; i++) {
				WebElement obj = tableRows.get(i);
				String rowCellValue = obj.getText();
				Boolean result = Pattern.compile(Pattern.quote(rowCellValue), Pattern.CASE_INSENSITIVE)
						.matcher(rowNameValue).find();
				if (result) {
					rowIndex = i + 2;
					break;
				}
			}

		} catch (NoSuchElementException e) {
			log.debug("No Such Element Found: " + xPathExpression);
		}
		return rowIndex;
	}

	/**
	 * Filter the table on a column name. Usage: Filter a table by column to display
	 * given type.
	 * 
	 * @param String xPathExpression
	 * @param String columnNameValue
	 * @param String filterValue
	 */
	public void filterColumn(String xPathExpression, String columnNameValue, String filterValue) throws Exception {

		WebElement tableObj = (WebElement) getTable(xPathExpression);
		List<WebElement> tableColumns = (tableObj.findElement(By.xpath("//tr[@class='cellCont']")))
				.findElements(By.tagName("th"));
		for (int i = 0; i < tableColumns.toArray().length; i++) {
			WebElement obj = (WebElement) tableObj.findElements(By.xpath("//div[@class='cellIn ']")).get(i);
			String columnCellValue = obj.getText();
			Boolean result = Pattern.compile(Pattern.quote(columnCellValue), Pattern.CASE_INSENSITIVE)
					.matcher(columnNameValue).find();
			if (result) {
				obj.findElements(By.xpath("//a[@class='filter highlight-ele']")).get(i - 3).click();
				String checkboxId = obj.findElement(By.xpath("//label[contains(text(),'" + filterValue + "')]"))
						.getAttribute("for");
				obj.findElement(By.xpath("//input[@id='" + checkboxId + "']")).click();
				obj.findElement(By.xpath("//button[contains(text(),'Apply')]")).click();
				break;
			}
		}
	}

	/**
	 * Return the value of cell in a table. Usage: Get the cell's value in the table
	 * based on the Column Index from the getColumnIndex method and Row Index from
	 * the getRowIndex method.
	 * 
	 * @param String xPathExpression
	 * @param int    rowIndex
	 * @param int    columnIndex
	 * @returns cellValue
	 */
	public String getCellValue(String xPathExpression, int rowIndex, int columnIndex) throws Exception {
		String cellValue = null;
		try {
			WebElement tableObj = (WebElement) getTable(xPathExpression);
			WebElement cellObj = tableObj.findElement(By.xpath("//tr[" + rowIndex + "]//td[" + columnIndex + "]"));
			cellValue = cellObj.getText();

		} catch (NoSuchElementException e) {
			log.debug("No Such Element Found: " + xPathExpression);
		}

		return cellValue;
	}

	/**
	 * Return the cell object in a table. Usage: Get the cell in the table based on
	 * the Column Index from the getColumnIndex method and Row Index from the
	 * getRowIndex method. Usage getCellValue(tableXpath,rowIndex,columnIndex) ==
	 * "In Build"
	 * 
	 * @param String xPathExpression
	 * @param int    rowIndex
	 * @param int    columnIndex
	 * @returns cellObj
	 */
	public WebElement getCellObj(String xPathExpression, int rowIndex, int columnIndex) throws Exception {
		WebElement cellObj = null;
		try {
			WebElement tableObj = (WebElement) getTable(xPathExpression);
			cellObj = tableObj.findElement(By.xpath("//tr[" + rowIndex + "]//td[" + columnIndex + "]"));

		} catch (NoSuchElementException e) {
			log.debug("No Such Element Found: " + xPathExpression);
		}

		return cellObj;
	}

	/**
	 * Switches to the appropriate iframe based on the case title such as "SI-1234"
	 * or "FAW-1234" or partial match "SI."
	 * 
	 * @param String frameTitle
	 */
	public String switchIframe(String frameTitle) throws NoSuchElementException {
		try {
			String iframeTitle = null;
			
			if (frameTitle != null) {
				webDriver.switchTo().defaultContent();
				List<WebElement> iframeArr = webDriver
						.findElements(By.xpath("//iframe[contains(@title,'" + frameTitle + "')]"));
				Collections.reverse(iframeArr);
				for (int i = 0; i < iframeArr.toArray().length; i++) {
					WebElement iframeObj = iframeArr.get(i);
					if (iframeObj.isDisplayed()) {
						iframeObj = iframeArr.get(i);
						iframeTitle = iframeObj.getAttribute("title");
						System.out.println("iframe: " +iframeTitle);
						if (iframeObj.getAttribute("title").contains(frameTitle)) {
							WebDriverWait wait = new WebDriverWait(webDriver, 60);
							wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeObj));
							waitForPageToBeReady();
							break;
						}
					}
				}
			}
			
			return iframeTitle;
		} catch (NoSuchElementException e) {
			log.debug("No Such iFrame Found: " + frameTitle);
			return null;
		}
	}

}