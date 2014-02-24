package jun.examples.helloworld;

import static jun.helpers.ResponseHelper.response;
import static jun.helpers.MiddlewareHelper.combine;

import jun.middleware.QueryParamsMiddleware;
import jun.handlers.Handler;

import jun.adapter.jetty.JettyAdapter;


class Main {
    static class HelloWorldHandler implements Handler {
        public Map handle(final Map request) {
            return response("<strong>Hóñá ääåéëþüúí</strong>", 200, "text/html");
        }
    }

    public static void main(String[] args) {
        def handler = combine(new HelloWorldHandler(),
                              new QueryParamsMiddleware());

        def server = new JettyAdapter(handler, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
