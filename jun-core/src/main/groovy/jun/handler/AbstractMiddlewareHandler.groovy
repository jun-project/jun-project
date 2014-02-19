package jun.handler;

import jun.handler.AbstractHandler;
import jun.handler.MiddlewareHandler;
import jun.handler.Handler;


abstract class AbstractMiddlewareHandler extends AbstractHandler implements MiddlewareHandler {
    public Handler handler;

    public AbstractMiddlewareHandler() {}

    public AbstractMiddlewareHandler(final Handler handler) {
        this.setHandler(handler);
    }

    void setHandler(final Handler handler) {
        this.handler = handler;
    }
}