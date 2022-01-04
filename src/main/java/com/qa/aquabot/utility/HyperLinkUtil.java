package com.qa.aquabot.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HyperLinkUtil extends HelperUtil {

    private WebDriver driver;

    public HyperLinkUtil(WebDriver driver) {
        super(driver);
    }

    public void clickLink(String linkText) {
        getElement(By.linkText(linkText)).click();
    }

    public void clickPartialLink(String partialLinkText) {
        getElement(By.partialLinkText(partialLinkText)).click();
    }

    public String getHyperLink(By locator) {
        return getHyperLink(getElement(locator));
    }

    public String getHyperLink(WebElement element) {
        String link = element.getAttribute("hreg");
        return link;
    }
}
