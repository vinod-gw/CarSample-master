package com.arcbees.carsample.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

@GinModules({DispatchAsyncModule.class, MobileModule.class})
public interface MobileGinjector extends ClientGinjector {
}
