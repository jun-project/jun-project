package jun.handler;

import java.nio.charset.Charset;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handler.middleware.AbstractMiddlewareHandler;

import jun.Request;
import jun.Response;

class CookiesHandler extends AbstractMiddlewareHandler {
    public Response handle(final Request request) {
        return this.handler.handle(request);
    }

    def makeCookiesMap(servletRequest) {
        def cookies = servletRequest.getCookies();
        def cookiesMap = cookies.collectEntries { ["s": 1] }

        return cookiesMap;
    }
}