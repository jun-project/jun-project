package yuki.handler;

import yuki.core.Request;
import yuki.core.Response;

import yuki.handler.AbstractHandler;
import yuki.handler.Handler;

public abstract class AbstractMiddlewareHandler extends AbstractHandler {
    final Handler handler;

    public AbstractMiddlewareHandler(final Handler handler) {
        this.handler = handler;
    }
}