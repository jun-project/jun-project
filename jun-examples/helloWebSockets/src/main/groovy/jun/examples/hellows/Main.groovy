package jun.examples.hellows;

import static jun.helpers.MiddlewareHelper.combine

import jun.handler.middleware.QueryParamsHandler
import jun.handler.AbstractHandler
import org.eclipse.jetty.websocket.api.Session

import jun.app.Application;
import jun.app.UrlMappings;
import jun.adapter.jetty.JettyAdapter
import jun.adapter.jetty.handlers.websocket.WSHandler


class Main {
    static class MyWsHandler extends WSHandler {
        public void onConnect(Session session) {
            println "CONNECT"
        }
        public void onError(Throwable exception) {
            println "ERROR"
        }
        public void onMessage(String message) {
            println "MSG ${message}"
        }
        public void onClose(String reason, Integer statusCode) {
            println "CLOSE ${reason} ${statusCode}"
        }
    }

    static class MyHandler extends AbstractHandler {
        public Map handle(final Map request) {
            return [body: "Hello World",
                    status: 200]
        }
    }
    static def mappings = [
        [name: "home", path: "/", controller: MyHandler, action: "home"],
        [name: "ws", path: "/ws", controller: MyWsHandler],
    ];

    public static void main(String[] args) {
        def urlMappings = new UrlMappings(mappings);
        def app = new Application(urlMappings);

        def server = new JettyAdapter(app, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
