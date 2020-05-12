package gov.ojp.usdoj.autotest.dataGenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.security.sasl.SaslException;
//import javax.xml.ws.http.HTTPException;
import javax.xml.ws.http.HTTPException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.http.HttpMethod;

import gov.ojp.usdoj.autotest.dataGenerator.ProfileManager.TestProfile;

//import com.gargoylesoftware.htmlunit.HttpMethod;

/**
 * @author tahiraka
 *
 * Util class that will support HttpsURLConnection service calls into Pega app node
 */

public class AutoTestServiceManager {
   
    private static final Logger log = LogManager.getLogger(AutoTestServiceManager.class);
    public static String appURL;
    public static AppName appName;
        
    private static final String SECURITY_CONFIG_APPNAME_PROP = "security.config.appName ";
    private static final String DEFAULT_TRUSTSTORE = "src/test/resources/pegadcm-cacerts";
    private static final String DEFAULT_TRUSTSTORE_PASSWORD = "PKIPassword";
    private static final String DEFAULT_TRUSTSTORE_TYPE = "jks";
    
    private static final String DEFAULT_KEYSTORE = "src/test/resources/test-token-automated/dcm_admin_pega_dcmsponsoredegroup.autotest.p12";
    private static final String DEFAULT_KEYSTORE_PASSWORD = "PKIPassword";
    private static final String DEFAULT_KEYSTORE_TYPE = "PKCS12";
    public static final TestProfile DEFAULT_SERVICE_ADMIN_PROFILE = TestProfile.AUTOTEST_OJP_DCM_ADMIN;
    
    static {        
    	/*
    	 * appURL is Initialized by StepDefinitionsBase now via externalized var, statically - Fernando
    	 * 
    	 */
    	
    	
    	//PropertiesManager propertiesManager = new PropertiesManager();
        //propertiesManager.setAutoTestServiceManager(new AutoTestServiceManager());

        //setDefaultSystemProperties();
        //appURL = (System.getProperty("OJPDcmURL") != null) ? System.getProperty("OJPDcmURL") : "https://stage-grants.ojp.usdoj.gov/prweb";
       
    	
    	// appURL = "https://stage-grants.ojp.usdoj.gov/prweb";
    	
        // call the service to retrieve the appName
        //appName = AppName.getEnum(propertiesManager.getPropertyValue(SECURITY_CONFIG_APPNAME_PROP));
    }
    
    public enum AppName {
        OJPDCM("JGITS");
        
        private final String value;
                
        AppName(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
        
        public static AppName getEnum(String name){
            for (AppName value : values()) {
                if (value.getValue().equalsIgnoreCase(name)) {
                    return value;
                }
            }
            return null;
        }
    }
    
    public boolean isApp(AppName appName){
        return appName.equals(AutoTestServiceManager.appName);
    }
    
