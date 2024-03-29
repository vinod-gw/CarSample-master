package com.arcbees.carsample.cucumber.application;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.inject.Inject;

import static com.google.gwt.user.client.ui.UIObject.DEBUG_ID_PREFIX;

public class ApplicationPage extends BasePage {
    @Inject
    public ApplicationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Boolean waitUntilDomIsLoaded(String nameToken) {
        try {
            webDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id(DEBUG_ID_PREFIX + nameToken +
                    "Panel")));
            webDriverWait().until(ExpectedConditions.presenceOfElementLocated(By.id(DEBUG_ID_PREFIX + "dom")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
