package com.arcbees.carsample.client.application.cars.car;

import com.arcbees.carsample.client.application.cars.car.CarPresenter.MyView;
import com.arcbees.carsample.client.application.cars.car.widget.CarPropertiesEditor;
import com.arcbees.carsample.client.application.manufacturer.ui.ManufacturerRenderer;
import com.arcbees.carsample.shared.domain.Car;
import com.arcbees.carsample.shared.domain.Manufacturer;
import com.arcbees.core.client.mvp.ViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;
import java.util.List;

public class CarMobileView extends ViewWithUiHandlers<CarUiHandlers> implements MyView, Editor<Car> {
    public interface Binder extends UiBinder<Widget, CarMobileView> {
    }

    public interface Driver extends SimpleBeanEditorDriver<Car, CarMobileView> {
    }

    @UiField
    TextBox model;
    @UiField(provided = true)
    ValueListBox<Manufacturer> manufacturer;
    @UiField
    CarPropertiesEditor carProperties;

    private final Driver driver;

    @Inject
    public CarMobileView(final Binder uiBinder, final Driver driver,
            final UiHandlersStrategy<CarUiHandlers> uiHandlersStrategy) {
        super(uiHandlersStrategy);

        manufacturer = new ValueListBox<Manufacturer>(new ManufacturerRenderer());
        this.driver = driver;

        initWidget(uiBinder.createAndBindUi(this));
        driver.initialize(this);

        model.getElement().setAttribute("placeholder", "Model");
    }

    @Override
    public void edit(Car car) {
        if (car.getManufacturer() == null) {
            car.setManufacturer(manufacturer.getValue());
        }

        driver.edit(car);
    }

    @Override
    public void setAllowedManufacturers(List<Manufacturer> manufacturers) {
        manufacturer.setValue(manufacturers.isEmpty() ? null : manufacturers.get(0));
        manufacturer.setAcceptableValues(manufacturers);
    }

    @Override
    public void resetFields(Car car) {
        driver.edit(car);
    }

    @Override
    public void getCar() {
        getUiHandlers().onSave(driver.flush());
    }
}
