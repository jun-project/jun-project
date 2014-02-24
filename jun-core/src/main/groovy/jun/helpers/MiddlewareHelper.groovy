package jun.helpers;

import jun.handlers.Handler
import jun.middleware.Middleware

public class MiddlewareHelper {
    public static Handler combine(final Handler handler, Middleware... middlewares) {
        Handler finalHandler = handler;

        middlewares.each { mw ->
            finalHandler = mw.wrap(handler);
        }

        return finalHandler;
    }
}