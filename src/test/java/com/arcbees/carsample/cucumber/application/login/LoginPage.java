package com.arcbees.carsample.cucumber.application.login;

import com.arcbees.carsample.cucumber.application.BasePage;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.inject.Inject;

import static com.google.gwt.user.client.ui.UIObject.DEBUG_ID_PREFIX;

public class LoginPage extends BasePage {
    private static final String LOGIN_ID = DEBUG_ID_PREFIX + "login";

    @FindBy(id = DEBUG_ID_PREFIX + "username")
    private WebElement username;
    @FindBy(id = DEBUG_ID_PREFIX + "password")
    private WebElement password;
    @FindBy(id = DEBUG_ID_PREFIX + "accountid")
    private WebElement accountid;
    @FindBy(id = LOGIN_ID)
    private WebElement login;

    @Inject
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

   	public void setUsername(String username) {
        webDriverWait().until(ExpectedConditions.visibilityOf(this.username));
        this.username.sendKeys(username);
    }

    public void setPassword(String password) {
        webDriverWait().until(ExpectedConditions.visibilityOf(this.password));
        this.password.sendKeys(password);
    }
	public void setAccountid(String accountid) {
        webDriverWait().until(ExpectedConditions.visibilityOf(this.accountid));
        this.accountid.sendKeys(accountid);
    }

    public void submitLoginForm() {
        webDriverWait().until(ExpectedConditions.visibilityOf(login));
        login.click();
    }

    public Boolean isOnLoginPage() {
        try {
            webDriverWait().until(ExpectedConditions.visibilityOf(login));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
