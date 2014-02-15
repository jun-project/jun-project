package yuki.examples.helloworld;

import yuki.handler.AbstractHandler;
import yuki.adapter.jetty.JettyAdapter;

import yuki.core.Request;
import yuki.core.Response;

import java.io.File;


public class Main {
    public static class HelloWorldHandler extends AbstractHandler {
        public Response handle(final Request request) {
            return new Response("<strong>Hóñá ääåéëþüúí</strong>", "text/html");
        }
    }

    public static void main(String[] args) {
        final JettyAdapter server = new JettyAdapter(new HelloWorldHandler(), [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
