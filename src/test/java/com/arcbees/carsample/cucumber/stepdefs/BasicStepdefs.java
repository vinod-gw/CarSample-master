package com.arcbees.carsample.cucumber.stepdefs;

import com.arcbees.carsample.cucumber.application.ApplicationPage;
import com.arcbees.carsample.cucumber.application.login.LoginPage;
import com.arcbees.carsample.cucumber.application.widgets.HeaderWidgetPage;

import cucumber.annotation.After;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.When;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;

public class BasicStepdefs {
    private static final String baseUrl = "http://localhost:8080/CarSample-0.1-SNAPSHOT";
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "qwerty";
   // private static final String VALID_ACCOUNTID = "glace";
    private static final String INVALID_USERNAME = "--";
    private static final String INVALID_PASSWORD = "--";
    private static final int TIME_OUT_IN_SECONDS = 60;

    private final WebDriver webDriver;
    private final LoginPage loginPage;
    private final ApplicationPage applicationPage;
    private final HeaderWidgetPage headerWidgetPage;

    @Inject
    public BasicStepdefs(WebDriver webDriver, LoginPage loginPage, ApplicationPage applicationPage,
                         HeaderWidgetPage headerWidgetPage) {
        this.webDriver = webDriver;
        this.loginPage = loginPage;
        this.applicationPage = applicationPage;
        this.headerWidgetPage = headerWidgetPage;
    }

    @After
    public void cleanup() {
        webDriver.close();
    }

    @Given("^I navigate to the (\\S+) page$")
    public void iNavigateTo(String nameToken) {
        String url = baseUrl + "#" + nameToken;

        webDriver.get(url);
        applicationPage.waitUntilDomIsLoaded(nameToken);
    }

    @Given("^I'm logged in$")
    public void iAmLoggedIn() {
        iNavigateTo("login");
        enterValidCredential("valid");
        applicationPage.waitUntilDomIsLoaded("manufacturer");
    }

    @When("^I enter (\\S+) credential$")
    public void enterValidCredential(String valid) {
        if (valid.equals("valid")) {
            loginPage.setUsername(VALID_USERNAME);
            loginPage.setPassword(VALID_PASSWORD);
        } else {
            loginPage.setUsername(INVALID_USERNAME);
            loginPage.setPassword(INVALID_PASSWORD);
        }
        loginPage.submitLoginForm();
    }
}
