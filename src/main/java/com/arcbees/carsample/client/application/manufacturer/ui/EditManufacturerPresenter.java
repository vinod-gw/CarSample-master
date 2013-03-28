package com.arcbees.carsample.client.application.manufacturer.ui;

import com.arcbees.carsample.client.application.event.DisplayMessageEvent;
import com.arcbees.carsample.client.application.manufacturer.event.ManufacturerAddedEvent;
import com.arcbees.carsample.client.application.manufacturer.ui.EditManufacturerPresenter.MyView;
import com.arcbees.carsample.client.application.widget.message.Message;
import com.arcbees.carsample.client.application.widget.message.MessageStyle;
import com.arcbees.carsample.client.util.ErrorHandlerAsyncCallback;
import com.arcbees.carsample.shared.dispatch.GetResult;
import com.arcbees.carsample.shared.dispatch.SaveManufacturerAction;
import com.arcbees.carsample.shared.domain.Manufacturer;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.proxy.RevealRootPopupContentEvent;

import javax.inject.Inject;

public class EditManufacturerPresenter extends PresenterWidget<MyView> implements EditManufacturerUiHandlers {
    public interface MyView extends PopupView {
        void edit(Manufacturer manufacturer);
    }

    private final DispatchAsync dispatcher;
    private Manufacturer manufacturer;
    private final EditManufacturerMessages messages;

    @Inject
    public EditManufacturerPresenter(final EventBus eventBus, final MyView view, final DispatchAsync dispatcher,
            final EditManufacturerMessages messages) {
        super(eventBus, view);
        this.dispatcher = dispatcher;
        this.messages = messages;
    }

    @Override
    public void createNew() {
        manufacturer = new Manufacturer();

        reveal();
    }

    @Override
    public void onCancel() {
        getView().hide();
    }

    @Override
    public void edit(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;

        reveal();
    }

    @Override
    public void onSave(Manufacturer manufacturer) {
        dispatcher.execute(new SaveManufacturerAction(manufacturer),
                new ErrorHandlerAsyncCallback<GetResult<Manufacturer>>(this) {
                    @Override
                    public void onSuccess(GetResult<Manufacturer> result) {
                        DisplayMessageEvent.fire(EditManufacturerPresenter.this,
                                new Message(messages.manufacturerSaved(), MessageStyle.SUCCESS));
                        ManufacturerAddedEvent.fire(EditManufacturerPresenter.this, result.getResult());

                        getView().hide();
                    }
                });
    }

    private void reveal() {
        getView().edit(manufacturer);

        RevealRootPopupContentEvent.fire(this, this);
    }
}
