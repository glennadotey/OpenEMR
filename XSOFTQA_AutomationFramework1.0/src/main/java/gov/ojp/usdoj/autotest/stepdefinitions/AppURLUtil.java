package gov.ojp.usdoj.autotest.stepdefinitions;

import gov.ojp.usdoj.autotest.dataGenerator.AutoTestServiceManager;

/**
 * @author tahiraka
 *
 */

public class AppURLUtil {
	

    
   public String getDCMURL() {
        return AutoTestServiceManager.appURL;
    }
   
   	public String getAdminURL() {
       return AutoTestServiceManager.appURL + "/admin";
   	}
    
    public String getBrowseQuestions(){
        return AutoTestServiceManager.appURL + "/questions?filterID=all";
    }
    
    public static String getBudgetDetailsURL() {
    	//JOIEwSHzRrZSo7An7TTvFQ%5B%5B*/!STANDARD
        return AutoTestServiceManager.appURL + "/JOIEwSHzRrZSo7An7TTvFQ%5B%5B*/!STANDARD";    
    }
    
    public String getMergeDeniedTabURL(long userId) {
        return AutoTestServiceManager.appURL + "/manage-workspaces.jspa?tab=denied&userID=" + userId;
    }

    public String getDataGeneratorURL() {
        return AutoTestServiceManager.appURL + "/auto-test-setup!input.jspa";
    }

    public String getGroupURL(long groupID) {
        return AutoTestServiceManager.appURL + "/groups/" + groupID;
    }
    
    public String getGroupActivityURL(long groupID) {
        return AutoTestServiceManager.appURL + "/groups/" + groupID + "/activity";
    }
    
    public String getGroupOverviewURL(long groupID) {
        return AutoTestServiceManager.appURL + "/groups/" + groupID + "/overview";
    }
    
    public String getGroupContentURLWithItemView(long groupID){
        return AutoTestServiceManager.appURL + "/groups/" + groupID + "/content?itemView=detail";        
    }
    
    public String getSearchPageURL() {
        return AutoTestServiceManager.appURL + "/search.jspa?";
    }
        
    public String getSearchURL(){
        return AutoTestServiceManager.appURL + "/search.jspa";
    }
    
    public String getBrowseTagsURL(){
        return AutoTestServiceManager.appURL + "/tags.jspa";
    }
    
    public String getUserPreferences() {
        return AutoTestServiceManager.appURL + "/user-preferences!input.jspa";
    }

}
