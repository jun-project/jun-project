package jun.app;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import jun.handler.Handler;
import jun.handler.AbstractHandler;

import jun.core.Response;

class UrlMappings extends AbstractHandler {
    def urls;
    def urlsByName;

    def UrlMappings(mappings) {
        this.urls = mappings.collect { url ->
            def urlmap = url.clone();
            urlmap.pattern = Pattern.compile(url.path.replaceAll(/:[^\\/]+/, '[^\\/]+'))
            return urlmap;
        }

        this.urlsByName = this.urls.collectEntries { url -> [(url.name): url] }

        println "======== URLS =========";
        println mappings;
        println "---";
        println this.urls;
    }

    def matchRequest(request) {
        def match = this.urls.find { urlmap ->
            def matcher = urlmap.pattern.matcher(request.path);
            return matcher.matches();
        }

        return match;
    }

    // TODO: pending optimize controller initialization
    // TODO: pending optimize new handler initialization
    def resolveControllerHandler(controllerPath, actionName) {
         def klass = Class.forName(controllerPath)
         def klassConstructor = klass.getConstructor();

         return new Handler() {
             def handle(request) {
                 def instance = klassConstructor.newInstance();
                 return instance."$actionName"(request);
             }
         }
    }

    def handleMatchedRequest(match, request) {
        // Attach current match to request
        // request.match = march
        println "MATCH: ${match}"
        println "REQUEST: ${request}"

        // Resolve handler
        def handler = this.resolveControllerHandler(match.controller, match.action);

        return handler.handle(request);
    }

    def handleUnmatchedRequest(request) {
        return new Response("No url matched", 500, "text/plain");
    }

    def handle(request) {
        def match = this.matchRequest(request);
        if (match) {
            return this.handleMatchedRequest(match, request);
        } else {
            return this.handleUnmatchedRequest(request);
        }
    }
}