package jun.examples.helloapp;

import jun.Request;
import jun.controller.Controller;


class ExampleController extends Controller {
    public String home(final Request request) {
        return "Home";
    }

    public String fooList(final Request request) {
        return "Foo List";
    }

    public String fooDetail(final Request request) {
        return "Foo Detail";
    }
}
