package jun.controller;

import static jun.helpers.ResponseHelper.response;

import jun.handler.AbstractHandler

abstract class Controller extends AbstractHandler {
    def contentType = "text/html";

    public Map handle(final Map request) {
        final Map match = request.match;
        def responseCandidate = this."${match.action}"(request);

        if (responseCandidate instanceof String) {
            return response(responseCandidate, 200, contentType);
        } else if (responseCandidate instanceof Map) {
            return responseCandidate;
        } else {
            throw RuntimeException("Temporary not supported response");
        }
    }
}