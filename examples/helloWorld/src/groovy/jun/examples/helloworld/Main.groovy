package jun.examples.helloworld;

import static jun.helpers.MiddlewareHelper.combine;

import jun.handler.QueryParamsHandler;
import jun.handler.AbstractHandler;
import jun.handler.Handler;

import jun.adapter.jetty.JettyAdapter;
import jun.core.Request;
import jun.core.Response;


public class Main {
    public static class HelloWorldHandler extends AbstractHandler {
        public Response handle(final Request request) {
            println request.headers
            return new Response("<strong>Hóñá ääåéëþüúí</strong>", "text/html");
        }
    }

    public static void main(String[] args) {
        final Handler handler = combine(new HelloWorldHandler(),
                                        new QueryParamsHandler());

        final JettyAdapter server = new JettyAdapter(handler, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
