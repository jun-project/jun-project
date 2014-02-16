package jun.handler;

import jun.core.Request;
import jun.core.Response;
import jun.handler.Handler;

public interface MiddlewareHandler<A extends Request,B extends Response> {
    public B handle(final A request);
    public void setHandler(final Handler handler);
}