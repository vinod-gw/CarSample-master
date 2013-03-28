package com.arcbees.carsample.client.application.login;

import com.arcbees.carsample.client.application.glace.GlaceLoginPresenter;
import com.arcbees.carsample.client.application.glace.GlaceLoginView;
import com.arcbees.carsample.client.application.glace.GlaceUiHandlers;
import com.arcbees.carsample.client.application.glace.HomePresenter;
import com.arcbees.carsample.client.application.glace.HomeView;
import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.arcbees.carsample.client.application.glace.AccessPresenter;
import com.arcbees.carsample.client.application.glace.AccessView;


public class LoginModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class,
                LoginPresenter.MyProxy.class);

        bind(new TypeLiteral<UiHandlersStrategy<LoginUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<LoginUiHandlers>>() {
                });
        bind(new TypeLiteral<UiHandlersStrategy<GlaceUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<GlaceUiHandlers>>() {
                });
        bind(LoginUiHandlers.class).to(LoginPresenter.class); 
        bind(GlaceUiHandlers.class).to(GlaceLoginPresenter.class);
		bindPresenter(GlaceLoginPresenter.class, GlaceLoginPresenter.MyView.class,
				GlaceLoginView.class, GlaceLoginPresenter.MyProxy.class);

		bindPresenter(HomePresenter.class, HomePresenter.MyView.class,
				HomeView.class, HomePresenter.MyProxy.class);

		bindPresenter(AccessPresenter.class, AccessPresenter.MyView.class,
				AccessView.class, AccessPresenter.MyProxy.class);
    }
}