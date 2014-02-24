package jun.handlers;

import static jun.helpers.ResponseHelper.response;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import jun.handlers.Handler;


class UrlMappings implements Handler {
    final List urls;
    final Map urlsByName;

    public UrlMappings(final List mappings) {
        this.urls = mappings.collect { url ->
            def urlmap = url.clone();
            urlmap.pattern = Pattern.compile(url.path.replaceAll(/:[^\\/]+/, '[^\\/]+'))
            return urlmap;
        }

        this.urlsByName = this.urls.collectEntries { url -> [(url.name): url] }
    }

    public Map matchRequest(final Map request) {
        def match = this.urls.find { urlmap ->
            def matcher = urlmap.pattern.matcher(request.path);
            return matcher.matches();
        }

        return match;
    }

    public Handler resolveControllerHandler(final String controllerPath) {
        def klass = Class.forName(controllerPath);
        def klassConstructor = klass.getConstructor();
        return klassConstructor.newInstance();
    }

    public Handler resolveControllerHandler(final Class controllerClass) {
        return controllerClass.newInstance();
    }

    public Map handleMatchedRequest(final Map match, final Map request) {
        Handler handler = this.resolveControllerHandler(match.controller);
        return handler.handle(request.plus([match:match]).asImmutable());
    }

    public Map handleUnmatchedRequest(final Map request) {
        return response("No url matched", 500, "text/plain");
    }

    public Map handle(final Map request) {
        final Map match = this.matchRequest(request);
        if (match) {
            return this.handleMatchedRequest(match, request);
        } else {
            return this.handleUnmatchedRequest(request);
        }
    }
}