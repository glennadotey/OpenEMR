/**
 * 
 */
package com.xsoftqa.autotest.stepdefinitions;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;

/**
 * @author tahiraka
 *
 */

import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
//import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;

import com.deque.axe.AXE;

import ch.qos.logback.core.joran.action.Action;
import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import com.xsoftqa.autotest.AutoTestGlobals;
import com.xsoftqa.autotest.dataGenerator.AutoTestServiceManager;
import com.xsoftqa.autotest.dataGenerator.ProfileManager.TestProfile;
import com.xsoftqa.autotest.pageobjects.PageObjectBase;
//import com.xsoftqa.autotest.pageobjects.CaseWorker.CaseWorkerPage;
//import com.xsoftqa.autotest.pageobjects.Common.LoginPage;
//import com.xsoftqa.autotest.pageobjects.Questions.CreateWebBaseBudgetDetailsPage;
//import com.xsoftqa.autotest.pageobjects.Questions.ManageSpecificQuestionsPage;
import com.xsoftqa.autotest.webdrivers.FirefoxDriverExt;
import com.xsoftqa.autotest.webdrivers.ChromeDriverExt;
import com.xsoftqa.autotest.webdrivers.InternetExplorerDriverExt;
import com.xsoftqa.autotest.webdrivers.WebDriverExt;

//import static io.github.bonigarcia.wdm.DriverManagerType.CHROME;
import io.github.bonigarcia.wdm.WebDriverManager;

/*Class<? extends WebDriver> driverClass = ChromeDriver.class;
WebDriverManager.getInstance(driverClass).setup();
WebDriver driver = driverClass.newInstance();

WebDriverManager.getInstance(CHROME).setup();
WebDriver driver = new ChromeDriver(); */


//@ContextConfiguration("classpath:cucumber.xml")
public class StepDefinitionsBase {
    
    //private static HashMap<String, WebDriverExt> webDriverMap = null;
    private static HashMap<String, WebDriver> webDriverMap = null;
    
    private static final Logger log = LogManager.getLogger(StepDefinitionsBase.class);

    // The web driver will be setup with an implicit wait, so explicit waits should normally not be needed.
    // However, if one of the following explicit waits is needed, the following format can be used:
    // longWait.until(ExpectedConditions.visy.linkText("Some Link")));VisibilityOfElementLocated(B
    
    private WebDriverWait longWait = null;
    private WebDriverWait shortWait = null;
    public WebDriver webDriver;

    private final String defaultProfileName;
    
    private final boolean maximizeBrowserWindows;
    private final boolean screenShotEnabled;
    private final String screenShotPath;
    private final String nodeNamesFile; 
    
	// This will hold the PEGA DCM URL
	private static String contextRoot;

	// This will hold the username
	protected static String contextAdminUser;
	protected static String contextPhysicianUser;
	protected static String contextClinicianUser;
	protected static String contextAccountantUser;
	protected static String contextReceptionistUser;

	private static String contextAdminUserPassword;
	private static String contextPhysicianUserPassword;
	private static String contextClinicianUserPassword;
	private static String contextAccountantUserPassword;
	private static String contextReceptionistUserPassword;

	/**
	 * Get the context Root PEGA URL from the global settings properties file and
	 * return it to the caller method
	 * 
	 * @param N/A
	 * @return contextRoot Usage: webDriver.get(super.getContextRoot());
	 *
	 */
	public static String getContextRoot() {
		return contextRoot;
	}

	/**
	 * @author Abdul
	 * @category This method returns the Admin Password
	 * @param Null
	 * @return contextAdminUserPassword
	 */
	public static String getContextAdminUserPassword() {
		return contextAdminUserPassword;
	}

	/**
	 * @author Abdul
	 * @category This method returns the Physician Password
	 * @param Null
	 * @return contextAdminUserPassword
	 */
	public static String getContextPhysicianUserPassword() {
		return contextPhysicianUserPassword;
	}

	
	private final static String propertyFilePath= "data/global-settings.properties";
    
    public  final URL scriptUrl = StepDefinitionsBase.class.getResource("/axe.min.js");
    @Autowired
    public AppURLUtil appURLUtil;
    
    public static final TestProfile DEFAULT_NONMEMBER_PROFILE = TestProfile.AUTOTEST_NO_ACCOUNT;
    	
