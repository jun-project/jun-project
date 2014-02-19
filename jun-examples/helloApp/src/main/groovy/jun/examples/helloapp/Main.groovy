package jun.examples.helloapp;

import jun.app.Application;
import jun.app.UrlMappings;

import jun.adapter.jetty.JettyAdapter;

class Main {
    static def mappings = [
        [name: "home", path: "/", controller: "jun.examples.helloapp.ExampleController", action: "home"],
        [name: "foo-list", path: "/foo", controller: "jun.examples.helloapp.ExampleController", action: "fooList"],
        [name: "foo-detail", path: "/foo/:id", controller: "jun.examples.helloapp.ExampleController", action: "fooDetail"]
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
