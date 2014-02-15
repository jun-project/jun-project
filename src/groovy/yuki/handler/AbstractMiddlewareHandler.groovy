package yuki.handler;

import yuki.core.Request;
import yuki.core.Response;

import yuki.handler.AbstractHandler;
import yuki.handler.MiddlewareHandler;
import yuki.handler.Handler;

public abstract class AbstractMiddlewareHandler extends AbstractHandler implements MiddlewareHandler {
    public Handler handler;

    public AbstractMiddlewareHandler(final Handler handler) {
        this.handler = handler;
    }

    public void setHandler(final Handler handler) {
        this.handler = handler;
    }
}