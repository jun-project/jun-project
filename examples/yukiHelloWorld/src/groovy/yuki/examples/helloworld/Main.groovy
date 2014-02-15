package yuki.examples.helloworld;

import static yuki.helpers.MiddlewareHelper.combine;

import yuki.handler.QueryParamsHandler;
import yuki.handler.AbstractHandler;
import yuki.handler.Handler;

import yuki.adapter.jetty.JettyAdapter;
import yuki.core.Request;
import yuki.core.Response;


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
