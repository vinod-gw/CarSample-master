package com.arcbees.carsample.client.gin;

import com.arcbees.carsample.client.Resources;
import com.arcbees.carsample.client.application.ApplicationPresenter;
import com.arcbees.carsample.client.application.cars.CarsPresenter;
import com.arcbees.carsample.client.application.cars.car.RootCarPresenter;
import com.arcbees.carsample.client.application.login.LoginPresenter;
import com.arcbees.carsample.client.application.manufacturer.ManufacturerDetailPresenter;
import com.arcbees.carsample.client.application.manufacturer.ManufacturerPresenter;
import com.arcbees.carsample.client.application.rating.RatingDetailPresenter;
import com.arcbees.carsample.client.application.rating.RatingPresenter;
import com.arcbees.carsample.client.application.report.ReportPresenter;
import com.arcbees.carsample.client.security.LoggedInGatekeeper;
import com.arcbees.carsample.shared.BootStrapper;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.arcbees.carsample.client.application.glace.GlaceLoginPresenter;
import com.arcbees.carsample.client.application.glace.HomePresenter;
import com.arcbees.carsample.client.application.glace.AccessPresenter;

public interface ClientGinjector extends Ginjector {
    EventBus getEventBus();

    BootStrapper getBootStrapper();

    Resources getResources();

    PlaceManager getPlaceManager();

    LoggedInGatekeeper getLoggedInGatekeeper();

    Provider<LoginPresenter> getLoginPresenter();

    Provider<ApplicationPresenter> getAppPresenter();

    Provider<RootCarPresenter> getRootCarPresenter();

    AsyncProvider<RatingPresenter> getRatingPresenter();

    AsyncProvider<RatingDetailPresenter> getRatingDetailPresenter();

    AsyncProvider<ManufacturerPresenter> getManufacturerPresenter();

    AsyncProvider<ManufacturerDetailPresenter> getManufacturerDetailPresenter();

    AsyncProvider<CarsPresenter> getCarPresenter();

    AsyncProvider<ReportPresenter> getReportPresenter();

	AsyncProvider<GlaceLoginPresenter> getGlaceLoginPresenter();

	AsyncProvider<HomePresenter> getHomePresenter();

	AsyncProvider<AccessPresenter> getAccessPresenter();
}
