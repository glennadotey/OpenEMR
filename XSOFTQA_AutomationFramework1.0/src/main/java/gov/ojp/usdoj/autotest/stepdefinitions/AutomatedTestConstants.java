package gov.ojp.usdoj.autotest.stepdefinitions;

/**
 * @author tahiraka
 *
 */



public class AutomatedTestConstants {


    static final String CUCUMBER_FEATURES =  "src/test/resources/features/";

    static final String CUCUMBER_FORMAT_TYPE =  "pretty";

    static final String CUCUMBER_GLUE =  "gov.ojp.usdoj.autotest.stepdefinitions"; 

    static final String CUCUMBER_FORMAT_TEST_RESULTS_PATH =  "junit:target/cucumber-junit-report/test_results.xml";
    static final String CUCUMBER_JSON_PATH = "json:target/Cucumber.json";
    static final String CUCUMBER_JUNIT_PATH = "junit:target/cucumber-reports/Cucumber.xml";
    static final String CUCUMBER_REPORT_PATH = "html:target/cucumber-report-html/";
    static final String CUCUMBER_OUTPUT_DIRECTORY = "JGII_Automation/target/cucumber-reports/";
    static final boolean CUCUMBER_MONACHROME =  true;
    static final boolean CUCUMBER_DRY_RUN =  false;

    //Used by the masterthough report
    static final String JSON_REPORT_FILE = CUCUMBER_OUTPUT_DIRECTORY + "Cucumber.json";
    static final String DIR_OUTPUT_FILE = CUCUMBER_OUTPUT_DIRECTORY + "dirOutput.txt";
    
    // This is for LINUX PLATFORM
    /**static final String CUCUMBER_FEATURES =  "src/test/resources/features/";
    static final String CUCUMBER_FORMAT_TYPE =  "pretty";
    static final String CUCUMBER_GLUE =  "src/test/java/gov/ojp/usdoj/autotest/stepdefinitions";
    static final String CUCUMBER_FORMAT_TEST_RESULTS_PATH =  "junit:target/cucumber-junit-report/test_results.xml";
    static final String CUCUMBER_JSON_PATH = "json:target/cucumber-reports/Cucumber.json";
    static final String CUCUMBER_JUNIT_PATH = "junit:target/cucumber-reports/Cucumber.xml";
    static final String CUCUMBER_REPORT_PATH = "html:target/cucumber-reports";
    static final String CUCUMBER_OUTPUT_DIRECTORY = "JGII_Automation/target/cucumber-reports/";
    static final boolean CUCUMBER_MONACHROME =  true;
    static final boolean CUCUMBER_DRY_RUN =  false;*/

}
