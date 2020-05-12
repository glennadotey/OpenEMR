/**
 * @author Gadotey
 *
 * 
 */
package com.xsoftqa.autotest.stepdefinitions.PatientScheduling;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.xsoftqa.autotest.pageobjects.Main.MainDashboardPage;
import com.xsoftqa.autotest.pageobjects.PatientScheduling.NewPatientPage;
import com.xsoftqa.autotest.stepdefinitions.StepDefinitionsBase;
import com.xsoftqa.autotest.stepdefinitions.TestScenarioSession;
import com.xsoftqa.autotest.stepdefinitions.Main.MainDashboardStepDefs;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Gadotey
 *
 */
public class NewPatientStepDefs extends StepDefinitionsBase{

	private static final Logger log = LogManager.getLogger(NewPatientStepDefs.class);
	private TestScenarioSession testScenarioSession = TestScenarioSession.getInstance();
	
	WebDriver webDriver = null;
	NewPatientPage newPatient = null;
	
	// Creating the Constructor for the step definition
	public NewPatientStepDefs() throws Throwable{
		webDriver = getWebDriver();
		newPatient = new NewPatientPage(webDriver);
	
	}
	
	@After("@main-dashboard")
	public void tearDown(Scenario scenario) {     
		super.tearDownTest(scenario);
	}
	
	@Given("^I am logged in as a receptionist$")
	public void i_am_logged_in_as_a_receptionist() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I navigate to the Flow Board$")
	public void i_navigate_to_the_Flow_Board() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I select \"([^\"]*)\" from the first drop down list$")
	public void i_select_from_the_first_drop_down_list(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^I should only see the \"([^\"]*)\" and the following columns:$")
	public void i_should_only_see_the_and_the_following_columns(String arg1, DataTable arg2) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// For automatic transformation, change DataTable to one of
		// List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		// E,K,V must be a scalar (String, Integer, Date, enum etc)
		throw new PendingException();
	}

}
