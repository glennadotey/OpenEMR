package com.xsoftqa.autotest.stepdefinitions.Main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.DataTable;

/**
 * @author Abdul
 *
 */

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.xsoftqa.autotest.pageobjects.Main.MainDashboardPage;
import com.xsoftqa.autotest.stepdefinitions.StepDefinitionsBase;
import com.xsoftqa.autotest.stepdefinitions.TestScenarioSession;

public class MainDashboardStepDefs extends StepDefinitionsBase{
	
		private static final Logger log = LogManager.getLogger(MainDashboardStepDefs.class);
		private TestScenarioSession testScenarioSession = TestScenarioSession.getInstance();
		
		WebDriver webDriver = null;
		MainDashboardPage mainDashboard= null;
		
		// Creating the Constructor for the step definition
		public MainDashboardStepDefs() throws Throwable{
			webDriver = getWebDriver();
			mainDashboard = new MainDashboardPage(webDriver);
		}
		
		@After("@main-dashboard")
		public void tearDown(Scenario scenario) {     
			super.tearDownTest(scenario);
		}
		
		//This section for Administrator
		@Given("^I am logged in as an Adminstrator$")
		public void i_am_logged_in_as_an_Adminstrator() throws Throwable {
			webDriver.get(super.getContextRoot());
			// Enter username as "admin" and password as "123456789"
			mainDashboard.login(super.contextAdminUser, getContextAdminUserPassword());
		}

		@When("^I logged in successfully as an Admin$")
		public void i_logged_in_successfully_as_an_Admin() throws Throwable {
		    // Verifying if user has logged in successfully
		    mainDashboard.verifySuccessfulLogin();
		}

		
		@Then("^I can see my full name \"([^\"]*)\" \"([^\"]*)\" on the top right corner of the page$")
		public void i_can_see_my_full_name_on_the_top_right_corner_of_the_page(String expectedFirstName, String expectedLastName) throws Throwable {
		    // Verify that the admin full name  display on the top right corner of the page.
		   Assert.assertEquals("Admin first name does not match the actual first name", mainDashboard.getFirstName(), mainDashboard.getFirstName());
		   Assert.assertEquals("Admin last name does not match the actual last name", mainDashboard.getLastName(), mainDashboard.getLastName());
		}

