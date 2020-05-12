package gov.ojp.usdoj.autotest.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import gov.ojp.usdoj.autotest.stepdefinitions.AutomatedTest;

/**
 * @author tahiraka
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        //CUCUMBER REGRESSION TEST TAGS . THIS IS INDEPENDENT OF THE JGITS SPECIFIC TESTS
        tags = {"@RegressionTest"})
public class RegressionTestRunner  extends AutomatedTest {
	
}
