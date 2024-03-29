package com.arcbees.carsample.client.application.manufacturer;

import com.arcbees.carsample.client.application.manufacturer.ManufacturerPresenter.MyView;
import com.arcbees.carsample.client.application.manufacturer.renderer.ManufacturerCell;
import com.arcbees.carsample.client.resource.MobileDataListStyle;
import com.arcbees.carsample.shared.domain.Manufacturer;
import com.arcbees.core.client.mvp.ViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import javax.inject.Inject;
import java.util.List;

public class ManufacturerMobileView extends ViewWithUiHandlers<ManufacturerUiHandlers> implements MyView {
    public interface Binder extends UiBinder<Widget, ManufacturerMobileView> {
    }

    @UiField(provided = true)
    CellList<Manufacturer> manufacturerList;

    private final ListDataProvider<Manufacturer> manufacturerDataProvider;
    private final SingleSelectionModel<Manufacturer> selectionModel;

    @Inject
    public ManufacturerMobileView(final Binder uiBinder,
            final ManufacturerCell manufacturerCell, final MobileDataListStyle mobileDataListStyle,
            final UiHandlersStrategy<ManufacturerUiHandlers> manufacturerUiHandlersUiHandlersStrategy) {
        super(manufacturerUiHandlersUiHandlersStrategy);

        manufacturerList = new CellList<Manufacturer>(manufacturerCell, mobileDataListStyle);

        initWidget(uiBinder.createAndBindUi(this));

        manufacturerDataProvider = new ListDataProvider<Manufacturer>();
        manufacturerDataProvider.addDataDisplay(manufacturerList);
        selectionModel = new SingleSelectionModel<Manufacturer>();
        manufacturerList.setSelectionModel(selectionModel);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                getUiHandlers().onDetail(selectionModel.getSelectedObject());
            }
        });
    }

    @Override
    public void displayManufacturers(List<Manufacturer> manufacturers) {
        manufacturerDataProvider.getList().clear();
        manufacturerDataProvider.getList().addAll(manufacturers);
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturerDataProvider.getList().add(manufacturer);
    }

    @Override
    public void removeManufacturer(Manufacturer manufacturer) {
        manufacturerDataProvider.getList().remove(manufacturer);
    }

    @Override
    public void replaceManufacturer(Manufacturer oldManufacturer, Manufacturer newManufacturer) {
        List<Manufacturer> manufacturers = manufacturerDataProvider.getList();
        int index = manufacturers.indexOf(oldManufacturer);

        manufacturers.add(index, newManufacturer);
        manufacturers.remove(index + 1);
    }
}