	WebDriver driver;	 
	//CreateWebBaseBudgetDetailsPage createWebBaseBudgetDetailsPage; 
	//ManageSpecificQuestionsPage manageSpecificQuestionsPage;	 
	//CaseWorkerPage caseWorker;
    
    // Constructor of the Step Definitions Base that will call the Initialization of the WebDriver
    //throws Throwable
	
	
	static {
		
		try (InputStream input = new FileInputStream(propertyFilePath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

         // get the property value and print it out
            contextRoot = (prop.getProperty("context.root") != null) ? prop.getProperty("context.root") : "null";
   
            // OpenEMR
            // This will get the property value for the Admin and print it out
            contextAdminUser =  (prop.getProperty("context.admin") != null) ? prop.getProperty("context.admin") : "null";                  
            contextPhysicianUser = (prop.getProperty("context.physician") != null) ? prop.getProperty("context.physician") : "null";
            contextReceptionistUser = (prop.getProperty("context.receptionist") != null) ? prop.getProperty("context.receptionist") : "null";
            contextClinicianUser = (prop.getProperty("context.clinician") != null) ? prop.getProperty("context.clinician") : "null";
            contextAccountantUser = (prop.getProperty("context.accountant") != null) ? prop.getProperty("context.accountant") : "null";  
           
            // ********************* SECTION BELOW IS FOR ALL PASSWORDS USED BY EACH TEAM *********************
           
            // get the property value for the COMMON user password used across teams
            contextAdminUserPassword = (prop.getProperty("context.admin-password") != null) ? prop.getProperty("context.admin-password") : "null";
            contextPhysicianUserPassword = (prop.getProperty("context.physician-password") != null) ? prop.getProperty("context.physician-password") : "null";
            contextClinicianUserPassword = (prop.getProperty("context.clinician-password") != null) ? prop.getProperty("context.clinician-password") : "null";
            contextAccountantUserPassword = (prop.getProperty("context.accountant-password") != null) ? prop.getProperty("context.accountant-password") : "null";
            contextReceptionistUserPassword = (prop.getProperty("context.receptionist-password") != null) ? prop.getProperty("context.receptionist-password") : "null"; 
	        
        } catch (IOException ex) {
            ex.printStackTrace();
        	}
								        
	        //log.info("Context Root property from global-settings.properties file: " + contextRoot);		
			
	        //log.info("Context Draft User property from global-settings.properties file: " + contextDraftUser);
	        
	}
	
	
	
    public StepDefinitionsBase()  {
    	    	
    	//setupChrome();
        initWebDriverMap();
        //WebDriverManager.chromedriver().config().setProperties("/JGII_Automation/src/main/java/gov/ojp/usdoj/autotest/configuration/my-wdm.properties");
        defaultProfileName = (WebDriverManager.chromedriver().config().getProperties() != null) ? WebDriverManager.chromedriver().config().getProperties() : TestProfile.AUTOTEST_OJP_DCM_ADMIN.name().toLowerCase();
        //defaultProfileName = (System.getProperty("Chrome") != null) ? System.getProperty("Chrome") : TestProfile.AUTOTEST_OJP_DCM_ADMIN.name().toLowerCase();
        //defaultProfileName = (System.getProperty("FirefoxProfile") != null) ? System.getProperty("FirefoxProfile") : TestProfile.AUTOTEST_OJP_DCM_ADMIN.name().toLowerCase();
       
        //initWebDriver(defaultProfileName);      
        maximizeBrowserWindows = (System.getProperty("MaximizeBrowserWindows") != null) ? Boolean.valueOf(System.getProperty("MaximizeBrowserWindows")) : true;
        screenShotEnabled = Boolean.valueOf(System.getProperty("screenshotEnabled", "true"));
        screenShotPath = System.getProperty("screenshotPath",  System.getProperty("user.dir") + "/target/screenshots");
        nodeNamesFile=System.getProperty("nodeNamesFile", "ojp-dcm-node-names.txt");
              
    }
	
    
    /*
     * Normally the Chrome binary is assumed to be in the default location for your particular operating system:
     * OS          Expected Location of Chrome
     * Linux       chrome (found using "which")
     * Mac         /Applications/Firefox.app/Contents/MacOS/chrome-bin
     * Windows     %PROGRAMFILES%\Google\Chrome\Application\chrome.exe 
     */
    @BeforeClass
    protected void setupChrome() {
    	
    	//setup the chromedriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
      

    }
    
    @Before
    public WebDriver getChromeDriver() throws Throwable {
    	//Create driver object for Chrome
    	WebDriver webDriver = new ChromeDriver();
    	
    	
    	return webDriver;
    }

	/*
	 * @BeforeClass public static void setupClass() {
	 * //WebDriverManager.chromedriver().config().setProperties(
	 * "/JGII_Automation/src/main/java/gov/ojp/usdoj/autotest/configuration/my-wdm.properties"
	 * ); // WebDriverManager.chromedriver().setup();
	 * WebDriverManager.chromedriver().proxy("http://10.173.10.100:8080").setup(); }
	 * 
	 * @Before public void setupTest() {
	 * //WebDriverManager.chromedriver().config().getProperties(); webDriver = new
	 * ChromeDriver(); }
	 */
    
    @After(value = "")
    public void teardown() {
        if (webDriver != null) {
        	 log.info("Closing Webdriver....");
        	webDriver.quit();
        }
    }

    
    protected synchronized void tearDownTest(Scenario scenario) {   
        // print out what version of Chrome was used

        log.info("------------TearDown Beginning-----------------------------------");
        log.info("CHROME VERSION : " + WebDriverManager.chromedriver().config().getChromeDriverVersion() );   //"Version: 74.0.3729.131 (Official Build) (64-bit)" getChromeVersion()
        log.info("-----------------------------------------------");

        List<String> nodesInfo = new ArrayList<>();
        
        try {
            if (scenario.isFailed()) log.error("TEST FAILED");
            //Change WebDriverExt to WebDriver
            for (String profile : webDriverMap.keySet()) {
                WebDriver webDriver = getWebDriver(profile);

                
                
                /* Should consider having in separate try catch block in order to catch if it can't find the build and Node info - Fernando
                // Taking this out for now because it was throwing errors
                 * 
                 * String buildAndNodeInfo = (String)((JavascriptExecutor)webDriver)
                        .executeScript("return $j('.j-footer-nav .font-color-meta:contains(\"Pega Software\")').prop('title')");
                if (buildAndNodeInfo != null) {
                    String nodeName = extractNodeName(buildAndNodeInfo);
                    nodesInfo.add(profile + ":" + nodeName);
                }
                
                */

                if (scenario.isFailed()) {
                    //Take a screenshot ...
                    final byte[] screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
                    scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
                  //  log.error("-------embed screenshot------------------------");
                    log.error("-----------------------------------------------");
                    log.error("PAGE INFORMATION IS FOR  " + webDriver.getWindowHandle());
                    log.error("PAGE IS ON " + webDriver.getTitle());
                    log.error("PAGE URL " + webDriver.getCurrentUrl());
                    int size = 0;
                    List<WebElement> errors = webDriver.findElements(By.cssSelector(".dcm-error-message"));
                    for ( WebElement error : errors ) {
                        if (error.isDisplayed()) {
                            log.error("ERROR MESSAGE" + error.getText());
                            size++;
                        }
                    }
                    log.error("TOTAL Validation ERRORS : " + size);
                //    log.error("BUILD # & NODE # " + buildAndNodeInfo);
                    log.error("-----------------------------------------------");
                }
            }
        }
        catch (Throwable e) {
            log.error("FAILED TO RETRIEVE PAGE INFORMATION", e.getCause()); 
        }
        finally {
            // Write app node names to file
        	//What is this for
            writeNodeNames(nodesInfo);
        }
        //Change WebDriverExt to WebDriver
        if (webDriverMap != null) {
            for (WebDriver driver : webDriverMap.values()) {

                //logOut(driver);
                driver.quit();
            }
        
            webDriverMap.clear();
        }
        
        longWait = null;
        shortWait = null;
        
        tearDownTestScenarioSession();
    }
    
    /**
     * Tear down everything in the session.
     */
    protected void tearDownTestScenarioSession() {
        TestScenarioSession testScenarioSession = TestScenarioSession.getInstance();
        
        //This section is for any cleanups that need to be done before the Driver is Killed.
        
        //deleteQuestion(questionObject);
        
        // Delete questions
        /**for(QuestionObject quesition: testScenarioSession.getQuestions()){
        	deleteQuestion(quesition);
        } */
        
        TestScenarioSession.destroySession();
    }
    
    /*private void deleteQuetion(QuestionObject questionObject) {
        if (questionObject != null && questionObject.getId() > 0) {
            if (questionObject.getQuestionType().equals(QuestionType.GROUP)) {
                groupManager.delete(questionObject.getId());
                deleteAssociatedTemplates(questionObject);
            } else if (questionObject.getQuestionType().equals(QuestionType.PROJECT)) {
                projectManager.delete(questionObject.getId());
            } else {
                throw new UnsupportedOperationException("teardown does not support place type " + questionObject.getQuestionType());
            }            
        }        
    }*/
    protected synchronized void printFrames() {
    	
    	List<WebElement> ele = webDriver.findElements(By.tagName("frame"));
        log.info("Number of frames in current page :" + ele.size());
        for(WebElement el : ele){
          //Returns the Id of a frame.
        	log.info("Frame Id :" + el.getAttribute("id"));
          //Returns the Name of a frame.
        	log.info("Frame name :" + el.getAttribute("name"));
        }
      
    	
    }
    
    private String getChromeVersion(){
        String version = "Unable to get chrome version";
        try {
            version = ( String ) ((JavascriptExecutor)getWebDriver()).executeScript("return navigator.userAgent;");
        }catch ( Throwable e ) {
            e.printStackTrace();
        }
        return version;
    }
    
    private String getFirefoxVersion(){
        String version = "Unable to get firefox version";
        try {
            version = ( String ) ((JavascriptExecutor)getWebDriver()).executeScript("return navigator.userAgent;");
        }catch ( Throwable e ) {
            e.printStackTrace();
        }
        return version;
    }
  
    
    private synchronized void initWebDriver(String profileName) throws Throwable {
        String browser = (System.getProperty("Browser") != null) ? System.getProperty("Browser") : "chrome";
        
        if (browser.equals("firefox")) {
            setFirefoxHome();
            
            ProfilesIni allProfiles = new ProfilesIni();
            FirefoxProfile profile = allProfiles.getProfile(profileName);
            if (profile == null) {
                throw new Exception(String.format("Failed to load Firefox profile '%s'. Be sure this profile is created and properly setup on the workstation that is running the test.", profileName));
            }
            profile.setAssumeUntrustedCertificateIssuer(false);
            
            profile.setPreference("browser.download.folderList",2);
            profile.setPreference("browser.download.manager.showWhenStarting",false);
            profile.setPreference("browser.download.dir",System.getProperty("java.io.tmpdir"));
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
            
            webDriver = new FirefoxDriverExt(profile);
            
//        }
//        else if (browser.equals("ie")) {
//        	webDriver = new InternetExplorerDriverExt();
        }
        else if (browser.equals("chrome")) {
        	// Set Chrome Home
        	setupChrome();
        	webDriver = getChromeDriver();
        	//getAdminConsole();
        }

        webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(1, TimeUnit.MINUTES);

        if (maximizeBrowserWindows) {
            java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
          
            webDriver.manage().window().maximize();
            //webDriver.manage().window().setSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
        }
        
        webDriverMap.put(profileName, webDriver);
    } 
    
    
    /*
     * Normally the Firefox binary is assumed to be in the default location for your particular operating system:
     * OS          Expected Location of Firefox
     * Linux       firefox (found using "which")
     * Mac         /Applications/Firefox.app/Contents/MacOS/firefox-bin
     * Windows     %PROGRAMFILES%\Mozilla Firefox\firefox.exe 
     */
    protected void setFirefoxHome() {
    	
    	//setup the firefox driver using WebDriverManager
    	WebDriverManager.firefoxdriver().setup();
    	
    	//Create driver object for Firefox
    	WebDriver driver = new FirefoxDriverExt(null);
        
        // Check if Firefox path name passed in as commandline argument
        /*String path = System.getProperty("FIREFOX_HOME");
        if (path == null) {
             
           // Check if environment variable holds name of Firefox path
           path = System.getenv("FIREFOX_HOME");
        }

        // Check if Firefox is in another location than the assumed default location, if so, use it
        if (path != null) {
            System.setProperty("webdriver.firefox.bin", path);
        }*/
        
    }

    
    protected void takeScreenshot(WebDriver driver, String fileName)  {
        try {
            if(screenShotEnabled){
                 // take a screenshot
                File tmpScreenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                File screenShotFile = new File(screenShotPath + File.separator + fileName + ".png");
                if(screenShotFile.exists()){
                    screenShotFile.delete();
                }
                FileUtils.moveFile(tmpScreenShot, screenShotFile);
                
                //Building up the destination path for the screenshot to save
				//Also make sure to create a folder 'screenshots' with in the cucumber-report folder
				//File destinationPath = new File("target/cucumber-reports/screenshots/" + File.separator + fileName + ".png");
				//System.getProperty("user.dir") +
				
				//Copy taken screenshot from source location to destination location
				//File.copy(tmpScreenShot, destinationPath);   

            }           
        }catch (Exception e) {
            log.error("Unable to take screen shot" , e);
        }       
    } 
    
    
    /**
     * Templates are not children of a group/project so they will not get deleted when the group/project gets deleted.
     * @param placeObject
     */
    /**private void deleteAssociatedTemplates(PlaceObject placeObject) {
        List<ContentObject> associatedTemplates = placeObject.getAssociatedTemplates();
        for (ContentObject template: associatedTemplates) {
            templateManager.deleteTemplate(template.getId());
        }        
    }*/
    
    protected void logOut(WebDriverExt driver) throws IOException {
        // go to the home page
        openOJPDCMHome(driver);
        
        // click the user name if it exists
        if (verifyElementExists(driver, By.xpath("//i[@data-test-id='px-opr-image-ctrl']"))) {
            new PageObjectBase(driver).clickElement( By.xpath("//i[@data-test-id='px-opr-image-ctrl']"), By.xpath("//li[@data-test-id='201711011301500120490']//a[@class='menu-item-anchor ']//*[@class='menu-item-title']"));

            driver.findClickableElement(By.xpath("//li[@data-test-id='201711011301500120490']//a[@class='menu-item-anchor ']//*[@class='menu-item-title']")).click();
            
            WebDriverWait wait = new WebDriverWait(driver, AutoTestGlobals.WAIT_TIME_NORMAL);                
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("securitywidgeterror"))); 
        }
            
    }
    
    //Change WebDriverExt to WebDriver
    private synchronized void initWebDriverMap() {
        if (webDriverMap == null) {
            webDriverMap = new HashMap<String, WebDriver>();
        }
    }
    
    /**
     * Returns the default profile name
     * @return
     */
    protected String getDefaultProfileName() {
        return defaultProfileName;
    }
    
    /**
     * Returns a WebDriver for the default user profile. The default
     * profile is <b>AUTOTEST_OJP_DCM_ADMIN</b>, unless another profile name has been
     * passed in as a command line parameter. Once a WebDriver has
     * been created for the profile, that same WebDriver will continue
     * to be returned for the rest of the test scenario.<p>
     *
     * @return      WebDriver for the default user
     */
    //Change WebDriverExt to WebDriver
    protected WebDriver getWebDriver() throws Throwable {
        return getWebDriver(defaultProfileName);
    }
    
    //Change WebDriverExt to WebDriver
    public WebDriver getWebDriver(TestProfile testProfile) throws Throwable {
        //WebDriver webDriver = null;
        webDriver = getWebDriver(testProfile.name().toLowerCase());
        if (webDriver == null) {
            throw new Exception ("Unknown profile type!");
        }
        
        return webDriver;
    }
    
    //Change WebDriverExt to WebDriver
    private synchronized WebDriver getWebDriver(String profileName) throws Throwable {
        if (!webDriverMap.containsKey(profileName)) {
            initWebDriver(profileName);
        }
        
        return webDriverMap.get(profileName);
    }
    
    protected synchronized WebDriverWait getLongWait() {
        if (longWait == null)
            longWait = new WebDriverWait(webDriverMap.get(defaultProfileName), 30);
        return longWait;
    }
    
    protected synchronized WebDriverWait getShortWait() {
        if (shortWait == null)
            shortWait = new WebDriverWait(webDriverMap.get(defaultProfileName), 15);
        return shortWait;
    }
    //Change WebDriverExt to WebDriver
    protected void openOJPDCMHome(WebDriver webDriver) throws IOException {
    	// TODO: Get the report configuration path
    	//webDriver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
        
    	// go to the homepage, and wait till we see the main navigation bar
        webDriver.get(appURLUtil.getDCMURL());
        
        WebDriverWait wait = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_NORMAL);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("j-globalNav"))); 
        
    	// TODO: Get the report configuration path
    	//FileReaderManager.getInstance().getConfigReader().getReportConfigPath();
        
    }
    //Change WebDriverExt to WebDriver
    protected WebDriver getAdminConsole() throws Throwable {
        return getAdminConsole(AutoTestServiceManager.DEFAULT_SERVICE_ADMIN_PROFILE);
    }
    //Change WebDriverExt to WebDriver
    protected WebDriver getAdminConsole(TestProfile testProfile) throws Throwable {
        WebDriver webDriver = getWebDriver(testProfile);
        openOJPDCMHome(webDriver);
        webDriver.get(appURLUtil.getAdminURL());
        
        return webDriver;    
    }
    
    
    
    protected boolean verifyElementDoesNotExist(WebDriver webDriver, By byElement) {
        return verifyElementDoesNotExist(webDriver, null, byElement);
    }

    protected boolean verifyElementDoesNotExist(WebDriver webDriver, WebElement containerElement, By byElement) {
        // since we're looking for something that should not exist, shorten the wait time
        webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_SHORT, TimeUnit.SECONDS);
    
        try {
            if (containerElement != null && containerElement.findElements(byElement).size() == 0) {                
                return true;
            }
            else if (webDriver.findElements(byElement).size() == 0) {
                return true;                          
            }
        }
        catch (Exception e) {
            log.error("Something went wrong in verifyElementDoesNotExist", e);
        }
        
        // set the wait time back to normal
        webDriver.manage().timeouts().implicitlyWait(AutoTestGlobals.WAIT_TIME_NORMAL, TimeUnit.SECONDS);


        return false;
    }

    protected boolean verifyElementExists(WebDriver webDriver, By byElement) {
        return verifyElementExists(webDriver, null, byElement);
    }
    
    /**
     * Verify if element exists
     * @param webDriver
     * @param byElement
     * @param loop if loop is true, it will try to find the element 10 tries
     * @return
     */
    protected boolean verifyElementExists(WebDriver webDriver, By byElement, boolean loop) {
        if(loop == true){
            return verifyElementExists(webDriver, null, byElement);
        }else {
            try {
                WebDriverWait wait = new WebDriverWait(webDriver, AutoTestGlobals.WAIT_TIME_SHORT);
                wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
                return true;
            }
            catch (Exception e ) {
                return false;
            }
        }
    }
    
    protected boolean verifyElementExists(WebDriver webDriver, WebElement containerElement, By byElement) {
        boolean exists = false; // assume we will not find it
        
        log.debug("Trying to determine if element: " + byElement + " exists.");
        
        try {
            if (containerElement != null) {
                containerElement.findElement(byElement);
            }
            else {
                webDriver.findElement(byElement);
            }
            exists = true;
        }
        catch (Exception e) {
            log.debug("Exception caught trying to find element.  That means it's false.");
        }

        return exists;
    }
    
   /* protected boolean isLoggedin() {
    	//verifyElementDisplayed();
    	// log.debug("Trying to determine if currently logged in i[@data-test-id='px-opr-image-ctrl']:" + verifyElementExists(driver, By.xpath("//i[@data-test-id='px-opr-image-ctrl']")));
    	return verifyElementExists(driver, By.xpath("//i[contains(@class,'icons avatar name-f')]"));
    	
    }*/

    
    protected synchronized WebElement findElementByRegularText(String textToFind) {
        String textToFindAsXPath = String.format("//*[contains(.,'%s')]", textToFind);
        return webDriverMap.get(defaultProfileName).findElement(By.xpath(textToFindAsXPath));
    }
    
    protected WebElement findElementByRegularText(String textToFind, WebDriver driver) {
        String textToFindAsXPath = String.format("//*[contains(.,'%s')]", textToFind);
        return driver.findElement(By.xpath(textToFindAsXPath));
    }
    
    protected synchronized String getAdminProperty(String propertyName) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriverMap.get(defaultProfileName);
        String jsCommand = String.format("return getPegaPropertyValue(\"%s\")", propertyName);
        return (String)jse.executeScript(jsCommand);
    }
    
    protected String getAdminProperty(String propertyName, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String jsCommand = String.format("return getPegaPropertyValue(\"%s\")", propertyName);
        return (String)jse.executeScript(jsCommand);
    }
    
    protected synchronized String getAdminProperty(String propertyName, String defaultValue) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriverMap.get(defaultProfileName);
        String jsCommand = String.format("return getPegaPropertyValue(\"%s\", \"%s\")", propertyName, defaultValue);
        return (String)jse.executeScript(jsCommand);
    }
    
    protected String getAdminProperty(String propertyName, String defaultValue, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String jsCommand = String.format("return getPegaPropertyValue(\"%s\", \"%s\")", propertyName, defaultValue);
        return (String)jse.executeScript(jsCommand);
    }
    
    protected synchronized void scrollPageVertically(int numberOfPixels) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriverMap.get(defaultProfileName);
        String jsCommand = String.format("window.scrollBy(0,%s)", numberOfPixels);
        jse.executeScript(jsCommand);
    }
    
    protected void scrollPageVertically(int numberOfPixels, WebDriver driver) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        String jsCommand = String.format("window.scrollBy(0,%s)", numberOfPixels);
        jse.executeScript(jsCommand);
    }
    
    /**
     * Mousemove currently does not work in firefox31 
     * Use hoverMouseOverWebElement(WebDriver driver, String element) for workaround
     * @param elementToHoverOver
     */
    protected synchronized void hoverMouseOverWebElement(WebElement elementToHoverOver) {
    	//TODO:	
        //Locatable hoverItem = (Locatable)elementToHoverOver;
        Mouse mouse = ((HasInputDevices)webDriverMap.get(defaultProfileName)).getMouse();
        //mouse.mouseMove(hoverItem.getCoordinates());
    }
    
    protected synchronized void hoverMouseOverMenuWebElement(By elementToHoverOver, By elementToHoverChoice) {
    	//TODO:
    	Actions action = new Actions(webDriver);
    	
    	//Mouse hover
    	action.moveToElement(webDriver.findElement(elementToHoverOver)).click();
    	action.moveToElement(webDriver.findElement(elementToHoverChoice));
    	action.click();
    	
    	//Mandatory method to be called for performing all the sequence of actions
    	action.perform();   	
    }

    protected void hoverMouseOverWebElement(WebElement elementToHoverOver, WebDriver driver) {
    	//TODO:
        //Locatable hoverItem = (Locatable)elementToHoverOver;
        Mouse mouse = ((HasInputDevices)driver).getMouse();
        //mouse.mouseMove(hoverItem.getCoordinates());
    }
    
    protected String extractNodeName(String buildAndNodeInfo) {
        final String hostPrefix = "; Host ";
        String nodeName = "";
        
        int index = buildAndNodeInfo.lastIndexOf(hostPrefix);
        if (index != -1) {
            nodeName = buildAndNodeInfo.substring(index + hostPrefix.length());
        }
        
        return nodeName;
    }
    
    protected void writeNodeNames(List<String> nodesInfo) {
        String filename = System.getProperty("java.io.tmpdir") + File.separator + nodeNamesFile;
        log.debug("Writing node names to file " + filename + ": " + nodesInfo.toString());
        
        try {
            File f = new File(filename);
            if (f.exists()) {
                f.delete();
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                bw.write(nodesInfo.toString());
            }
        }
        catch (Exception e) {
            log.error("Unable to write node names to file: " + filename, e);
        }
    }
    
    

	// Method analyzes pages for 508 Compliance.
	// outputs to both a text file and extent report.

	public void testAccessibility(WebDriver webDriver) {

		LocalDateTime time = LocalDateTime.now();
		String getTime = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(time);
		
		getTime = getTime.replace(':', ' ');

		try {
			//test.log(Status.INFO, "508 Compliance Testing");
			System.out.println("Trying 508 Testing");

			String pageTitle =  webDriver.getTitle();
			JSONObject responseJSON = new AXE.Builder(webDriver, scriptUrl).analyze();

			JSONArray violations = responseJSON.getJSONArray("violations");

			if (violations.length() == 0) {
				assertTrue("No violations found", true);
				System.out.println("No 508 Issues Indentified");
			} else {
				AXE.writeResults("Test Accessiblity", responseJSON);
				// System.out.println(responseJSON.toString());
				System.out.println(AXE.report(violations));

				BufferedWriter writer = new BufferedWriter(new FileWriter("./target/Reports/508 Compliance/" + "508 Compliance Report " +pageTitle+ " " + getTime + ".txt"));

				writer.write(System.getProperty("line.separator") + AXE.report(violations));

			
			System.out.println("508 Compliance Violations " + System.getProperty("line.separator") + AXE.report(violations));
				writer.close();
			}
			//assertTrue(AXE.report(violations), false);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
