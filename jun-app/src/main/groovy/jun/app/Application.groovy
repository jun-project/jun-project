package jun.app;

import static jun.helpers.MiddlewareHelper.combine;

import jun.handlers.middleware.QueryParamsHandler;
import jun.handlers.AbstractHandler;
import jun.handlers.Handler;


class Application extends AbstractHandler {
    final Handler handler;

    public Application(final Handler handler) {
        this.handler = combine(handler,
                               new QueryParamsHandler());
    }

    public Map handle(final Map request) {
        return this.handler.handle(request);
    }
}