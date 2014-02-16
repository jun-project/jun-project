package jun.examples.helloworld;

import static jun.helpers.MiddlewareHelper.combine;

import jun.handler.middleware.QueryParamsHandler;
import jun.handler.AbstractHandler;

import jun.adapter.jetty.JettyAdapter;
import jun.core.Response;

class Main {
    static class HelloWorldHandler extends AbstractHandler {
        def handle(request) {
            return new Response("<strong>Hóñá ääåéëþüúí</strong>", "text/html");
        }
    }

    public static void main(String[] args) {
        def handler = combine(new HelloWorldHandler(),
                              new QueryParamsHandler());

        def server = new JettyAdapter(handler, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
