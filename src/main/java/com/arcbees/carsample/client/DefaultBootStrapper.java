package com.arcbees.carsample.client;

import com.arcbees.carsample.client.security.CurrentUser;
import com.arcbees.carsample.shared.BootStrapper;
import com.arcbees.carsample.shared.dispatch.GetCurrentUserAction;
import com.arcbees.carsample.shared.dispatch.GetCurrentUserResult;
import com.arcbees.carsample.shared.dto.CurrentUserDto;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;

public class DefaultBootStrapper implements BootStrapper {
    private final PlaceManager placeManager;
    private final DispatchAsync dispatchAsync;
    private final CurrentUser currentUser;

    @Inject
    public DefaultBootStrapper(final PlaceManager placeManager, final DispatchAsync dispatchAsync,
            final CurrentUser currentUser) {
        this.placeManager = placeManager;
        this.dispatchAsync = dispatchAsync;
        this.currentUser = currentUser;
    }

    @Override
    public void init() {
        getCurrentUser();
    }

    private void getCurrentUser() {
        dispatchAsync.execute(new GetCurrentUserAction(), new AsyncCallback<GetCurrentUserResult>() {
            @Override
            public void onFailure(Throwable caught) {
                placeManager.revealCurrentPlace();
            }

            @Override
            public void onSuccess(GetCurrentUserResult result) {
                onGetCurrentUserSuccess(result.getCurrentUserDto());
            }
        });
    }

    private void onGetCurrentUserSuccess(CurrentUserDto currentUserDto) {
        currentUser.fromCurrentUserDto(currentUserDto);
        placeManager.revealCurrentPlace();
    }
}
