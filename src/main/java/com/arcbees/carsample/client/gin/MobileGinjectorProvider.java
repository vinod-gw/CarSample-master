package com.arcbees.carsample.client.gin;

import com.google.gwt.core.client.GWT;

import java.util.logging.Logger;

public class MobileGinjectorProvider implements GinjectorProvider {
    public final static Logger logger = Logger.getLogger(MobileGinjectorProvider.class.getName());

    @Override
    public ClientGinjector get() {
        logger.info("MobileGinjectorProvider get() MobileGinjector was used.");

        return GWT.create(MobileGinjector.class);
    }
}
