package jun.handler;

import jun.core.Request;
import jun.core.Response;

import jun.handler.AbstractHandler;
import jun.handler.MiddlewareHandler;
import jun.handler.Handler;

public abstract class AbstractMiddlewareHandler extends AbstractHandler implements MiddlewareHandler {
    public Handler handler;

    public AbstractMiddlewareHandler(final Handler handler) {
        this.handler = handler;
    }

    public void setHandler(final Handler handler) {
        this.handler = handler;
    }
}