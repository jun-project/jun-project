package jun.handler.middleware;

import java.nio.charset.Charset;
import groovy.transform.InheritConstructors;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handler.middleware.AbstractMiddlewareHandler;


class QueryParamsHandler extends AbstractMiddlewareHandler {
    def handle(request) {
        if (request.queryString) {
            def rawParams = URLEncodedUtils.parse(request.queryString, Charset.forName("UTF-8"));
            def params = rawParams.collectEntries { [(it.getName()):(it.getValue())]}
            request.queryParams = params;
        } else {
            request.queryParams = [:];
        }

        return this.handler.handle(request);
    }
}