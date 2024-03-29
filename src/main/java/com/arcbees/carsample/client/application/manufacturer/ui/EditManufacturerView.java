package com.arcbees.carsample.client.application.manufacturer.ui;

import com.arcbees.carsample.client.application.manufacturer.ui.EditManufacturerPresenter.MyView;
import com.arcbees.carsample.shared.domain.Manufacturer;
import com.arcbees.core.client.mvp.PopupViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;

import javax.inject.Inject;

public class EditManufacturerView extends PopupViewWithUiHandlers<EditManufacturerUiHandlers> implements MyView,
        Editor<Manufacturer> {
    public interface Binder extends UiBinder<Widget, EditManufacturerView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Manufacturer, EditManufacturerView> {
    }

    @UiField
    TextBox name;

    private final Driver driver;

    @Inject
    public EditManufacturerView(final Binder uiBinder, final Driver driver, final EventBus eventBus,
            final UiHandlersStrategy<EditManufacturerUiHandlers> uiHandlersStrategy) {
        super(eventBus, uiHandlersStrategy);

        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);
    }

    @Override
    public void edit(Manufacturer manufacturer) {
        driver.edit(manufacturer);
    }

    @UiHandler("save")
    void onSaveClicked(ClickEvent ignored) {
        getUiHandlers().onSave(driver.flush());
    }

    @UiHandler("close")
    void onCloseClicked(ClickEvent ignored) {
        getUiHandlers().onCancel();
    }
}
