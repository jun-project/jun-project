package jun.handler;

import jun.core.Request;
import jun.core.Response;
import jun.handler.Handler;

public abstract class AbstractHandler implements Handler {
    public abstract Response handle(final Request request);
}