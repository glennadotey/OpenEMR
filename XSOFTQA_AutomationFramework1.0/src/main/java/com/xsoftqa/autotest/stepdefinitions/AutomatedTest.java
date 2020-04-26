package com.xsoftqa.autotest.stepdefinitions;

/**
 * @author tahiraka
 *
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


import static com.xsoftqa.autotest.stepdefinitions.AutomatedTestConstants.*;
import static com.xsoftqa.autotest.stepdefinitions.AutomatedTestConstants.CUCUMBER_DRY_RUN;
import static com.xsoftqa.autotest.stepdefinitions.AutomatedTestConstants.CUCUMBER_MONACHROME;


/**
 *  This class is what gets inherited and gets the tests running. This is annotation driven and
 *  for a description of available Cucumber options,
 *  see http://cukes.info/api/cucumber/jvm/javadoc/cucumber/api/junit/Cucumber.Options.html.
 *
 *  For the sake of running this from outside the directory we need to specify the full path to source folder
 *
 */
//
@RunWith(Cucumber.class)
@CucumberOptions(features = {CUCUMBER_FEATURES}, 
		glue = {CUCUMBER_GLUE},
        format = {CUCUMBER_FORMAT_TYPE, CUCUMBER_FORMAT_TEST_RESULTS_PATH},
        plugin = {CUCUMBER_JSON_PATH,
                CUCUMBER_JUNIT_PATH,
                CUCUMBER_REPORT_PATH},
        monochrome = CUCUMBER_MONACHROME,
        dryRun = CUCUMBER_DRY_RUN)
public class AutomatedTest {

}
