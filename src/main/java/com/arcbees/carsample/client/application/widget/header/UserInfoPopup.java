package com.arcbees.carsample.client.application.widget.header;

import com.arcbees.carsample.client.Resources;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class UserInfoPopup extends PopupPanel {
    public interface Binder extends UiBinder<Widget, UserInfoPopup> {
    }

    public interface Handler {
        void onLogout();
    }

    @UiField
    Label username;

    private Handler handler;

    @Inject
    public UserInfoPopup(final Binder uiBinder, final Resources resources) {
        setWidget(uiBinder.createAndBindUi(this));
        setAutoHideEnabled(true);
        setStyleName(resources.styles().gwtPopupPanel());
    }

    public void setUsername(String login) {
        username.setText(login);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @UiHandler("logout")
    void onLogoutClicked(ClickEvent event) {
        handler.onLogout();
        hide();
    }
}
