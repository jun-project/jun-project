package jun.handlers.middleware;

import java.nio.charset.Charset;
import groovy.transform.InheritConstructors;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handlers.AbstractMiddlewareHandler;

class QueryParamsHandler extends AbstractMiddlewareHandler {
    public Map handle(final Map request) {
        Map queryParams = [:];

        if (request.queryString) {
            final Map rawParams = URLEncodedUtils.parse(request.queryString, Charset.forName("UTF-8"));
            queryParams = rawParams.collectEntries { [(it.getName()):(it.getValue())]}
        } 

        final Map req = request.plus([queryParams:queryParams]);
        return this.handler.handle(req.asImmutable());
    }
}