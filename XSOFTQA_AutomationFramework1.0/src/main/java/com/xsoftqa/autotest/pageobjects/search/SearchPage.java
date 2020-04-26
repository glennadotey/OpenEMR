/**
 * @author tahiraka
 *
 */

package com.xsoftqa.autotest.pageobjects.search;

import java.util.concurrent.TimeUnit;

import com.xsoftqa.autotest.AutoTestGlobals;
import com.xsoftqa.autotest.pageobjects.PageObjectBase;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends PageObjectBase {

    private By searchButtonBy = By.xpath(".//*[@id='j-search-form']/input[2]");
    private By searchTextBoxBy = By.id("ac8b84a7");
    private By searchResultTitleby = By.xpath(".//span[@class='j-search-result-title']");
    private By searchFundingApproverBy = By.xpath(".//*[@data-value='icidea']");
    private By sortByLastModifiedBy = By.xpath(".//*[@data-value='updatedDesc']");
    private String internalSearchXpath = ".//*[contains(text(), '%s')]";
    private By searchFundedAwardBy = By.xpath(".//*[@title='Search for Places']/span[@class='j-status']");
    private By searchAllFundedAwardsBy = By.xpath(".//*[@title='Show All places']");
    
    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }
    
    public boolean onSearchPage() {
        try {
            webDriver.findElement(searchButtonBy);
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }
    
    public void sendSearch(String searchText) {
        webDriver.findElement(searchTextBoxBy).sendKeys(searchText, Keys.RETURN);
    }
    
    public void sendSearch(String searchText, String resultName) {
        sendSearch(searchText);
        for (int t = 0; t < AutoTestGlobals.MIN_RETRIES; t++) {
            if(areSearchResultsPresent(resultName)) {
                break;
            }
            else {
                webDriver.findElement(searchButtonBy).click();
            }
        }        
    }
    
    public boolean areSearchResultsPresent(boolean expectedToFind) {
        try {
            webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_SHORT, TimeUnit.SECONDS);
            WebDriverWait wait = waitNormal;
            if (!expectedToFind) {
                wait = waitShort;
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultTitleby));
            return true;
        }
        catch(TimeoutException | NoSuchElementException e) {
            return false;
        }
        finally {
            webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);
        }
    }
        
    public boolean areSearchResultsPresent() {
        return areSearchResultsPresent(true);
    }
    
    public boolean areSearchResultsPresent(String subject) {
        try {
            webDriver.findElement(By.xpath(String.format(internalSearchXpath, subject)));
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }
    
    public boolean isSearchFundingApproverPresent() {
        try {
            webDriver.findElement(searchFundingApproverBy);
            return true;
        }
        catch(TimeoutException e) {
            return false;
        }
    }
    
    public void selectFundingApproverType() {
        webDriver.findElement(searchFundingApproverBy).click();
    }
    
    public void selectSortByLastModified() {
        webDriver.findElement(sortByLastModifiedBy).click();
    }
    
    public void selectSearchFundedAward() {
        for (int i = 1; i <= AutoTestGlobals.MIN_RETRIES; i++) {
            try{
                webDriver.findElement(searchFundedAwardBy).click();
                waitShort.until(ExpectedConditions.visibilityOfElementLocated(searchAllFundedAwardsBy));
                return;
            }catch(Exception e){
                log.info("Retry clicking Funded Award" + i);
            }
        }
        Assert.fail("failed to click Funded Award");
    }
}
