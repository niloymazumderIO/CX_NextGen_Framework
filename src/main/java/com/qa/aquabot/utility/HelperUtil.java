package com.qa.aquabot.utility;

import com.qa.aquabot.factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class HelperUtil {

    private final WebDriver driver;

    public HelperUtil(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement getElement(By locator) {

        if (IsElementPresentQuick(locator))
            return driver.findElement(locator);
        try {
            throw new NoSuchElementException("Element Not Found : " + locator);
        } catch (RuntimeException re) {

            throw re;
        }
    }

    protected boolean IsElementPresentQuick(By locator) {
        return driver.findElements(locator).size() >= 1;
    }

    public String takeCustomScreenshot() {
        File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/custom-screenshots/" + getCurrentDateTime() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
//System.currentTimeMillis()
    public String getCurrentDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        return "" + dateFormat.format(cal.getTime());
    }

    public String getCurrentDate() {
        return getCurrentDateTime().substring(0, 11);
    }
}
