package com.arcbees.carsample.client.application.manufacturer;

import com.arcbees.carsample.shared.domain.Manufacturer;
import com.arcbees.core.client.mvp.ViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

public class ManufacturerDetailView extends ViewWithUiHandlers<ManufacturerDetailUiHandlers>
        implements ManufacturerDetailPresenter.MyView, Editor<Manufacturer> {
    public interface Binder extends UiBinder<Widget, ManufacturerDetailView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Manufacturer, ManufacturerDetailView> {
    }

    @UiField
    TextBox name;

    private final Driver driver;

    @Inject
    public ManufacturerDetailView(final Binder uiBinder, final Driver driver,
            final UiHandlersStrategy<ManufacturerDetailUiHandlers> uiHandler) {
        super(uiHandler);

        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        name.getElement().setAttribute("placeholder", "Manufacturer name");
    }

    @Override
    public void edit(Manufacturer manufacturer) {
        name.setFocus(true);
        driver.edit(manufacturer);
    }

    @Override
    public void getManufacturer() {
        getUiHandlers().onSave(driver.flush());
    }
}

