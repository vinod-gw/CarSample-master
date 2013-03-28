package com.arcbees.carsample.client.application.manufacturer;

import com.arcbees.carsample.client.application.manufacturer.ui.EditManufacturerPresenter;
import com.arcbees.carsample.client.application.manufacturer.ui.EditManufacturerUiHandlers;
import com.arcbees.carsample.client.application.manufacturer.ui.EditManufacturerView;
import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ManufacturerMobileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ManufacturerPresenter.class, ManufacturerPresenter.MyView.class, ManufacturerMobileView.class,
                ManufacturerPresenter.MyProxy.class);
        bind(new TypeLiteral<UiHandlersStrategy<ManufacturerUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<ManufacturerUiHandlers>>() {});
        bind(ManufacturerUiHandlers.class).to(ManufacturerPresenter.class);

        bindPresenter(ManufacturerDetailPresenter.class, ManufacturerDetailPresenter.MyView.class,
                ManufacturerDetailView.class, ManufacturerDetailPresenter.MyProxy.class);
        bind(new TypeLiteral<UiHandlersStrategy<ManufacturerDetailUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<ManufacturerDetailUiHandlers>>() {});
        bind(ManufacturerDetailUiHandlers.class).to(ManufacturerDetailPresenter.class);

        bindSingletonPresenterWidget(EditManufacturerPresenter.class, EditManufacturerPresenter.MyView.class,
                EditManufacturerView.class);
        bind(new TypeLiteral<UiHandlersStrategy<EditManufacturerUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<EditManufacturerUiHandlers>>() {});
        bind(EditManufacturerUiHandlers.class).to(EditManufacturerPresenter.class);
    }
}
