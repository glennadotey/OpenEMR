/**
 * 
 */
package gov.ojp.usdoj.autotest.pageobjects.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import gov.ojp.usdoj.autotest.pageobjects.PageObjectBase;

/**
 * @author Gadotey
 *
 */
public class MainDashboardPage extends PageObjectBase {

	public MainDashboardPage(WebDriver driver) {
		super(driver);
		// wait for the dialog to appear
		//waitNormal.until(ExpectedConditions.visibilityOfElementLocated(dialogBy));
	}

	private By calendarMenuBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Calendar')]")
	private WebElement calendarMenuElement;

	private By usernameBy = By.ByXPath.xpath("//div[@class='menuSection userSection']//div[@id='username']");
	@FindBy(how = How.XPATH, using = "//div[@class='menuSection userSection']//div[@id='username']")
	private WebElement usernameElement;

	private By addStageBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	private By dialogBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	
	private By firstNameBy = By.ByXPath.xpath("//span[contains(text(),'Billy')]");
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Billy')]")
	private WebElement firstNameElement;
	
	private By lastNameBy = By.ByXPath.xpath("//span[contains(text(),'Smith')]");
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Smith')]")
	private WebElement lastNameElement;
	
	private By pageTitleBy = By.ByXPath.xpath("//div[contains(text(),'Patient:')]");
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Patient:')]")
	private WebElement pageTitleElement;
	
	private By flowBoardMenuBy = By.ByXPath.xpath("//div[contains(text(),'Patient:')]");
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Patient:')]")
	private WebElement flowBoardeByElement;
	
	private By calendarMonthBy = By.ByXPath.xpath("//td[@class='tdMonthName-small']");
	@FindBy(how = How.XPATH, using = "//td[@class='tdMonthName-small']")
	private WebElement calendarMonthElement;
	
	//private By anySearchBoxBy = By.id("anySearchBox");
	
	public String getFirstName() {
		if (verifyElementDisplayed(firstNameBy)) {
			highLightElement(firstNameElement);
			return actualText = readValueFromElement(firstNameElement);
			
		}
		return actualText;
		
		
	}
	
//	public String getLastName() {
//		if (verifyElementDisplayed(lastNameBy)) {
//			highLightElement(lastNameElement);
//			//return actualText = readValueFromElement(lastNameElement);
//			log.debug("The Acutal value is ...  " + lastNameElement.getText());
//			return actualText = lastNameElement.getText();
//		}
//		return actualText;
		
//	}
	
	
	public boolean iscalendarMenuDisplay() {
		if (verifyElementDisplayed(calendarMenuBy)) {
			highLightElement(calendarMenuElement);
			return true;
		}
		return false;
	}
	
	public boolean iscalendarMonthDisplay() {
		if (verifyElementDisplayed(calendarMonthBy)) {
			highLightElement(calendarMonthElement);
			return true;
		}
		return false;
	}
	
	
	public boolean isflowBoardMenutDisplay() {
		if (verifyElementDisplayed(flowBoardMenuBy)) {
			highLightElement(flowBoardeByElement);
			return true;
		}
		return false;
	}

	public boolean isUsernameDisplay() {
		if (verifyElementDisplayed(usernameBy)) {
			highLightElement(usernameElement);
			return true;
		}
		return false;
	}

	public void clickAddStage() {
		webDriver.findElement(addStageBy).click();
	}

	public String setVisibleAction(String actualActionMenu) {
		if (webDriver.findElement(calendarMenuBy).isDisplayed()) {
			actualActionMenu = calendarMenuElement.getText();
		}
		return actualActionMenu;
	}

	public String setNotVisibleAction(String actualNotVisibleMenu) {
		if (webDriver.findElement(calendarMenuBy).isDisplayed()) {
			actualNotVisibleMenu = calendarMenuElement.getText();
		}
		return actualNotVisibleMenu;
	}

	public void clickMenu() {
		webDriver.findElement(calendarMenuBy).click();
	}
	
	public String getTitle() {
		return webDriver.findElement(pageTitleBy).getText();
		}

}
