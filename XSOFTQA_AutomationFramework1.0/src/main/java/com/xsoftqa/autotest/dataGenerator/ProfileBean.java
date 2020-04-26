package com.xsoftqa.autotest.dataGenerator;

/**
 * Profile Bean
 * @author tahiraka
 *
 */
public class ProfileBean {
    
    private String userDN;
    private String cert;
    private String certType;
    private String certPwd;
    private String testProfile;
    
    public ProfileBean(){
        
    }
    
    public ProfileBean(String userDN, String cert, String certType, String certPwd, String testProfile) {
        this.userDN = userDN;
        this.cert = cert;
        this.certType = certType;
        this.certPwd = certPwd;
        this.testProfile = testProfile;
    }
    
    public String getUserDN() {
        return userDN;
    }
    
    public void setUserDN(String userDN) {
        this.userDN = userDN;
    }
    
    public String getCert() {
        return cert;
    }
    
    public void setCert(String cert) {
        this.cert = cert;
    }
    
    public String getCertType() {
        return certType;
    }
    
    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getCertPwd() {
        return certPwd;
    }

    public void setCertPwd(String certPwd) {
        this.certPwd = certPwd;
    }

    public String getTestProfile() {
        return testProfile;
    }

    public void setTestProfile(String testProfile) {
        this.testProfile = testProfile;
    }

}
