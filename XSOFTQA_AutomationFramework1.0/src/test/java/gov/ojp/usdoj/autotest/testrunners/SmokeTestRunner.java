package com.xsoftqa.autotest.testrunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.xsoftqa.autotest.stepdefinitions.AutomatedTest;

/**
 *  This class is what gets inherited and gets the Cucumber smoke tests running. This is annotation driven and
 *  for a description of available Cucumber options,
 *  see http://cukes.info/api/cucumber/jvm/javadoc/cucumber/api/junit/Cucumber.Options.html.
 *
 *  For the sake of running this from outside the directory we need to specify the full path to source folder
 *
 * @author tahiraka
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@SmokeTest"}
        )
public class SmokeTestRunner extends AutomatedTest{

}
