package com.arcbees.carsample.client.application.rating;

import com.arcbees.carsample.client.application.rating.renderer.RatingCellFactory;
import com.arcbees.carsample.client.application.rating.ui.EditRatingPresenter;
import com.arcbees.carsample.client.application.rating.ui.EditRatingUiHandlers;
import com.arcbees.carsample.client.application.rating.ui.EditRatingView;
import com.arcbees.core.client.mvp.uihandlers.ProviderUiHandlersStrategy;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;
import com.google.inject.TypeLiteral;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RatingMobileModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(RatingPresenter.class, RatingPresenter.MyView.class, RatingMobileView.class,
                RatingPresenter.MyProxy.class);
        bind(new TypeLiteral<UiHandlersStrategy<RatingUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<RatingUiHandlers>>() {});
        bind(RatingUiHandlers.class).to(RatingPresenter.class);

        bindPresenter(RatingDetailPresenter.class, RatingDetailPresenter.MyView.class, RatingDetailView.class,
                RatingDetailPresenter.MyProxy.class);
        bind(new TypeLiteral<UiHandlersStrategy<RatingDetailUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<RatingDetailUiHandlers>>() {});
        bind(RatingDetailUiHandlers.class).to(RatingDetailPresenter.class);

        bindSingletonPresenterWidget(EditRatingPresenter.class, EditRatingPresenter.MyView.class,
                EditRatingView.class);
        bind(new TypeLiteral<UiHandlersStrategy<EditRatingUiHandlers>>() {})
                .to(new TypeLiteral<ProviderUiHandlersStrategy<EditRatingUiHandlers>>() {});
        bind(EditRatingUiHandlers.class).to(EditRatingPresenter.class);

        install(new GinFactoryModuleBuilder().build(RatingCellFactory.class));
    }
}
