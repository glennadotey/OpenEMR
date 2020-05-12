package com.xsoftqa.autotest.testrunners;

/**
 * @author Gadotey
 *
 */

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.xsoftqa.autotest.stepdefinitions.AutomatedTest;

/*
 *  This class is manual testing and what gets Cucumber running. The class itself is intentionally empty and does nothing,
 *  it's the class annotations that do the work. For a description of available Cucumber options,
 *  see http://cukes.info/api/cucumber/jvm/javadoc/cucumber/api/junit/Cucumber.Options.html.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
				  //******************************************************************************************************
				  // The following "tags" line is commented out to support running automated tests. To run
				  // tests by specific tags, uncomment the line and replace @default_test with the tag(s) you want to run.
				  //******************************************************************************************************

					//tags = {"@common","@groups","@merge", "@merge-data-setup", "@regression", "~@manual", "@test-seq-1"}
			
					tags = { "@OpenEMR", "@main-dashboard", "@test-seq-1" }
		
				  )
public class TestRunner extends AutomatedTest {
	
}