/**
 * 
 */
package com.xsoftqa.autotest.stepdefinitions.Main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

//import com.xsoftqa.autotest.pageobjects.Main.MainDashboardPage;
import com.xsoftqa.autotest.pageobjects.Main.PatientDemographicsPage;
import com.xsoftqa.autotest.stepdefinitions.StepDefinitionsBase;
import com.xsoftqa.autotest.stepdefinitions.TestScenarioSession;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Abdul
 *
 */
public class PatientDemographicsStepDefs extends StepDefinitionsBase{
	
	private static final Logger log = LogManager.getLogger(PatientDemographicsStepDefs.class);
	private TestScenarioSession testScenarioSession = TestScenarioSession.getInstance();
	
	WebDriver webDriver = null;
	PatientDemographicsPage patientDemographics = null;
	
	// Creating the Constructor for the step definition
	public void PatientDemographicsStepDefs() throws Throwable{
		webDriver = getWebDriver();
		patientDemographics = new PatientDemographicsPage(webDriver);
	
	}
	
	@After("@main-dashboard")
	public void tearDown(Scenario scenario) {     
		super.tearDownTest(scenario);
	}
	
	
//	@Given("^I login as a receptionist$")
//    public void i_login_as_a_receptionist() throws Throwable {
//        throw new PendingException();
//    }
//
//    @When("^i navigate to the patient demographics$")
//    public void i_navigate_to_the_patient_demographics() throws Throwable {
//        throw new PendingException();
//    }
//
//    @Then("^I should see the following fields to enter the patient information$")
//    public void i_should_see_the_following_fields_to_enter_the_patient_information(DataTable args) throws Throwable {
//        throw new PendingException();
//    }
	
//	Given("^I login as a receptionist$", () -> {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	});
//
//	When("^i navigate to the patient demographics$", () -> {
//	    // Write code here that turns the phrase above into concrete actions
//	    throw new PendingException();
//	});
//
//	Then("^I should see the following fields to enter the patient information$", (DataTable arg1) -> {
//	    // Write code here that turns the phrase above into concrete actions
//	    // For automatic transformation, change DataTable to one of
//	    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//	    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//	    throw new PendingException();
//	});

}
