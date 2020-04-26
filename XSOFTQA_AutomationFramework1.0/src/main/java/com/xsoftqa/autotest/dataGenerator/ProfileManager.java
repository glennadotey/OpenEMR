package com.xsoftqa.autotest.dataGenerator;

import com.xsoftqa.autotest.dataGenerator.ProfileBean;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
//import org.springframework.test.context.ContextConfiguration;



/**
 * Profile manager that retrieve and get user profile
 * @author: 		tahiraka
 * @version: 		1.0
 * @modified by:
 * @modified date:	 
 */

//@ContextConfiguration("classpath:cucumber.xml")
public class ProfileManager {
	
	private static final Logger logger = LogManager.getLogger(ProfileManager.class);

    //TODO: Seems strange that these are static AND they have setters
    public static HashMap<String, ProfileBean> profiles;
    private static String resourcesLocation;

    public enum TestProfile {
        AUTOTEST_OJP_DCM_ADMIN,
        DEFAULT_SERVICE_ADMIN_PROFILE,
        AUTOTEST_NO_ACCOUNT,
        NO_CLEARANCE,
        NO_CITIZENSHIP,
        NO_OJP_EMAIL,
        NOT_US,
        UNCLEARED,
        ANONYMOUS_USER; 
        
        public static TestProfile getTestProfile(String profileName) {
            for (TestProfile profile : values()) {
                if (profile.name().equalsIgnoreCase(profileName)) {
                    return profile;
                }
            }
            return null;
        }
    }

    /**
     * Get user cert path 
     * @param profile
     * @return path String
     */
    public static String getCertPath(TestProfile profile){
        
        ProfileBean bean = (ProfileBean)profiles.get(profile.name().toLowerCase());
        
        try {
            return findFile(bean.getCert(), new File(resourcesLocation));
        }
        catch (Exception e ) {
            logger.error("Unable to get the cert path for the profile" + profile.name());
            return "";
        }
    }
    
    /**
     * Get user cert path 
     * @param certName
     * @return path String
     */
    public static String getCertPath(String certName){        
        try {
            return findFile(certName, new File(resourcesLocation));
        }
        catch (Exception e ) {
            logger.error("Unable to get the cert path for the profile" + certName);
            return "";
        }
    }
      
    /**
     * Get userDN 
     * @param profile
     * @return userDN String
     */
    public static String getUserDN(TestProfile profile){
        ProfileBean bean = (ProfileBean)profiles.get(profile.name().toLowerCase());
        
        if(bean != null){
            return bean.getUserDN();
        }
        
       return "";
    }
    
    /**
     * Get user cert password
     * @param profile
     * @return
     */
    public static String getCertPassword(TestProfile profile){
        ProfileBean bean = (ProfileBean)profiles.get(profile.name().toLowerCase());
        
        if(bean != null){
            return bean.getCertPwd();
        }
        
       return "";
    }
    
    /**
     * Get user profile bean
     * @param profile
     * @return
     */
    public static ProfileBean getUserProfileBean(TestProfile profile){
        return (ProfileBean)profiles.get(profile.name().toLowerCase());
    
    }

    /**
     * Find the file in the directory and subdirectories
     * @param fileName
     * @param file
     * @return filePath String
     * @throws IOException
     */
    private static String findFile(String fileName, File file) throws IOException {
        String path = "";
        File[] list = file.listFiles();

        if ( list != null ) {
            for ( File f : list ) {
                if ( path != "" ) {
                    return path;
                }
                
                if ( fileName.equalsIgnoreCase(f.getName()) ) {
                    path = f.getCanonicalPath();
                    logger.info("Found the file" + path);
                    break;
                }
                else if ( f.isDirectory() && !f.getName().equals("features") ) {
                    logger.info("Searching file in the directory" + f.getName());
                    path = findFile(fileName, f);
                }
            }
        }
        
        return path;
    }
    
    public static void setProfiles(HashMap<String, ProfileBean> profiles) {
        ProfileManager.profiles = profiles;
    }

    public void setResourcesLocation(String resourcesLocation) {
        ProfileManager.resourcesLocation = resourcesLocation;
    }

}
