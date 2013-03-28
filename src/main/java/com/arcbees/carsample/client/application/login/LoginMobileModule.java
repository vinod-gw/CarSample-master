package com.arcbees.carsample.client.application.login;

import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LoginMobileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class, LoginMobileView.class,
                LoginPresenter.MyProxy.class);

        bind(new TypeLiteral<UiHandlersStrategy<LoginUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<LoginUiHandlers>>() {
                });
        bind(LoginUiHandlers.class).to(LoginPresenter.class);
    }
}
