/**
 * @author Gadotey
 *
 * 
 */
package com.xsoftqa.autotest.pageobjects.PatientScheduling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.xsoftqa.autotest.pageobjects.PageObjectBase;

/**
 * @author Gadotey
 *
 */
public class NewPatientPage extends PageObjectBase{
	
	public NewPatientPage(WebDriver driver) {
		super(driver);
        // wait for the dialog to appear
       // waitNormal.until(ExpectedConditions.visibilityOfElementLocated(dialogBy));
	}

}
