/**
 * @author Gadotey
 *
 * 
 */
package gov.ojp.usdoj.autotest.pageobjects.PatientScheduling;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import gov.ojp.usdoj.autotest.pageobjects.PageObjectBase;

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
