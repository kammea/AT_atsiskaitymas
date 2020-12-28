package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class ComponentUtils {
    /**
     * Prints the time when TestCase failed
     */
    public static void printTimeOfFailure() {
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'Europe / Vilnius'");
        time.setTimeZone(TimeZone.getTimeZone("Europe/Vilnius"));
        long failTime = System.currentTimeMillis();
        System.out.println(time.format(failTime));
    }

    /**
     * @param tcName String
     * @param driver WebDriver
     * @throws IOException exception
     */
    public static void makeScreenshot(String tcName, WebDriver driver) throws IOException {
        SimpleDateFormat time = new SimpleDateFormat("MM_dd_HH_mm_ss");
        time.setTimeZone(TimeZone.getTimeZone("Europe/Vilnius"));
        long failTime = System.currentTimeMillis();
        String failedAt = time.format(failTime);

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("C:\\Users\\Kompas\\IdeaProjects\\Aiste_Navajauskieen_automatinis_testavimas_atsiskaitymas\\screenshots\\failedTC_ " + tcName + failedAt + ".png"));
    }

}
