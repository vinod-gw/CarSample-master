package com.arcbees.carsample.client.application.widget.header;

import com.arcbees.carsample.client.application.event.ActionBarEvent;
import com.arcbees.carsample.client.application.event.ActionBarVisibilityEvent;
import com.arcbees.carsample.client.application.event.ChangeActionBarEvent;
import com.arcbees.carsample.client.application.event.ChangeActionBarEvent.ActionType;
import com.arcbees.carsample.client.application.event.DisplayMessageEvent;
import com.arcbees.carsample.client.application.event.GoBackEvent;
import com.arcbees.carsample.client.application.event.UserLoginEvent;
import com.arcbees.carsample.client.application.login.LoginPresenter;
import com.arcbees.carsample.client.application.widget.message.Message;
import com.arcbees.carsample.client.application.widget.message.MessageStyle;
import com.arcbees.carsample.client.place.DefaultPlace;
import com.arcbees.carsample.client.security.CurrentUser;
import com.arcbees.carsample.shared.dispatch.LogoutAction;
import com.arcbees.carsample.shared.dispatch.LogoutResult;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

import java.util.logging.Logger;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView> implements HeaderUiHandlers,
        UserLoginEvent.UserLoginHandler, ChangeActionBarEvent.ChangeActionBarHandler,
        ActionBarVisibilityEvent.ActionBarVisibilityHandler {
    public interface MyView extends View {
        void enableUserOptions(CurrentUser currentUser);

        void disableUserOptions();

        void showActionBar(Boolean visible);

        void initActionBar(Boolean tabsVisible);

        void hideActionButtons();

        void showActionButton(ActionType actionType);
    }

    private static final Logger logger = Logger.getLogger(HeaderPresenter.class.getName());

    private final DispatchAsync dispatchAsync;
    private final String defaultPlaceNameToken;
    private final PlaceManager placeManager;
    private final CurrentUser currentUser;
    private final HeaderMessages messages;

    @Inject
    public HeaderPresenter(final EventBus eventBus, final MyView view, final DispatchAsync dispatchAsync,
            @DefaultPlace final String defaultPlaceNameToken, final PlaceManager placeManager,
            final CurrentUser currentUser, final HeaderMessages messages) {
        super(eventBus, view);

        this.dispatchAsync = dispatchAsync;
        this.defaultPlaceNameToken = defaultPlaceNameToken;
        this.placeManager = placeManager;
        this.currentUser = currentUser;
        this.messages = messages;
    }

    @Override
    public void logout() {
        dispatchAsync.execute(new LogoutAction(), new AsyncCallback<LogoutResult>() {
            @Override
            public void onFailure(Throwable caught) {
                DisplayMessageEvent.fire(HeaderPresenter.this, new Message(messages.errorLoggingOut(),
                        MessageStyle.ERROR));
            }

            @Override
            public void onSuccess(LogoutResult result) {
                onLogoutSuccess();
            }
        });
    }

    @Override
    public void onLogin(UserLoginEvent event) {
        getView().enableUserOptions(currentUser);
    }

    @Override
    public void onActionBarVisible(ActionBarVisibilityEvent event) {
        getView().showActionBar(event.isVisible());
    }

    @Override
    public void onChangeActionBar(ChangeActionBarEvent event) {
        getView().initActionBar(event.getTabsVisible());
        getView().hideActionButtons();
        for (ActionType actionType : event.getActions()) {
            getView().showActionButton(actionType);
        }
    }

    @Override
    public void onAction(ActionType actionType) {
        String sourceToken = placeManager.getCurrentPlaceRequest().getNameToken();
        ActionBarEvent.fire(this, actionType, sourceToken);
    }

    @Override
    public void onGoBack() {
        GoBackEvent.fire(this);
    }

    @Override
    protected void onBind() {
        addRegisteredHandler(UserLoginEvent.getType(), this);
        addRegisteredHandler(ActionBarVisibilityEvent.getType(), this);
        addRegisteredHandler(ChangeActionBarEvent.getType(), this);

        if (currentUser.isLoggedIn()) {
            getView().enableUserOptions(currentUser);
        }
    }

    private void onLogoutSuccess() {
        resetLoggedInCookie();

        currentUser.reset();
        getView().disableUserOptions();

        PlaceRequest placeRequest = new PlaceRequest(defaultPlaceNameToken);
        placeManager.revealPlace(placeRequest);
    }

    private void resetLoggedInCookie() {
        Cookies.removeCookie(LoginPresenter.LOGIN_COOKIE_NAME);

        logger.info("HeaderPresenter.resetLoggedInCookie(): The cookie was removed from client.");
    }
}
