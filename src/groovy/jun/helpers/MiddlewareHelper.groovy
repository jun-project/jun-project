package jun.helpers;

import jun.handler.Handler;
import jun.handler.MiddlewareHandler;

public class MiddlewareHelper {
    public static Handler combine(final Handler handler, MiddlewareHandler... middlewares) {
        Handler finalHandler = handler;

        middlewares.each { mw ->
            mw.setHandler(finalHandler);
            finalHandler = mw;
        }

        return finalHandler;
    }
}