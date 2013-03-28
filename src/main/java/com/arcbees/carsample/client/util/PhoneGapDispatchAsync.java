package com.arcbees.carsample.client.util;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.rpc.RpcRequestBuilder;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.gwtplatform.dispatch.client.DefaultDispatchAsync;
import com.gwtplatform.dispatch.client.ExceptionHandler;
import com.gwtplatform.dispatch.client.actionhandler.ClientActionHandlerRegistry;
import com.gwtplatform.dispatch.shared.SecurityCookieAccessor;

import javax.inject.Inject;

public class PhoneGapDispatchAsync extends DefaultDispatchAsync {
    private String REMOTE_SERVER_URL = "http://demos.arcbees.com/carsample-v4/";

    @Inject
    public PhoneGapDispatchAsync(ExceptionHandler exceptionHandler,
                                 SecurityCookieAccessor securityCookieAccessor,
                                 ClientActionHandlerRegistry registry) {
        super(exceptionHandler, securityCookieAccessor, registry);
    }

    @Override
    protected void prepareService(ServiceDefTarget service, final String moduleUrl, String relativeServiceUrl) {
        service.setServiceEntryPoint(REMOTE_SERVER_URL  + relativeServiceUrl);
        service.setRpcRequestBuilder(new RpcRequestBuilder() {
            @Override
            protected void doFinish(RequestBuilder requestBuilder) {
                super.doFinish(requestBuilder); 
                requestBuilder.setHeader(MODULE_BASE_HEADER, REMOTE_SERVER_URL);
            }
        });
    }
}
