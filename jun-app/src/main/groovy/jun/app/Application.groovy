package jun.app;

import static jun.helpers.MiddlewareHelper.combine;

import jun.handler.middleware.QueryParamsHandler;
import jun.handler.middleware.HeadersHandler;
import jun.handler.AbstractHandler;
import jun.handler.Handler;

import jun.Request;
import jun.Response;


class Application extends AbstractHandler {
    final Handler handler;

    public Application(final Handler handler) {
        this.handler = combine(handler,
                               new QueryParamsHandler(),
                               new HeadersHandler());
    }

    public Response handle(final Request request) {
        return this.handler.handle(request);
    }
}