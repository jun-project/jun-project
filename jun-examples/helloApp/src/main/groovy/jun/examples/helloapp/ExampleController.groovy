package jun.examples.helloapp;

import jun.controller.Controller;


class ExampleController extends Controller {
    public String home(final Map request) {
        return "Home";
    }

    public String fooList(final Map request) {
        return "Foo List";
    }

    public String fooDetail(final Map request) {
        return "Foo Detail";
    }
}
