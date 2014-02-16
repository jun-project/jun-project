package jun.handler;

import java.nio.charset.Charset;
import groovy.transform.InheritConstructors;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handler.middleware.AbstractMiddlewareHandler;

class CookiesHandler extends AbstractMiddlewareHandler {
    def handle(request) {
        return this.handler.handle(request);
    }
}