    /**
     * Method to call service with JSONObject data passed in
     * @param serviceURL
     * @param data JSONObject
     * @param method POST, DELETE, PUT
     * @param profile user who you want to make the connection
     * @return
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, JSONObject data, HttpMethod method, TestProfile profile){ 
        try {  
            HttpsURLConnection connection = getHttpsURLConnection(serviceURL, method, profile);        
            return callService(data, connection);

        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
        
    }
    
    /**
     * Method to call service with urlParams data passed in
     * @param serviceURL
     * @param data String
     * @param method POST, DELETE, PUT
     * @param profile user who you want to make the connection
     * @return
     */
    public JSONObject callService(String serviceURL, String data, HttpMethod method, TestProfile profile){ 

        try {  
            HttpsURLConnection connection = getHttpsURLConnection(serviceURL, method, profile);     
            return callService(data, connection);

        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
        
    }
    

    
    /**
     * Method to call service with JSONObject data passed in
     * @param serviceURL
     * @param data JSONObject
     * @param method POST, DELETE, PUT
     * @return JSONObject
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, JSONObject data, HttpMethod method){        
        try {  
            return callService(data, getDefaultHttpsURLConnection(serviceURL, method.name()));            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
    }
    
    /**
     * Method to call service with urlParams data passed in
     * @param serviceURL
     * @param data String
     * @param method POST, DELETE, PUT
     * @return JSONObject
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, String data, HttpMethod method){        
        try {  
            return callService(data, getDefaultHttpsURLConnection(serviceURL, method.name()));            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
    }
    
    
    /**
     * Method to call service with JSONObject data passed in
     * @param serviceURL
     * @param data JSONObject
     * @param requestProperties 
     * @param method POST, DELETE, PUT
     * @return JSONObject
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, JSONObject data, Map<String, String> requestProperties, HttpMethod method){        
        try {  
            
            requestProperties.put("Content-Type", "application/json");
            return callService(data.toString(), requestProperties, getDefaultHttpsURLConnection(serviceURL, method.name()));            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
    }
    
    /**
     * Method to call service with urlParams data passed in
     * @param serviceURL
     * @param data urlParams
     * @param requestProperties 
     * @param method POST, DELETE, PUT
     * @return JSONObject
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, String data, Map<String, String> requestProperties, HttpMethod method){        
        try {  
            
            requestProperties.put("Content-Type", "application/x-www-form-urlencoded");
            return callService(data.toString(), requestProperties, getDefaultHttpsURLConnection(serviceURL, method.name()));            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
    }
  
  
    /**
     * Method to call service with urlParams data passed in
     * @param data urlParams
     * @param connection
     * @return
     */
    private JSONObject callService(String data, HttpsURLConnection connection){  
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Content-Type", "application/x-www-form-urlencoded");
        return callService(data, requestProperties, connection);
    }
    
    /**
     * Method to call service with JSONObject data passed in
     * @param serviceURL
     * @param data
     * @param method POST, DELETE, PUT
     * @param connection
     * @return
     */
    private JSONObject callService(JSONObject data, HttpsURLConnection connection){  
        Map<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Content-Type", "application/json");
        return callService(data.toString(), requestProperties, connection);
    }
    
    /**
     * Method to call service with JSONObject data passed in with extra requestheader
     * @param data
     * @param requestProperties HashMap more properties u watch to add on requestheader
     * @param connection
     * @return
     */
    private JSONObject callService(String data, Map<String, String> requestProperties, HttpsURLConnection connection){  
        try {  
            for(Map.Entry<String, String> entry : requestProperties.entrySet()){
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            
            if(data != null){
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());  
                wr.write(data);  
                wr.close();
            }
  
            return getJSONResponse(connection);    
            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + connection.getURL() , e);  
        }
        return null;      
    }

    /**
     * Method to call the service
     * @param serviceURL
     * @param method
     * @param profile
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public JSONObject callService(String serviceURL, HttpMethod method, TestProfile profile) {

        try {  
            ProfileBean bean = ProfileManager.getUserProfileBean(profile);
            SSLSocketFactory sslSocketFactory = getSocketFactory(bean);
            HttpsURLConnection connection = getHttpsURLConnection(serviceURL, method, sslSocketFactory);            
            return callService(connection);
            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;
    }
    
    /**
     * Method to call the service
     * @param serviceURL
     * @param method
     * @return JSONObject
     * @return method GET, DELETE
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(String serviceURL, HttpMethod method){     
        try {  
            return callService(getDefaultHttpsURLConnection(serviceURL, method.name()));
            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + serviceURL , e);  
        }
        return null;  
    }
    
    /**
     * Method to call the service
     * @return connection
     * @return method GET, DELETE
     * @throws IOException 
     * @throws MalformedURLException 
     */
    public JSONObject callService(HttpsURLConnection connection){  
        try {  
            return getJSONResponse(connection);    
            
        } catch (Exception e) {  
            log.error("Unable to make the service call" + connection.getURL() , e);  
        }
        return null;       
    }
         
