package jun.examples.helloworld;

import static jun.helpers.MiddlewareHelper.combine;

import jun.handler.middleware.QueryParamsHandler;
import jun.handler.AbstractHandler;

import jun.adapter.jetty.JettyAdapter;


class Main {
    static class HelloWorldHandler extends AbstractHandler {
        public Map handle(final Map request) {
            return [body: "<strong>Hóñá ääåéëþüúí</strong>",
                    status: 200,
                    contentType: "text/html",
                    headers: ["X-KK": "2"]];
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
