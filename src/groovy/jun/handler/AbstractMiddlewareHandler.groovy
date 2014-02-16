package jun.handler;

import jun.handler.AbstractHandler;
import jun.handler.MiddlewareHandler;
import jun.handler.Handler;

abstract class AbstractMiddlewareHandler extends AbstractHandler implements MiddlewareHandler {
    Handler handler;

    def AbstractMiddlewareHandler(Handler handler) {
        this.handler = handler;
    }

    void setHandler(Handler handler) {
        this.handler = handler;
    }
}