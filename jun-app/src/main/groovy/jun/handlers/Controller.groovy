package jun.handlers;

import com.google.common.collect.ImmutableMap;

import static jun.helpers.ResponseHelper.response;
import jun.handlers.Handler

abstract class Controller implements Handler {
    def contentType = "text/html";

    public Map handle(final Map request) {
        final Map match = request.match;
        def responseCandidate = this."${match.action}"(request);

        if (responseCandidate instanceof String) {
            return response(responseCandidate, 200, contentType);
        } else if (responseCandidate instanceof Map) {
            return ImmutableMap.copyOf(responseCandidate);
        } else {
            throw RuntimeException("Temporary not supported response");
        }
    }
}