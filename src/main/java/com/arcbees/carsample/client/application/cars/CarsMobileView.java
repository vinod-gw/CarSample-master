package com.arcbees.carsample.client.application.cars;

import com.arcbees.carsample.client.application.cars.CarsPresenter.MyView;
import com.arcbees.carsample.client.application.cars.renderer.CarCell;
import com.arcbees.carsample.client.application.ui.ShowMorePagerPanel;
import com.arcbees.carsample.client.resource.MobileDataListStyle;
import com.arcbees.carsample.shared.domain.Car;
import com.arcbees.core.client.mvp.ViewWithUiHandlers;
import com.arcbees.core.client.mvp.uihandlers.UiHandlersStrategy;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import javax.inject.Inject;
import java.util.List;

public class CarsMobileView extends ViewWithUiHandlers<CarsUiHandlers> implements MyView {
    public interface Binder extends UiBinder<Widget, CarsMobileView> {
    }

    private static final int PAGE_SIZE = 20;

    @UiField(provided = true)
    ShowMorePagerPanel pagerPanel;

    private CellList<Car> carList;
    private AsyncDataProvider<Car> asyncDataProvider;
    private SingleSelectionModel<Car> selectionModel;

    @Inject
    public CarsMobileView(final Binder uiBinder,
            final CarCell carCell, final MobileDataListStyle mobileDataListStyle,
            final UiHandlersStrategy<CarsUiHandlers> uiHandlersStrategy) {
        super(uiHandlersStrategy);

        pagerPanel = new ShowMorePagerPanel(PAGE_SIZE);
        carList = new CellList<Car>(carCell, mobileDataListStyle);
        selectionModel = new SingleSelectionModel<Car>();

        initWidget(uiBinder.createAndBindUi(this));

        pagerPanel.setDisplay(carList);
        carList.setSelectionModel(selectionModel);

        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                getUiHandlers().onEdit(selectionModel.getSelectedObject());
            }
        });
    }

    @Override
    public void initDataProvider() {
        asyncDataProvider = new AsyncDataProvider<Car>() {
            @Override
            protected void onRangeChanged(HasData<Car> display) {
                Range range = display.getVisibleRange();
                getUiHandlers().fetchData(range.getStart(), range.getLength());
            }
        };

        asyncDataProvider.addDataDisplay(carList);
    }

    @Override
    public HasData<Car> getCarDisplay() {
        return carList;
    }

    @Override
    public void setCarsCount(Integer result) {
        asyncDataProvider.updateRowCount(result, true);
    }

    @Override
    public void displayCars(int offset, List<Car> cars) {
        asyncDataProvider.updateRowData(offset, cars);
    }
}

