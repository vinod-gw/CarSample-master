package com.arcbees.carsample.client.security;

import com.gwtplatform.mvp.client.proxy.Gatekeeper;

import javax.inject.Inject;

public class LoggedInGatekeeper implements Gatekeeper {
    private final CurrentUser currentUser;

    @Inject
    public LoggedInGatekeeper(final CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public boolean canReveal() {
        return currentUser.isLoggedIn();
    }
}
