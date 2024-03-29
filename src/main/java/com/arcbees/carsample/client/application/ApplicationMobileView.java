package com.arcbees.carsample.client.application;

import com.arcbees.core.client.mvp.ViewImpl;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class ApplicationMobileView extends ViewImpl implements ApplicationPresenter.MyView {
    public interface Binder extends UiBinder<Widget, ApplicationMobileView> {
    }

    private static final Double HEIGHT_WITH_TABS = 89d;
    private static final Double HEIGHT_WITHOUT_TABS = 47d;

    @UiField
    LayoutPanel rootPanel;
    @UiField
    HTMLPanel headerWrapper;
    @UiField
    SimplePanel main;
    @UiField
    SimplePanel header;
    @UiField
    SimplePanel messages;

    @Inject
    public ApplicationMobileView(final Binder uiBinder) {
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
        if (actionBarVisible) {
            rootPanel.setWidgetTopBottom(main, HEIGHT_WITH_TABS, Style.Unit.PX, 0d, Style.Unit.PX);
        } else {
            rootPanel.setWidgetTopBottom(main, 0, Style.Unit.PX, 0d, Style.Unit.PX);
        }
    }

    @Override
    public void adjustLayout(Boolean tabsVisible) {
        double offset = tabsVisible ? HEIGHT_WITH_TABS : HEIGHT_WITHOUT_TABS;
        rootPanel.setWidgetTopHeight(headerWrapper, 0d, Style.Unit.PX, offset, Style.Unit.PX);
        rootPanel.setWidgetTopBottom(main, offset, Style.Unit.PX, 0d, Style.Unit.PX);
    }
}
