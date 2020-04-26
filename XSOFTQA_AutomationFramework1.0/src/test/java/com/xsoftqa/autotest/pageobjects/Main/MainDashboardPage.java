/**
 * 
 */
package com.xsoftqa.autotest.pageobjects.Main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.xsoftqa.autotest.pageobjects.PageObjectBase;

/**
 * @author Abdul
 *
 */
public class MainDashboardPage extends PageObjectBase{

	public MainDashboardPage(WebDriver driver) {
		super(driver);
        // wait for the dialog to appear
        waitNormal.until(ExpectedConditions.visibilityOfElementLocated(dialogBy));
	}
	
	private By calendarMenuBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Calendar')]")
	private WebElement calendarMenuElement;
	
	private By usernameBy = By.ByXPath.xpath("//div[@class='menuSection userSection']//div[@id='username']");
	@FindBy (how = How.XPATH, using = "//div[@class='menuSection userSection']//div[@id='username']")
	private WebElement usernameElement;
	
	private By addStageBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	private By dialogBy = By.ByXPath.xpath("//div[contains(text(),'Calendar')]");
	
	public boolean iscalendarMenutDisplay(){
		if (verifyElementDisplayed(calendarMenuBy)){
			highLightElement(calendarMenuElement);
			return true;
		}
		return false;
	}
	
	public boolean isUsernameDisplay(){
		if (verifyElementDisplayed(usernameBy)){
			highLightElement(usernameElement);
			return true;
		}
		return false;
	}
	
	public void clickAddStage() {
        webDriver.findElement(addStageBy).click();
    }
	
	public String setVisibleAction(String actualActionMenu) {
        if (webDriver.findElement(calendarMenuBy).isDisplayed()){
        	actualActionMenu = calendarMenuElement.getText();
        }
		return actualActionMenu;
    }
	
	public String setNotVisibleAction(String actualNotVisibleMenu) {
        if (webDriver.findElement(calendarMenuBy).isDisplayed()){
        	actualNotVisibleMenu = calendarMenuElement.getText();
        }
		return actualNotVisibleMenu;
    }
	
	public void clickMenu() {
        webDriver.findElement(calendarMenuBy).click();
    }

}
