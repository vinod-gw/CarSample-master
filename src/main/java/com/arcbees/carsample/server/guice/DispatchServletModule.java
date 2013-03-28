package com.arcbees.carsample.server.guice;

import com.arcbees.carsample.server.guice.rpc.DispatchServiceImplOverrideSOP;
import com.arcbees.carsample.server.servlet.ExampleServlet;
import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {
    @Override
    public void configureServlets() {
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImplOverrideSOP.class);
        serve("/exampleServlet").with(ExampleServlet.class);
    }
}
