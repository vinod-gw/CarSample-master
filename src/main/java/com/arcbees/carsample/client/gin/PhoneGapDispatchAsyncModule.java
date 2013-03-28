package com.arcbees.carsample.client.gin;

import com.arcbees.carsample.client.util.PhoneGapDispatchAsync;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.gwtplatform.dispatch.client.DefaultExceptionHandler;
import com.gwtplatform.dispatch.client.DefaultSecurityCookieAccessor;
import com.gwtplatform.dispatch.client.ExceptionHandler;
import com.gwtplatform.dispatch.client.actionhandler.ClientActionHandlerRegistry;
import com.gwtplatform.dispatch.client.actionhandler.DefaultClientActionHandlerRegistry;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.dispatch.shared.SecurityCookieAccessor;

public class PhoneGapDispatchAsyncModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(ExceptionHandler.class).to(DefaultExceptionHandler.class);
        bind(SecurityCookieAccessor.class).to(DefaultSecurityCookieAccessor.class);
        bind(ClientActionHandlerRegistry.class).to(DefaultClientActionHandlerRegistry.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    protected DispatchAsync provideDispatchAsync(
            ExceptionHandler exceptionHandler,
            SecurityCookieAccessor secureSessionAccessor,
            ClientActionHandlerRegistry registry) {
        return new PhoneGapDispatchAsync(exceptionHandler, secureSessionAccessor, registry);
    }

}
