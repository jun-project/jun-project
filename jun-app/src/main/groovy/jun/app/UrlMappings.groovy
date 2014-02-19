package jun.app;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import jun.handler.Handler;
import jun.handler.AbstractHandler;

import jun.Request;
import jun.Response;

class UrlMappings extends AbstractHandler {
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

    public Map matchRequest(final Request request) {
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

    public Response handleMatchedRequest(final Map match, final Request request) {
        Handler handler = this.resolveControllerHandler(match.controller);
        return handler.handle(request.assoc("match", match));
    }

    public Response handleUnmatchedRequest(final Request request) {
        return new Response("No url matched", 500, "text/plain");
    }

    public Response handle(final Request request) {
        final Map match = this.matchRequest(request);
        if (match) {
            return this.handleMatchedRequest(match, request);
        } else {
            return this.handleUnmatchedRequest(request);
        }
    }
}