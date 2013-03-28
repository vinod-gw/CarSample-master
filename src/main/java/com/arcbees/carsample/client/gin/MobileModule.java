package com.arcbees.carsample.client.gin;

import com.arcbees.carsample.client.DefaultBootStrapper;
import com.arcbees.carsample.client.Resources;
import com.arcbees.carsample.client.application.ApplicationMobileModule;
import com.arcbees.carsample.client.place.ClientPlaceManager;
import com.arcbees.carsample.client.place.DefaultPlace;
import com.arcbees.carsample.client.place.NameTokens;
import com.arcbees.carsample.client.security.SecurityModule;
import com.arcbees.carsample.shared.BootStrapper;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import javax.inject.Singleton;

public class MobileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new DefaultModule(ClientPlaceManager.class));
        install(new ApplicationMobileModule());
        install(new SecurityModule());

        bind(Resources.class).in(Singleton.class);

        bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.login);
        bind(BootStrapper.class).to(DefaultBootStrapper.class).in(Singleton.class);
    }
}
