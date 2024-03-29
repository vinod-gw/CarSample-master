package com.arcbees.carsample.client.application.cars.car;

import com.arcbees.carsample.shared.domain.Car;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;
import javax.inject.Provider;

public class CarProxyFactory {
    private final Provider<CarPresenterProvider> carPresenterProvider;
    private final PlaceManager placeManager;
    private final EventBus eventBus;
    private final CarProxyImplFactory carProxyImplFactory;

    @Inject
    public CarProxyFactory(Provider<CarPresenterProvider> carPresenterProvider, final PlaceManager placeManager,
            final EventBus eventBus, final CarProxyImplFactory carProxyImplFactory) {
        this.carPresenterProvider = carPresenterProvider;
        this.placeManager = placeManager;
        this.eventBus = eventBus;
        this.carProxyImplFactory = carProxyImplFactory;
    }

    public CarPresenter.MyProxy create(Car car, String nameToken) {
        CarPresenterProvider carPresenter = carPresenterProvider.get();
        carPresenter.setCar(car);

        CarProxyImpl carProxy = carProxyImplFactory.create(carPresenter, nameToken);
        carProxy.bind(placeManager, eventBus);

        return carProxy;
    }
}