    /**
     * Default HttpsURLConnection configuration
     * @param serviceURL
     * @param requestMethod
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private HttpsURLConnection getDefaultHttpsURLConnection(String serviceURL, String requestMethod) throws MalformedURLException, IOException{
        HttpsURLConnection connection = (HttpsURLConnection) new URL(appURL + serviceURL).openConnection();  
        connection.setRequestMethod(requestMethod);  
        connection.setDoOutput(true);  
        connection.setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());  
        
        return connection;
        
    }
    
    /**
     * Get HttpsURLConnection with the associated Profile
     * @param serviceURL
     * @param requestMethod
     * @param profile
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private HttpsURLConnection getHttpsURLConnection(String serviceURL, HttpMethod requestMethod, TestProfile profile) throws MalformedURLException, IOException{
        ProfileBean bean = ProfileManager.getUserProfileBean(profile);
        SSLSocketFactory sslSocketFactory = getSocketFactory(bean);        
        return getHttpsURLConnection(serviceURL, requestMethod, sslSocketFactory);      
    }
    
    /**
     * HttpsURLConnection configuration 
     * @param serviceURL
     * @param requestMethod
     * @param socketFactory
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    private HttpsURLConnection getHttpsURLConnection(String serviceURL, HttpMethod requestMethod, SSLSocketFactory socketFactory) throws MalformedURLException, IOException{
        HttpsURLConnection connection = (HttpsURLConnection) new URL(appURL + serviceURL).openConnection();  
        connection.setRequestMethod(requestMethod.name());  
        connection.setDoOutput(true);  
        connection.setSSLSocketFactory(socketFactory);  
        
        return connection;
        
    }
    
    /**
     * Helper method to get the JSONObject based on connection status
     * @param connection
     * @return
     * @throws ServiceException 
     */
    //ServiceException
    private JSONObject getJSONResponse(HttpsURLConnection connection) throws SaslException {  
        try {  
            int status = connection.getResponseCode();  
            log.info(status + " status request method \"" + connection.getRequestMethod() + "\" on serviceURL " + connection.getURL());
            
            if(status == HttpsURLConnection.HTTP_UNAUTHORIZED){
                URL url = new URL(appURL);
                url.openConnection();
            }
            
            if (status == HttpsURLConnection.HTTP_OK || status == HttpsURLConnection.HTTP_CREATED || 
                status == HttpsURLConnection.HTTP_ACCEPTED || status == HttpsURLConnection.HTTP_NO_CONTENT) {  
                if(connection.getInputStream() != null){
                    return convertStreamToJSON(connection.getInputStream());  
                }                
                return new JSONObject();  
                 
            } else if (status == HttpsURLConnection.HTTP_BAD_METHOD) {
                throw new HTTPException(status);
            } else if(connection.getErrorStream() != null){
                // log the error message so we know why there's an error
                JSONObject errorObject = convertStreamToJSON(connection.getErrorStream());
                if(errorObject.has("error")){
                    JSONObject error = errorObject.getJSONObject("error");
                    log.error("Error message response " + "\"" + error.opt("message") + "\"" );
                }
                
                return errorObject;              
            }
            
            log.error("Invalid response from service");
            //TODO:
            //throw new ServiceException("Invalid response from service: " + status);
        }
        catch (Exception e) {  
            log.error("Unable to get the response" , e);
            //TODO:
            throw new SaslException();
            //throw new ServiceException(e);
        }
		return new JSONObject();  
    }  
    
