package com.arcbees.carsample.server.guice.rpc;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.gwtplatform.dispatch.server.AbstractDispatchImpl;
import com.gwtplatform.dispatch.server.Dispatch;
import com.gwtplatform.dispatch.server.RequestProvider;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.DispatchService;
import com.gwtplatform.dispatch.shared.Result;
import com.gwtplatform.dispatch.shared.ServiceException;

/**
 * This is the server-side implementation of the {@link DispatchService}, for
 * which the client-side async service is
 * {@link com.gwtplatform.dispatch.shared.DispatchServiceAsync}.
 * <p />
 * This class is closely related to {@link AbstractDispatchImpl}, in theory the
 * latter wouldn't be needed, but we use it to workaround a GWT limitation
 * described in {@link com.gwtplatform.dispatch.shared.DispatchAsync}.
 * 
 * @see com.gwtplatform.dispatch.shared.DispatchAsync
 * @see com.gwtplatform.dispatch.server.Dispatch
 * @see com.gwtplatform.dispatch.server.guice.DispatchImpl
 * @see com.gwtplatform.dispatch.shared.DispatchService
 * @see com.gwtplatform.dispatch.shared.DispatchServiceAsync
 * @see com.gwtplatform.dispatch.server.guice.DispatchServiceImpl
 * @author Christian Goudreau
 * @author David Peterson
 */
public abstract class AbstractDispatchServiceImpl extends RemoteServiceServlet implements DispatchService {
    private static final String noSecurityCookieMessage = "You have to define a security cookie in order to use secured actions. See com.gwtplatform.dispatch.shared.SecurityCookie for details.";

    private static final long serialVersionUID = -4753225025940949024L;
    private static final String xsrfAttackMessage = "Cookie provided by RPC doesn't match request cookie, aborting action, possible XSRF attack. (Maybe you forgot to set the security cookie?)";

    protected final Dispatch dispatch;
    protected final Logger logger;

    protected RequestProvider requestProvider;

    protected AbstractDispatchServiceImpl(final Logger logger, final Dispatch dispatch, RequestProvider requestProvider) {
        this.logger = logger;
        this.dispatch = dispatch;
        this.requestProvider = requestProvider;
    }

    public String getSecurityCookieName() {
        return null;
    }

    @Override
    public Result execute(String cookieSentByRPC, Action<?> action) throws ActionException, ServiceException {

        if (action.isSecured() && !cookieMatch(cookieSentByRPC)) {
            String message = xsrfAttackMessage + " While executing action: " + action.getClass().getName();

            logger.severe(message);
            throw new ServiceException(message);
        }

        try {
            return dispatch.execute(action);
        } catch (ActionException e) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING,
                        "Action exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e);
            }
            throw e;
        } catch (ServiceException e) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING,
                        "Service exception while executing " + action.getClass().getName() + ": " + e.getMessage(), e);
            }
            throw e;
        } catch (RuntimeException e) {
            if (logger.isLoggable(Level.WARNING)) {
                logger.log(Level.WARNING, "Unexpected exception while executing " + action.getClass().getName() + ": "
                        + e.getMessage(), e);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void undo(String cookieSentByRPC, Action<Result> action, Result result) throws ActionException,
            ServiceException {

        if (action.isSecured() && !cookieMatch(cookieSentByRPC)) {
            String message = xsrfAttackMessage + " While undoing action: " + action.getClass().getName();

            logger.severe(message);
            throw new ServiceException(message);
        }

        try {
            dispatch.undo(action, result);
        } catch (ActionException e) {
            logger.warning("Action exception while undoing " + action.getClass().getName() + ": " + e.getMessage());
            throw e;
        } catch (ServiceException e) {
            logger.warning("Service exception while undoing " + action.getClass().getName() + ": " + e.getMessage());
            throw e;
        } catch (RuntimeException e) {
            logger.warning("Unexpected exception while undoing " + action.getClass().getName() + ": " + e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Checks that the cookie in the RPC matches the one in the http request
     * header.
     * 
     * @param cookieSentByRPC
     *            The content of the security cookie sent by RPC.
     * @return {@code true} if the cookies match, {@code false} otherwise.
     * @throws ServiceException
     *             If you forgot to bind a
     *             {@link com.gwtplatform.dispatch.shared.SecurityCookie}.
     */
    private boolean cookieMatch(String cookieSentByRPC) throws ServiceException {

        // Make sure the specified cookie matches the
        HttpServletRequest request = requestProvider.getServletRequest();

        if (getSecurityCookieName() == null) {
            logger.info(noSecurityCookieMessage);
            return false;
        }

        if (cookieSentByRPC == null) {
            logger.info("No cookie sent by client in RPC. (Did you forget to bind the security cookie client-side?" +
            		" Or it could be an attack.)");
            return false;
        }

        // Try to match session tokens to prevent XSRF
        Cookie[] cookies = request.getCookies();
        String cookieInRequest = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(getSecurityCookieName())) {
                    cookieInRequest = cookie.getValue();
                    break;
                }
            }
        }

        if (cookieInRequest == null) {
            logger.info("Cookie \"" + getSecurityCookieName() + "\" not found in HttpServletRequest!");
            return false;
        }

        return cookieInRequest.equals(cookieSentByRPC);
    }
}
