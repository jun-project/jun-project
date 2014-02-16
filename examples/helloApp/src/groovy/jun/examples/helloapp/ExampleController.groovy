package jun.examples.helloapp;

import jun.core.Response;

class ExampleController extends jun.controller.Controller {
    def home(request) {
        return new Response("Home");
    }

    def fooList(request) {
        return new Response("Foo List");
    }

    def fooDetail(request) {
        return new Response("Foo Detail");
    }
}
