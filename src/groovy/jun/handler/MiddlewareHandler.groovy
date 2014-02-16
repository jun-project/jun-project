package jun.handler;

import jun.core.Request;
import jun.core.Response;
import jun.handler.Handler;

public interface MiddlewareHandler {
    public Response handle(final Request request);
    public void setHandler(final Handler handler);
}