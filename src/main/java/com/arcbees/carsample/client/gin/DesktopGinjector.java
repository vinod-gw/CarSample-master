package com.arcbees.carsample.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

@GinModules({DispatchAsyncModule.class, DesktopModule.class})
public interface DesktopGinjector extends ClientGinjector {
}
