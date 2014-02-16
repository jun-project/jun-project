package jun.handler;

import jun.core.Request;
import jun.core.Response;
import jun.handler.Handler;

abstract class AbstractHandler implements Handler {
    abstract handle(request);
}