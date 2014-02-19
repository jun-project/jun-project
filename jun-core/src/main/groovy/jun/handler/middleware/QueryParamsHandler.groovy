package jun.handler.middleware;

import java.nio.charset.Charset;
import groovy.transform.InheritConstructors;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handler.middleware.AbstractMiddlewareHandler;

import jun.Request;
import jun.Response;

class QueryParamsHandler extends AbstractMiddlewareHandler {
    public Response handle(final Request request) {
        def queryParams = [:];

        if (request.queryString) {
            def rawParams = URLEncodedUtils.parse(request.queryString, Charset.forName("UTF-8"));
            queryParams = rawParams.collectEntries { [(it.getName()):(it.getValue())]}
        }

        return this.handler.handle(request.assoc([queryParams:queryParams]));
    }
}