		@And("^I can see the actions i can performed as follows as an Admin:$")
		public void i_can_see_the_actions_i_can_performed_as_follows_as_an_Admin(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		@And("^can see the months calender and today's date$")
		public void can_see_the_months_calender_and_today_s_date() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		//************************BEGINNING OF NEW SCENARIO****************************************
		@Given("^I am logged in as a Physician$")
		public void i_am_logged_in_as_a_Physician() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I logged in successfully as a Physician$")
		public void i_logged_in_successfully_as_a_Physician() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I can see the actions i can performed as follows as a Physician:$")
		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Physician(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		@Then("^can not see the following menus as a Physician:$")
		public void can_not_see_the_following_menus_as_a_Physician(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		//************************BEGINNING OF NEW SCENARIO****************************************
		@Given("^I am logged in as an Clinician$")
		public void i_am_logged_in_as_an_Clinician() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I logged in successfully as a Clinician$")
		public void i_logged_in_successfully_as_a_Clinician() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I can see the actions i can performed as follows as a Clinician:$")
		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Clinician(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		@Then("^can not see the following menus as a Clinician:$")
		public void can_not_see_the_following_menus_as_a_Clinician(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		//************************BEGINNING OF NEW SCENARIO****************************************
		@Given("^I am logged in as an Accountant$")
		public void i_am_logged_in_as_an_Accountant() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I logged in successfully as a Accountant$")
		public void i_logged_in_successfully_as_a_Accountant() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I can see the actions i can performed as follows as a Accountant:$")
		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Accountant(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		@Then("^can not see the following menus as a Accountant:$")
		public void can_not_see_the_following_menus_as_a_Accountant(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		//************************BEGINNING OF NEW SCENARIO****************************************
		@Given("^I am logged in as a Receptionist$")
		public void i_am_logged_in_as_a_Receptionist() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I logged in successfully as a Receptionist$")
		public void i_logged_in_successfully_as_a_Receptionist() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I can see the actions i can performed as follows as a Receptionist:$")
		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Receptionist(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		@Then("^can not see the following menus as a Receptionist:$")
		public void can_not_see_the_following_menus_as_a_Receptionist(DataTable arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    // For automatic transformation, change DataTable to one of
		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
		    throw new PendingException();
		}

		//************************BEGINNING OF NEW SCENARIO****************************************
		@Given("^I want to write a step with name(\\d+)$")
		public void i_want_to_write_a_step_with_name(int arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@When("^I check for the (\\d+) in step$")
		public void i_check_for_the_in_step(int arg1) throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I verify the POSSITIVE in step$")
		public void i_verify_the_POSSITIVE_in_step() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

		@Then("^I verify the NEGATIVE in step$")
		public void i_verify_the_NEGATIVE_in_step() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		    throw new PendingException();
		}

			
		// This section for Admnistrator
//		@Given("^I am logged in as an Adminstrator$")
//		public void i_am_logged_in_as_an_Adminstrator() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    //throw new PendingException();
//			// Navigate to the OpenEMR Login page by copying and pasting the URL into your browser of choice
//			//webDriver.get("https://demo.openemr.io/openemr");
//			webDriver.get(super.getContextRoot());
//			// Enter username as "admin" and password as "123456789"
//			mainDashborad.login(super.contextAdminUser, getContextAdminUserPassword());
//		}
//
//		@When("^I logged in successfully as an Admin$")
//		public void i_logged_in_successfully_as_an_Admin() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see my full name on the top right corner of the page$")
//		public void i_can_see_my_full_name_on_the_top_right_corner_of_the_page() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see the actions i can performed as follows as an Admin:$")
//		public void i_can_see_the_actions_i_can_performed_as_follows_as_an_Admin(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Then("^can see the months calender and today's date$")
//		public void can_see_the_months_calender_and_today_s_date() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Given("^I am logged in as a Physician$")
//		public void i_am_logged_in_as_a_Physician() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@When("^I logged in successfully as a Physician$")
//		public void i_logged_in_successfully_as_a_Physician() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see the actions i can performed as follows as a Physician:$")
//		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Physician(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Then("^can not see the following menus as a Physician:$")
//		public void can_not_see_the_following_menus_as_a_Physician(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Given("^I am logged in as an Clinician$")
//		public void i_am_logged_in_as_an_Clinician() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@When("^I logged in successfully as a Clinician$")
//		public void i_logged_in_successfully_as_a_Clinician() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see the actions i can performed as follows as a Clinician:$")
//		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Clinician(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Then("^can not see the following menus as a Clinician:$")
//		public void can_not_see_the_following_menus_as_a_Clinician(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Given("^I am logged in as an Accountant$")
//		public void i_am_logged_in_as_an_Accountant() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@When("^I logged in successfully as a Accountant$")
//		public void i_logged_in_successfully_as_a_Accountant() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see the actions i can performed as follows as a Accountant:$")
//		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Accountant(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Then("^can not see the following menus as a Accountant:$")
//		public void can_not_see_the_following_menus_as_a_Accountant(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Given("^I am logged in as a Receptionist$")
//		public void i_am_logged_in_as_a_Receptionist() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@When("^I logged in successfully as a Receptionist$")
//		public void i_logged_in_successfully_as_a_Receptionist() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I can see the actions i can performed as follows as a Receptionist:$")
//		public void i_can_see_the_actions_i_can_performed_as_follows_as_a_Receptionist(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Then("^can not see the following menus as a Receptionist:$")
//		public void can_not_see_the_following_menus_as_a_Receptionist(DataTable arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    // For automatic transformation, change DataTable to one of
//		    // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
//		    // E,K,V must be a scalar (String, Integer, Date, enum etc)
//		    throw new PendingException();
//		}
//
//		@Given("^I want to write a step with name(\\d+)$")
//		public void i_want_to_write_a_step_with_name(int arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@When("^I check for the (\\d+) in step$")
//		public void i_check_for_the_in_step(int arg1) throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I verify the POSSITIVE in step$")
//		public void i_verify_the_POSSITIVE_in_step() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}
//
//		@Then("^I verify the NEGATIVE in step$")
//		public void i_verify_the_NEGATIVE_in_step() throws Throwable {
//		    // Write code here that turns the phrase above into concrete actions
//		    throw new PendingException();
//		}


	

}
