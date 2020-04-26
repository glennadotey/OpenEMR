package com.xsoftqa.autotest.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.xsoftqa.autotest.stepdefinitions.AutomatedTest;

/**
 * @author tahiraka
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        //CUCUMBER REGRESSION TEST TAGS . THIS IS INDEPENDENT OF THE JGITS SPECIFIC TESTS
        tags = {"@end2end"})
public class End2EndTestRunner  extends AutomatedTest {
	
}
