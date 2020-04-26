/**
 * 
 */
package com.xsoftqa.autotest;

/**
 * 
 * This class contains "global" constants and code that is shared between the step definitions and page objects.
 * It is declared final because there's really no reason to extend this class.
 *
 * @author tahiraka
 *
 */
public final class AutoTestGlobals {

    // private constructor to effectively make this a static class
    private AutoTestGlobals() {
    	
    }
    
    // the following wait times are in seconds
    public static final int WAIT_TIME_NORMAL = 30;
    public static final int WAIT_TIME_EXTENDED = 120;
    public static final int WAIT_TIME_SHORT = 5;
    public static final int WAIT_UNTIL = 30;
    
    public static final int ZERO_RETRY = 0;
    public static final int MIN_RETRY = 4;
    public static final int MIN_RETRIES = 7;
    public static final int DEFAULT_RETRIES = 10;
    public static final int HIGH_RETRIES = 25;
    public static final int EXTREME_RETRIES = 50;  

}
