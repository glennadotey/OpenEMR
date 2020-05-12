/**
 * 
 */
package gov.ojp.usdoj.autotest.stepdefinitions;

import java.io.File;
import java.util.TimerTask;

/**
 * This abstract class that allows us to watch for file changes
 * it is used by the cucumber reporting
 *
 * @author tahiraka
 *
 */
public abstract class FileWatcher extends TimerTask {

    private long timeStamp;
    private File file;

    public FileWatcher(File file) {
        System.out.println("Initializing File " + file.getName());
        this.file = file;
        this.timeStamp = file.lastModified();

    }


    public final void run() {

        long timeStamp = file.lastModified();

        System.out.println("File was last modified : " + timeStamp);

        if(this.timeStamp != timeStamp) {
            this.timeStamp = timeStamp;
            onChange(file);
        }

    }

    protected abstract void onChange(File file);

}
