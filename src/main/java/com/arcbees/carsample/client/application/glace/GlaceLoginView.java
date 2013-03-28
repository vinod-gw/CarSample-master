package com.arcbees.carsample.client.application.glace;


import com.arcbees.carsample.client.application.login.LoginUiHandlers;
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

public class GlaceLoginView extends ViewWithUiHandlers<GlaceUiHandlers> implements
		GlaceLoginPresenter.MyView {
	
	public interface Binder extends UiBinder<Widget, GlaceLoginView> {
	}
	
    @UiField
    Button glacelogin;
    @UiField
    PasswordTextBox password;
    @UiField
    TextBox username;
    @UiField
    TextBox accountid;
    
    

	@Inject
	public GlaceLoginView(final Binder uiBinder, final UiHandlersStrategy<GlaceUiHandlers> uiHandlersStrategy) {
        super(uiHandlersStrategy);

        initWidget(uiBinder.createAndBindUi(this));

        username.getElement().setAttribute("placeholder", "Username");
        password.getElement().setAttribute("placeholder", "Password");
        accountid.getElement().setAttribute("placeholder", "AccountId");
    }
	
	@UiHandler("glacelogin")
	    void onLoginClicked(ClickEvent event) {
	        String username = this.username.getValue();
	        String password = this.password.getValue();
	        String accountid = this.accountid.getValue();
	        getUiHandlers().login(username, password, accountid);
	    }
    
	public Button getLogin() {
		return glacelogin;
	}
}