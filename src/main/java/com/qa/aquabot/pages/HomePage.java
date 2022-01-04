package com.qa.aquabot.pages;

import com.qa.aquabot.utility.ElementUtil;
import com.qa.aquabot.utility.HelperUtil;
import com.qa.aquabot.utility.JavaScriptUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;
    private ElementUtil elementUtil;
    private JavaScriptUtil javaScriptUtil;
    private HelperUtil helperUtil;

    private By homePageLogo = By.xpath("//img[@alt='Aquabot']");
    private By headerFormsLink = By.linkText("Forms");

    private By nameInputField = By.xpath("//input[@id='cname']");
    private By emailInputField = By.xpath("//input[@id='cemail']");
    private By phoneNoInputField = By.xpath("//input[@id='cphone']");
    private By mobilePhoneRadioButton = By.xpath("(//span[@class='radio'])[2]");
    private By staticDropDown = By.xpath("//select[@id='cselect']");
    private By successCheckBox = By.xpath("//input[@id='csuccess']");
    private By formSubmitButton = By.xpath("//button[@id='submit']");

    public HomePage(WebDriver driver) {

        this.driver = driver;
        elementUtil = new ElementUtil(driver);
        javaScriptUtil = new JavaScriptUtil(driver);
        helperUtil = new HelperUtil(driver);
    }

    @Step("filling contact details and submitting form successfully")
    public void submittingContactForm(String name, String email, String phoneNumber) {

        elementUtil.doClick(headerFormsLink);
        elementUtil.doPresenceOfElementLocated(nameInputField, 2);
        elementUtil.doClick(nameInputField);
        elementUtil.doSendKeys(nameInputField, name);
        elementUtil.doSendKeys(emailInputField, email);
        elementUtil.doSendKeys(phoneNoInputField, phoneNumber);
        elementUtil.doClick(mobilePhoneRadioButton);
        helperUtil.takeCustomScreenshot();
        elementUtil.doDropDownSelectByVisibleText(staticDropDown, "Frameworks");
        elementUtil.doClick(successCheckBox);
        javaScriptUtil.scrollIntoView(elementUtil.getElement(formSubmitButton));
        System.out.println("Test Completed on: " + helperUtil.getCurrentDateTime());
    }
}
