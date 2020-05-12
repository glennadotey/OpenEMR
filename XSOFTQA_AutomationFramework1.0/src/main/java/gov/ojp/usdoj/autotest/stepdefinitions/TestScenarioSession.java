package gov.ojp.usdoj.autotest.stepdefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gov.ojp.usdoj.autotest.dataobjects.QuestionObject;


/**
 * @author tahiraka
 * {@code}: There is no connection between Step Definitions. They can be from any JAVA class
 *          and the there is no way to pass information between them. To solve this problem a singleton called 
 *          
 *
 */
public class TestScenarioSession {
	
    private TestScenarioSession() {
    	
    }
    
    private static TestScenarioSession instance = null;
    
    protected QuestionObject questionObject;

    // List of questions (mainly questions), use for searching questions
    //protected List<QuestionObject> questions = new ArrayList<QuestionObject>();
   
    protected String fundedAward = null;
    protected String gamID = null;
    protected String manualPassFail = null;
    
    protected List<Long> customStreams = new ArrayList<Long>();
    
    public static synchronized TestScenarioSession getInstance() {
        if (instance == null) {
            instance = new TestScenarioSession();
        }
        return instance;
    }

    public static void destroySession(){
        instance = null;
    }
    
    public String getFundedAward() {
        return fundedAward;
    }

    public void setFundedAward(String fundedAward) {
        this.fundedAward = fundedAward;
    }
    
    public String getGAMID() {
        return gamID;
    }

    public void setGAMID(String fundedAward) {
        this.gamID = gamID;
    }   

    public void setManualTestResult(String manualPassFail) {
        this.manualPassFail = manualPassFail;
    }
    
    public String getManualTestResult() {
        return manualPassFail;
    }
    

}
