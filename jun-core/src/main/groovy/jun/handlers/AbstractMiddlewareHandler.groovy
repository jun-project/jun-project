package jun.handlers;

import jun.handlers.AbstractHandler;
import jun.handlers.MiddlewareHandler;
import jun.handlers.Handler;


abstract class AbstractMiddlewareHandler implements MiddlewareHandler, Handler {
    public Handler handler;

    public AbstractMiddlewareHandler() {}
    public AbstractMiddlewareHandler(final Handler handler) {
        this.setHandler(handler);
    }

    void setHandler(final Handler handler) {
        this.handler = handler;
    }

    abstract public Map handle(final Map request);
}

