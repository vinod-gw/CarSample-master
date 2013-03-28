package com.arcbees.carsample.client.application;

import com.arcbees.core.client.mvp.ViewImpl;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    HTMLPanel container;
    @UiField
    SimplePanel main;
    @UiField
    SimplePanel header;
    @UiField
    SimplePanel messages;

    @Inject
    public ApplicationView(final Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, Widget content) {
        if (slot == ApplicationPresenter.TYPE_SetMainContent) {
            main.setWidget(content);
        } else if (slot == ApplicationPresenter.TYPE_SetHeaderContent) {
            header.setWidget(content);
        } else if (slot == ApplicationPresenter.TYPE_SetMessagesContent) {
            messages.setWidget(content);
        }
    }

    @Override
    public void adjustActionBar(Boolean actionBarVisible) {
    }

    @Override
    public void adjustLayout(Boolean tabsVisible) {
    }
}
