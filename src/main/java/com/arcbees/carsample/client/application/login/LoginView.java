package com.arcbees.carsample.client.application.login;

import com.arcbees.core.client.mvp.ViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class LoginView extends ViewWithUiHandlers<LoginUiHandlers> implements LoginPresenter.MyView {
    public interface Binder extends UiBinder<Widget, LoginView> {
    }

    @UiField
    Button login;
    @UiField
    PasswordTextBox password;
    @UiField
    TextBox username;

    @Inject
    public LoginView(final Binder uiBinder, final UiHandlersStrategy<LoginUiHandlers> uiHandlersStrategy) {
        super(uiHandlersStrategy);

        initWidget(uiBinder.createAndBindUi(this));

        username.getElement().setAttribute("placeholder", "Username");
        password.getElement().setAttribute("placeholder", "Password");
    }

    @UiHandler("login")
    void onLoginClicked(ClickEvent event) {
        String username = this.username.getValue();
        String password = this.password.getValue();

        getUiHandlers().login(username, password);
    }

    @Override
    public void setLoginButtonEnabled(boolean enabled) {
        login.setEnabled(enabled);
    }
}
