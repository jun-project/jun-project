package jun.examples.helloapp;

import jun.core.Response;

class ExampleController extends jun.controller.Controller {
    def home(request) {
        return "Home"
    }

    def fooList(request) {
        return "Foo List";
    }

    def fooDetail(request) {
        return "Foo Detail";
    }
}
