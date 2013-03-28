package com.arcbees.carsample.client.security;

import com.google.gwt.inject.client.AbstractGinModule;

import javax.inject.Singleton;

public class SecurityModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(LoggedInGatekeeper.class).in(Singleton.class);
        bind(CurrentUser.class).asEagerSingleton();
    }
}
