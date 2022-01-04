package com.qa.aquabot.test;

import com.qa.aquabot.listeners.Retry;
import com.qa.aquabot.listeners.TestAllureListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Epic("Epic 100: Design Contact Form - Home Page")
@Story("RIM 101: Contact Form functionality with all features")
@Listeners(TestAllureListener.class)
public class ContactFromTest extends BaseTest {

    @Description("form submit test")
    @Severity(SeverityLevel.CRITICAL)
    @Test(retryAnalyzer= Retry.class)
    public void contactFromTest() throws IOException {

        homePage.submittingContactForm(properties.getProperty("name"),
                properties.getProperty("email"), properties.getProperty("phoneNumber"));
    }
}
