package com.arcbees.carsample.client.gin;

import com.google.gwt.core.client.GWT;

import java.util.logging.Logger;

public class PhoneGapGinjectorProvider implements GinjectorProvider {
    public final static Logger logger = Logger.getLogger(PhoneGapGinjectorProvider.class.getName());

    @Override
    public ClientGinjector get() {
        logger.info("PhoneGapGinjectorProvider get() PhoneGapGinjector was used.");

        return GWT.create(PhoneGapGinjector.class);
    }
}
