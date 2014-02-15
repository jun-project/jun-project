package yuki.handler;

import yuki.core.Request;
import yuki.core.Response;
import yuki.handler.Handler;

public interface MiddlewareHandler {
    public Response handle(final Request request);
    public void setHandler(final Handler handler);
}