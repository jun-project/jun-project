package jun.handler.middleware;

import java.nio.charset.Charset;
import groovy.transform.InheritConstructors;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handler.middleware.AbstractMiddlewareHandler;

import jun.Request;
import jun.Response;


class HeadersHandler extends AbstractMiddlewareHandler {
    public Response handle(final Request request) {
        def servletRequest = request.get("servletRequest");
        def headers = this.makeHeadersMap(servletRequest);

        return this.handler.handle(request.assoc("headers", headers));
    }

    def makeHeadersMap(servletRequest) {
        def headerNames = servletRequest.getHeaderNames();
        def headers = [:];

        headerNames.each { name ->
            headers[name] = servletRequest.getHeader(name);
        }

        return headers;
    }
}
