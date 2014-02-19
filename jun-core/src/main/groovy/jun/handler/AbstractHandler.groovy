package jun.handler;

import jun.Request;
import jun.Response;
import jun.handler.Handler;

abstract class AbstractHandler implements Handler {
    abstract Response handle(final Request request);
}