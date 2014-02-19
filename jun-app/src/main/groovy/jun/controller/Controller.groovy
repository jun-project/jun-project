package jun.controller;

import jun.handler.AbstractHandler

import jun.Request;
import jun.Response;

abstract class Controller extends AbstractHandler {
    def contentType = "text/html";

    public Response handle(final Request request) {
        def match = request.match;
        def responseCandidate = this."${match.action}"(request);

        if (responseCandidate instanceof String) {
            return new Response(responseCandidate, 200, contentType);
        } else if (responseCandidate instanceof Response) {
            return responseCandidate;
        } else {
            throw RuntimeException("Temporary not supported response");
        }
    }
}