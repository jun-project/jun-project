package yuki.examples.helloworld;

import yuki.handler.AbstractHandler;
import yuki.handler.Handler;
import yuki.adapter.jetty.JettyAdapter;

import yuki.middleware.QueryParamsHandler;

import yuki.core.Request;
import yuki.core.Response;


public class Main {
    public static class HelloWorldHandler extends AbstractHandler {
        public Response handle(final Request request) {
            println "==============================";
            println "Request.path ${request.path}"
            println "Request.queryString ${request.queryString} ${request.queryString.class}"
            println "Request.queryParams ${request.queryParams} ${request.queryParams.getClass()}"
            return new Response("<strong>Hóñá ääåéëþüúí</strong>", "text/html");
        }
    }

    public static void main(String[] args) {
        final Handler helloWorldHandler = new HelloWorldHandler();
        final Handler mainHandler = new QueryParamsHandler(helloWorldHandler);

        final JettyAdapter server = new JettyAdapter(mainHandler, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