    /**
     * Helper method to convert stream to JSONObject
     * @param inputStream
     * @return
     */
    private JSONObject convertStreamToJSON(InputStream inputStream){      
        BufferedReader br;
        StringBuilder sb = new StringBuilder();  
        
        try {
            br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String line = null;  
            while ((line = br.readLine()) != null) {  
                if(!line.startsWith("throw")){
                    sb.append(line + "\n");  
                }
            }  
            br.close(); 
            
            if(!sb.toString().isEmpty()){
                return new JSONObject(sb.toString());
            }
 
        }catch (JSONException e){
            log.error("Unable to convert string to JSONObject " + sb.toString() , e);
        }catch (IOException e ) {
            log.error("Unable to convert stream" , e);  
        }
        
      return new JSONObject();        
    }
    
    /**
     * Set the default keystore and truststore
     */
    private static void setDefaultSystemProperties(){  
        System.setProperty("javax.net.ssl.keyStore", getFileAbsoluteFile(DEFAULT_KEYSTORE));
        System.setProperty("javax.net.ssl.keyStoreType", DEFAULT_KEYSTORE_TYPE);  
        System.setProperty("javax.net.ssl.keyStorePassword", DEFAULT_KEYSTORE_PASSWORD);  

        System.setProperty("javax.net.ssl.trustStore", getFileAbsoluteFile(DEFAULT_TRUSTSTORE));  
        System.setProperty("javax.net.ssl.trustStoreType", DEFAULT_TRUSTSTORE_TYPE);  
        System.setProperty("javax.net.ssl.trustStorePassword", DEFAULT_TRUSTSTORE_PASSWORD); 
        
        System.setProperty("jsse.enableSNIExtension", "false");    
    }  
    
    /**
     * Create the socketFactory for the specific user who want to make the connection
     * @param profileBean
     * @return
     */
    private SSLSocketFactory getSocketFactory(ProfileBean bean){
        return getSocketFactory(bean.getCertType(), ProfileManager.getCertPath(bean.getCert()), bean.getCertPwd(), DEFAULT_TRUSTSTORE_TYPE, getFileAbsoluteFile(DEFAULT_TRUSTSTORE), DEFAULT_TRUSTSTORE_PASSWORD);
    }
    
    /**
     * Create the socketFactory to be used to make the connection 
     * @param keyStoreType
     * @param keyStorePath
     * @param keyStorePassword
     * @param trustStoreType
     * @param trustStorePath
     * @param trustStorePassword
     * @return
     */
    private SSLSocketFactory getSocketFactory(String keyStoreType, String keyStorePath, String keyStorePassword, String trustStoreType, String trustStorePath, String trustStorePassword){
        try {
            // setup keystore
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance( KeyManagerFactory.getDefaultAlgorithm() );
            KeyStore keyStore = KeyStore.getInstance( keyStoreType);
            if(StringUtils.isEmpty(keyStorePath) || StringUtils.isBlank(keyStorePath)) {
                keyStorePath = DEFAULT_KEYSTORE;
                log.debug("keyStorePath ==>" + keyStorePath);
            }
            keyStore.load(new FileInputStream(keyStorePath), keyStorePassword.toCharArray());
            keyManagerFactory.init(keyStore, keyStorePassword.toCharArray());
            
            //setup truststore
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance( TrustManagerFactory.getDefaultAlgorithm() );
            KeyStore trustStore = KeyStore.getInstance(trustStoreType);
            trustStore.load( new FileInputStream( trustStorePath ), trustStorePassword.toCharArray() );
            trustManagerFactory.init(trustStore);
            
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            return context.getSocketFactory();

        }catch ( Exception e ) {
            log.error("Unable to initialize AutoTestSocketFactory" , e);
        }
        return null;
    }

    private static String getFileAbsoluteFile(String path){
        File file = new File (path);
        System.out.println(file.getAbsolutePath());
        return file != null ? file.getAbsolutePath() : "";

    }
    
    /**
     * Check if obj has no error 
     * @param obj
     * @return boolean
     */
    public boolean isSuccessful(JSONObject obj){
        if(obj != null && !obj.has("error")){
          return true;
        }    

        log.info("JSONObject has an error " + obj);
        return false;
    }

}
