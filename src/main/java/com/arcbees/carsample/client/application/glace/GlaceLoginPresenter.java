package com.arcbees.carsample.client.application.glace;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.arcbees.carsample.client.application.ApplicationPresenter;
import com.arcbees.carsample.client.application.event.ActionBarVisibilityEvent;
import com.arcbees.carsample.client.application.event.DisplayMessageEvent;
import com.arcbees.carsample.client.application.event.UserLoginEvent;
import com.arcbees.carsample.client.application.login.LoginMessages;
import com.arcbees.carsample.client.application.login.LoginPresenter;
import com.arcbees.carsample.client.application.login.LoginUiHandlers;
import com.arcbees.carsample.client.application.login.LoginPresenter.MyProxy;
import com.arcbees.carsample.client.application.login.LoginPresenter.MyView;
import com.arcbees.carsample.client.application.widget.message.Message;
import com.arcbees.carsample.client.application.widget.message.MessageStyle;
import com.arcbees.carsample.client.place.NameTokens;
import com.arcbees.carsample.client.security.CurrentUser;
import com.arcbees.carsample.shared.dispatch.ActionType;
import com.arcbees.carsample.shared.dispatch.LogInAction;
import com.arcbees.carsample.shared.dispatch.LogInResult;
import com.arcbees.carsample.shared.dto.CurrentUserDto;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.google.common.base.Strings;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class GlaceLoginPresenter extends
		Presenter<GlaceLoginPresenter.MyView, GlaceLoginPresenter.MyProxy> implements GlaceUiHandlers{

	public interface MyView extends View {
		public Button getLogin();
	}

	@Inject PlaceManager placemanager;
	@ProxyCodeSplit
	@NameToken(NameTokens.glacelogin)
	public interface MyProxy extends ProxyPlace<GlaceLoginPresenter> {
	}
	public static final String LOGIN_COOKIE_NAME = "LoggedInCookie";
	private static final Logger logger = Logger.getLogger(GlaceLoginPresenter.class.getName());
    private final PlaceManager placeManager;
    private final DispatchAsync dispatchAsync;
    private final CurrentUser currentUser;
    private final LoginMessages messages;
	 
	 	@Inject
	    public GlaceLoginPresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
	            final PlaceManager placeManager, final DispatchAsync dispatchAsync,
	            final CurrentUser currentUser, final LoginMessages messages) {
	        super(eventBus, view, proxy);

	        this.placeManager = placeManager;
	        this.dispatchAsync = dispatchAsync;
	        this.currentUser = currentUser;
	        this.messages = messages;
	    }
	 
	 
	 @Override
	    public void glaceLogin(String username, String password, String accountid) {
		 PlaceRequest homePlaceRequest = new PlaceRequest(NameTokens.getHome());
         placeManager.revealPlace(homePlaceRequest);
	    }
	 
	 @Override
	    public void login(String username, String password, String accountid) {
		 	LogInAction loginAction = new LogInAction(username, password, accountid);
	        callServerLoginAction(loginAction);
	    }
	 private void callServerLoginAction(LogInAction loginAction) {
	        dispatchAsync.execute(loginAction, new AsyncCallback<LogInResult>() {
	            @Override
	            public void onFailure(Throwable e) {
	                DisplayMessageEvent.fire(GlaceLoginPresenter.this, new Message(messages.unableToContactServer(),
	                        MessageStyle.ERROR));
	                
	                logger.log(Level.SEVERE, "callServerLoginAction(): Server failed to process login call.", e);
	            }

	            @Override
	            public void onSuccess(LogInResult result) {
	            	 if (result.getCurrentUserDto().isLoggedIn()) {
	                     setLoggedInCookie(result.getLoggedInCookie());
	                 }

	                 if (result.getActionType() == ActionType.VIA_COOKIE) {
	                     onLoginCallSuceededForCookie(result.getCurrentUserDto());
	                 } else {
	                     onLoginCallSuceeded(result.getCurrentUserDto());
	                 }

	               
	            }
	        });
	    }
	 
	 private void onLoginCallSuceededForCookie(CurrentUserDto currentUserDto) {
	     //   getView().setLoginButtonEnabled(true);

	        if (currentUserDto.isLoggedIn()) {
	            onLoginCallSuceeded(currentUserDto);
	        }
	    }
	 
	 private void onLoginCallSuceeded(CurrentUserDto currentUserDto) {
	        if (currentUserDto.isLoggedIn()) {
	            currentUser.fromCurrentUserDto(currentUserDto);

	            PlaceRequest homePlaceRequest = new PlaceRequest(NameTokens.getHome());
	            placeManager.revealPlace(homePlaceRequest);

	            UserLoginEvent.fire(this);
	            DisplayMessageEvent.fire(this, new Message(messages.onSuccessfulLogin(), MessageStyle.SUCCESS));
	        } else {
	            DisplayMessageEvent.fire(this, new Message(messages.invalidEmailOrPassword(), MessageStyle.ERROR));
	        }
	    }
	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
		// RevealContentEvent.fire(this, ApplicationPresenter.TYPE_SetMainContent, this);
	}
	 @Override
	    protected void onReveal() {
	        ActionBarVisibilityEvent.fire(this, false);

	        if (!Strings.isNullOrEmpty(getLoggedInCookie())) {
	          //  tryLoggingInWithCookieFirst();
	        }
	    } 
	 private void tryLoggingInWithCookieFirst() {
     //   getView().setLoginButtonEnabled(false);
        LogInAction loginAction = new LogInAction(getLoggedInCookie());
        callServerLoginAction(loginAction);
    }

    private String getLoggedInCookie() {
        return Cookies.getCookie(LOGIN_COOKIE_NAME);
    }
    private void setLoggedInCookie(String value) {
        Cookies.removeCookie(LOGIN_COOKIE_NAME);

        Date expires = new Date();
        CalendarUtil.addDaysToDate(expires, 14);
        String domain = getDomain();
        String path = "/";
        boolean secure = false;
        Cookies.setCookie(LOGIN_COOKIE_NAME, value, expires, domain, path, secure);

        logger.info("LoginPresenter.setLoggedInCookie() Set client cookie=" + value);
    }
    
    private String getDomain() {
        String domain = GWT.getHostPageBaseURL();
        domain = domain.replaceAll(".*//", "");
        domain = domain.replaceAll("/", "");
        domain = domain.replaceAll(":.*", "");

        return domain;
    }

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();		
	}
}