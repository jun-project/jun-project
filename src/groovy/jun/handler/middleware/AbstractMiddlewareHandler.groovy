package jun.handler.middleware;

import jun.handler.AbstractHandler;
import jun.handler.MiddlewareHandler;
import jun.handler.Handler;


abstract class AbstractMiddlewareHandler extends AbstractHandler implements MiddlewareHandler {
    Handler handler;

    def AbstractMiddlewareHandler() {}

    def AbstractMiddlewareHandler(Handler handler) {
        this.setHandler(handler);
    }

    void setHandler(Handler handler) {
        this.handler = handler;
    }
}