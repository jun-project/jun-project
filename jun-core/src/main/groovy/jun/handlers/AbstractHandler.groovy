package jun.handlers;

import jun.handlers.Handler;

abstract class AbstractHandler implements Handler {
    abstract Map handle(final Map request);
}