package com.arcbees.carsample.client.gin;

import com.google.gwt.inject.client.GinModules;

@GinModules({PhoneGapDispatchAsyncModule.class, MobileModule.class})
public interface PhoneGapGinjector extends ClientGinjector {
}
