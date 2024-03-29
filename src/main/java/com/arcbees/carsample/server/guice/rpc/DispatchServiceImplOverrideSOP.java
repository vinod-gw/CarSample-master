package com.arcbees.carsample.server.guice.rpc;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.Dispatch;
import com.gwtplatform.dispatch.server.RequestProvider;
import com.gwtplatform.dispatch.shared.SecurityCookie;

import javax.inject.Singleton;
import java.util.logging.Logger;

/**
 * This is the server-side implementation of the
 * {@link com.gwtplatform.dispatch.shared.DispatchService}, for which the
 * client-side async service is
 * {@link com.gwtplatform.dispatch.shared.DispatchServiceAsync}.
 * <p />
 * This class is closely related to {@link DispatchImpl}, in theory the latter
 * wouldn't be needed, but we use it to workaround a GWT limitation described in
 * {@link com.gwtplatform.dispatch.shared.DispatchAsync}.
 * 
 * @see com.gwtplatform.dispatch.shared.DispatchAsync
 * @see com.gwtplatform.dispatch.server.Dispatch
 * @see com.gwtplatform.dispatch.server.guice.DispatchImpl
 * @see com.gwtplatform.dispatch.shared.DispatchService
 * @see com.gwtplatform.dispatch.shared.DispatchServiceAsync
 * @see com.gwtplatform.dispatch.server.guice.DispatchServiceImpl
 * 
 * @author Christian Goudreau
 * @author David Peterson
 * @author Peter Simun
 */
@Singleton
public class DispatchServiceImplOverrideSOP extends AbstractDispatchServiceImpl {
    private static final long serialVersionUID = 136176741488585959L;

    @Inject(optional = true)
    @SecurityCookie
    protected String securityCookieName;

    @Inject
    public DispatchServiceImplOverrideSOP(final Logger logger, final Dispatch dispatch, 
            RequestProvider requestProvider) {
        super(logger, dispatch, requestProvider);
    }

    @Override
    public String getSecurityCookieName() {
        return securityCookieName;
    }
}
