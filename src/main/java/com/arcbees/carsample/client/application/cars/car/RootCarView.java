package com.arcbees.carsample.client.application.cars.car;

import com.arcbees.core.client.mvp.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class RootCarView extends ViewImpl implements RootCarPresenter.MyView {
    public interface Binder extends UiBinder<HTMLPanel, RootCarView> {
    }

    @UiField
    SimplePanel tabBarPanel;
    @UiField
    SimplePanel contentPanel;

    @Inject
    public RootCarView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (slot == RootCarPresenter.TYPE_SetTabBar) {
            tabBarPanel.setWidget(content);
        } else if (slot == RootCarPresenter.TYPE_SetCarContent) {
            contentPanel.setWidget(content);
        }
    }
}
