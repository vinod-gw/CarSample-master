package com.arcbees.carsample.client;

import com.arcbees.carsample.client.gin.ClientGinjector;
import com.arcbees.carsample.client.gin.GinjectorProvider;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class CarSample implements EntryPoint {
    // This will get the desktop or mobile ginjector
    private final ClientGinjector ginjector = ((GinjectorProvider) GWT.create(GinjectorProvider.class)).get();

    @Override
    public void onModuleLoad() {
        // This is required for Gwt-Platform proxy's generator
        DelayedBindRegistry.bind(ginjector);

        // This CSS style overrides the Standard CSS style that is included in the CarSample.gwt.xml        
        ginjector.getResources().styles().ensureInjected();

        // Start the application
        ginjector.getBootStrapper().init();
    }
}
