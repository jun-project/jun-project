package jun.handler;

import jun.handler.Handler;

abstract class AbstractHandler implements Handler {
    abstract Map handle(final Map request);
}