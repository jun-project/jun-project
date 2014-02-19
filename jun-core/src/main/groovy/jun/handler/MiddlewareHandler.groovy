package jun.handler;

import jun.Request;
import jun.handler.Handler;

public interface MiddlewareHandler {
    public void setHandler(final Handler handler);
}