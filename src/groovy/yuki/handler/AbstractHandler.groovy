package yuki.handler;

import yuki.core.Request;
import yuki.core.Response;
import yuki.handler.Handler;

public abstract class AbstractHandler implements Handler {
    public abstract Response handle(final Request request);
}