package yuki.examples.helloworld;

import yuki.handler.AbstractHandler;
import yuki.adapter.jetty.JettyAdapter;

import yuki.core.Request;
import yuki.core.Response;


public class Main {

    public static class HelloWorldHandler extends AbstractHandler {
        public Response handle(final Request request) {
            return new Response("Hello World", 200);
        }
    }

    public static void main(String[] args) {
        final JettyAdapter server = new JettyAdapter(new HelloWorldHandler(), [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
