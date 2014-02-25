package jun.examples.helloapp;

import static jun.helpers.MiddlewareHelper.combine

import jun.handlers.UrlMappings
import jun.handlers.Controller

import jun.middleware.QueryParamsMiddleware
import jun.adapter.jetty.JettyAdapter


class Main {
    static class ExampleController extends Controller {
        public String home(final Map request) {
            return "Home"
        }

        public String fooList(final Map request) {
            return "Foo List"
        }

        public String fooDetail(final Map request) {
            return "Foo Detail"
        }
    }

    static def mappings = [
        [name: "home", path: "/", controller: ExampleController, action: "home"],
        [name: "foo-list", path: "/foo", controller: ExampleController, action: "fooList"],
        [name: "foo-detail", path: "/foo/:id", controller: ExampleController, action: "fooDetail"]
    ]

    public static void main(String[] args) {
        def handler = combine(new UrlMappings(mappings),
                              new QueryParamsMiddleware())

        def server = new JettyAdapter(handler, [port: 8080]);
        server.start();

        println "Now watching on http://localhost:8080/";
        server.join();
    }
}
