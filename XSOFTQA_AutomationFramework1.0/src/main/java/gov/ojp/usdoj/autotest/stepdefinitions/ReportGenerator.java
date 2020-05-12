
package gov.ojp.usdoj.autotest.stepdefinitions;


import static gov.ojp.usdoj.autotest.stepdefinitions.AutomatedTestConstants.*;

/*
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.apache.commons.io.FileUtils;
*/
import java.io.*;
import java.util.*;

/**
 * This class generates nice cucumber reports useful for the developers to
 * review the test results
 *
 * @author tahiraka
 *
 */
public class ReportGenerator {/*public static void main(String[] args) {

    //Start monitoring the json file
    TimerTask task = new FileWatcher(new File(JSON_REPORT_FILE)) {

        @Override
        protected void onChange(File file) {

            System.out.println("File " + file.getName() + " has changed!");
            if(file.length() > 1 ) {

                System.out.println("About to generate cucumber html report ...");
                try {

                    generateCucumberReport();

                } catch (IOException io) {

                    io.printStackTrace();
                }
            }
        }
    };


    Timer timer = new Timer();
    //Repeat every second
    timer.schedule(task, new Date(), 1000);

}

public static void generateCucumberReport() throws IOException {

    String testResultDir = null;
    try {
            FileReader fileReader = new FileReader(DIR_OUTPUT_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((testResultDir = bufferedReader.readLine()) != null) {
                System.out.println("Directory => " + testResultDir);
                break;
            }
            bufferedReader.close();
    } catch (FileNotFoundException ex) {
        System.out.println("Unable to find the file " + ex.getMessage());
        ex.printStackTrace();
    }
    catch (IOException io) {
        io.printStackTrace();
    }

    File reportOutputDirectory = new File(CUCUMBER_OUTPUT_DIRECTORY);
    System.out.println("===================Cucumber.json=====================");
    try (BufferedReader br = new BufferedReader( new FileReader(JSON_REPORT_FILE))){
        String line = null;
        while((line = br.readLine()) != null) {
            System.out.println(line);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    System.out.println("====================================================");
    List<String> jsonFiles = new ArrayList<>();
    jsonFiles.add(JSON_REPORT_FILE);
    String buildNumber = "Version 3.3.0";
    String projectName = "JGITS";
    boolean runWithJenkins = false;
    boolean parallelTesting = false;

    Configuration configuration = new Configuration(reportOutputDirectory, projectName);
    // optional configuration
    //TODO:
    //configuration.setParallelTesting(parallelTesting);
    //configuration.setRunWithJenkins(runWithJenkins);
    configuration.setBuildNumber(buildNumber);
    // additional metadata presented on main page
    configuration.addClassifications("Platform", "Linux");
    configuration.addClassifications("Browser", "Chrome");
    configuration.addClassifications("Branch", "JGII_Automation");

    ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
    Reportable result = reportBuilder.generateReports();

    try {

        File destDir = new File(testResultDir);
        if(!destDir.exists()) {
            System.out.println("Creating directory:" + destDir.getName());
            destDir.mkdir();
        }
        //now copy the directories
        FileUtils.copyDirectory(reportOutputDirectory,destDir);
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("Completed the generation of report results....");



}
*/
}
