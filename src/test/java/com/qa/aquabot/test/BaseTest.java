package com.qa.aquabot.test;

import com.qa.aquabot.factory.DriverFactory;
import com.qa.aquabot.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    DriverFactory driverFactory;
    Properties properties;
    WebDriver driver;
    HomePage homePage;
    SoftAssert softAssert;

    @Parameters({"browser", "browserVersion"})
    @BeforeTest
    public void setUp(String browser, String browserVersion) throws IOException {

        driverFactory = new DriverFactory();
        properties = driverFactory.init_prop();

        if (browser != null) {
            properties.setProperty("browser", browser);
            properties.setProperty("browserVersion", browserVersion);
        }

        driver = driverFactory.init_driver(properties);
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown() {

        driver.quit();
    }
}
