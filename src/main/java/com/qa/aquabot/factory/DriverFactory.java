package com.qa.aquabot.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public Properties prop;
    public OptionsManager optionsManager;

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        String browserVersion = prop.getProperty("browserVersion").trim();

        System.out.println("Browser Name: " + browserName);
        System.out.println("Chrome Version: " + browserVersion);
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;

            default:
                System.out.println("Please pass the right browser: " + browserName);
                break;
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(300));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

        URL url;
        try {
            url = new URL(prop.getProperty("url"));
            openUrl(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return getDriver();
    }

    public Properties init_prop() throws IOException {
        prop = new Properties();
        FileInputStream ip;


        ip = new FileInputStream("./src/test/resources/config/config.properties");
        prop.load(ip);
        String envName = prop.getProperty("env");  //null(Production)/qa/dev/stage/uat
        if (envName == null) {
            System.out.println("Running on PRODUCTION");
        } else {
            System.out.println("Running on environment: " + envName);
            try {
                switch (envName.toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right environment.....");
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void openUrl(String url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        getDriver().get(url);
    }

    public void openUrl(URL url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        getDriver().navigate().to(url);
    }

    public void openUrl(String baseUrl, String path) {
        try {
            if (baseUrl == null) {
                throw new Exception("baseUrl is null");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        getDriver().get(baseUrl + "/" + path);
    }

    public void openUrl(String baseUrl, String path, String queryParam) {
        try {
            if (baseUrl == null) {
                throw new Exception("baseUrl is null");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        getDriver().get(baseUrl + "/" + path + "?" + queryParam);
    }

}
