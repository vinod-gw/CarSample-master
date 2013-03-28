package com.arcbees.carsample.client.application.widget;

import com.arcbees.carsample.client.application.widget.header.HeaderMobileView;
import com.arcbees.carsample.client.application.widget.header.HeaderPresenter;
import com.arcbees.carsample.client.application.widget.header.HeaderUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetMobileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class,
                HeaderMobileView.class);

        bind(new TypeLiteral<UiHandlersStrategy<HeaderUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<HeaderUiHandlers>>() {
                });
        bind(HeaderUiHandlers.class).to(HeaderPresenter.class);
    }
}
