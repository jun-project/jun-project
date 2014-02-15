package yuki.middleware;

import yuki.handler.Handler;
import yuki.handler.MiddlewareHandler;

public class Middleware {
    public static Handler combine(final Handler handler, MiddlewareHandler... middlewares) {
        Handler finalHandler = handler;

        middlewares.each { mw ->
            mw.setHandler(finalHandler);
            finalHandler = mw;
        }

        return finalHandler;
    }
}