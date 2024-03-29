package com.arcbees.carsample.client.application;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.arcbees.carsample.client.application.cars.CarsPresenter;
import com.arcbees.carsample.client.application.cars.car.CarPresenter;
import com.arcbees.carsample.client.application.cars.car.CarProxyFactory;
import com.arcbees.carsample.client.application.glace.GlaceLoginPresenter;
import com.arcbees.carsample.client.application.testutils.PresenterTestModule;
import com.arcbees.carsample.client.application.testutils.PresenterWidgetTestBase;
import com.arcbees.carsample.client.place.NameTokens;
import com.arcbees.carsample.shared.dispatch.DeleteCarAction;
import com.arcbees.carsample.shared.dispatch.GetCarsAction;
import com.arcbees.carsample.shared.dispatch.GetResults;
import com.arcbees.carsample.shared.dispatch.NoResults;
import com.arcbees.carsample.shared.domain.Car;
import com.arcbees.carsample.shared.domain.Manufacturer;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;

@RunWith(JukitoRunner.class)
public class CarsPresenterTest extends PresenterWidgetTestBase {
    public static class Module extends PresenterTestModule {
      @Override
      protected void configurePresenterTest() {
          forceMock(CarProxyFactory.class);
      }
    }
    
    @Inject
    CarsPresenter carsPresenter;
    @Inject
    GlaceLoginPresenter glaceLoginPresenter;
    @Inject
    CarsPresenter.MyView view;
    @Inject
    GlaceLoginPresenter.MyView glaceview;
    @Inject
    CarProxyFactory carProxyFactory;
    @Inject
    CarPresenter.MyProxy proxy;
    
    @Test
    public void onEditCar(PlaceManager placeManager) {        
        // Given
        Car car = mock(Car.class);
        when(car.getManufacturer()).thenReturn(mock(Manufacturer.class));
        when(carProxyFactory.create(car, car.getManufacturer().getName() + car.getModel())).thenReturn(proxy);
        when(proxy.getNameToken()).thenReturn("token");
        
        PlaceRequest placeRequest = new PlaceRequest("token");
        
        // When
        carsPresenter.onEdit(car);

        // Then
        verify(placeManager).revealPlace(eq(placeRequest));
    }

    @Test
    public void onCreate(PlaceManager placeManager) {
        // Given
        PlaceRequest placeRequest = new PlaceRequest(NameTokens.newCar);
        
        // When
        carsPresenter.onCreate();
        
        // Then
        verify(placeManager).revealPlace(eq(placeRequest));
    }
    @Test
    public void onLogin(PlaceManager placeManager) {
    	
    	
    	
       
    }

    @Test
    public void onDelete(Car car, 
            HasData<Car> hasCarData, Range range) {
        // Given we have DeleteCarAction 
        dispatcher.given(DeleteCarAction.class).willReturn(new NoResults());
        
        // And GetCarAction for fetching after delete
        GetResults<Car> result = new GetResults<Car>(new ArrayList<Car>());
        dispatcher.given(GetCarsAction.class).willReturn(result);
        
        // And display is setup
        when(view.getCarDisplay()).thenReturn(hasCarData);
        
        // And range is setup
        HasData<Car> display = view.getCarDisplay();
        when(display.getVisibleRange()).thenReturn(range);
        
        // When
        carsPresenter.onDelete(car);
        
        // Then
        verify(view).setCarsCount(-1);
    }
    
    @Test
    public void onFetchData(ArrayList<Car> cars) {
        // Given
        GetResults<Car> result = new GetResults<Car>(new ArrayList<Car>());
        dispatcher.given(GetCarsAction.class).willReturn(result);
        
        // When
        carsPresenter.fetchData(0, 1);
        
        // Then
        verify(view).displayCars(0, cars);
    }
    
    @Test
    public void onFetchDataThreeCars(ArrayList<Car> cars) {
        // Given
        GetResults<Car> result = new GetResults<Car>(cars);
        dispatcher.given(GetCarsAction.class).willReturn(result);
        
        // When
        carsPresenter.fetchData(0, 3);
        
        // Then
        verify(view).displayCars(0, cars);
    }
}
