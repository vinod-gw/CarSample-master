package com.arcbees.carsample.client.application.cars;

import com.arcbees.carsample.client.application.cars.car.CarPresenterFactory;
import com.arcbees.carsample.client.application.cars.car.CarProxyImplFactory;
import com.arcbees.carsample.client.application.cars.car.CarUiHandlers;
import com.arcbees.carsample.client.application.cars.car.RootCarPresenter;
import com.arcbees.carsample.client.application.cars.car.RootCarView;
import com.arcbees.carsample.client.application.cars.car.navigation.NavigationTabPresenter;
import com.arcbees.carsample.client.application.cars.car.navigation.NavigationTabView;
import com.arcbees.carsample.client.application.cars.car.navigation.NavigationUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.SetterUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CarsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bind(new TypeLiteral<UiHandlersStrategy<CarsUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<CarsUiHandlers>>() {
                });
        bind(CarsUiHandlers.class).to(CarsPresenter.class);

        bind(new TypeLiteral<UiHandlersStrategy<CarUiHandlers>>() {
        })
                .to(new TypeLiteral<SetterUiHandlersStrategy<CarUiHandlers>>() {
                });

        bindPresenter(RootCarPresenter.class, RootCarPresenter.MyView.class, RootCarView.class,
                RootCarPresenter.MyProxy.class);

        bindSingletonPresenterWidget(NavigationTabPresenter.class, NavigationTabPresenter.MyView.class,
                NavigationTabView.class);
        bind(new TypeLiteral<UiHandlersStrategy<NavigationUiHandlers>>() {
        })
                .to(new TypeLiteral<ProviderUiHandlersStrategy<NavigationUiHandlers>>() {
                });
        bind(NavigationUiHandlers.class).to(NavigationTabPresenter.class);

        install(new GinFactoryModuleBuilder().build(CarPresenterFactory.class));
        install(new GinFactoryModuleBuilder().build(CarProxyImplFactory.class));
    }
}
