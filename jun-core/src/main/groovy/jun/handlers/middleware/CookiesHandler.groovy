package jun.handlers.middleware;

import groovy.transform.CompileStatic;

import java.nio.charset.Charset;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.client.utils.URLEncodedUtils;

import jun.handlers.AbstractMiddlewareHandler;

// NOTE: explicit casting is mandatory because we using
// CompileStatic transformation and request and response
// are dinamic objects.

class CookiesHandler extends AbstractMiddlewareHandler {
    @CompileStatic
    public Map handle(final Map request) {
        //final Map cookies = this.makeCookiesMap(request);
        final Map cookies = [:];
        final Map response = this.handler.handle(request.plus([cookies:cookies]).asImmutable());

        if (response.cookies) {
            //final Map responseCookies = response.cookies

            // keys.each { final Object _key ->
            //     final String key = (String) _key;
            //     final Map value = (Map) responseCookies.get(key);
            // }
            // Response coming with cookies
        }

        return response;
    }

    // @CompileStatic
    // public Map makeCookiesMap(final Request request) {
    //     final Cookie[] cookies = servletRequest.getCookies();

    //     if (cookies == null) {
    //         return new ImmutableHashMap();
    //     }

    //     final Map cookiesMap = cookies.collectEntries { Cookie cookie ->
    //         final Map cookieMap = [value: cookie.getValue()];

    //         final String domain = cookie.getDomain();
    //         if (domain != null) { cookieMap.domain = domain; }

    //         final String path = cookie.getPath();
    //         if (path != null) { cookieMap.path = path; }

    //         // TODO
    //         //httpOnly: cookie.isHttpOnly(),
    //         // secure: cookie.getSecure()];

    //         return [(cookie.getName()): cookieMap.asImmutable()];
    //     }

    //     return new ImmutableHashMap(cookiesMap);
    // }

    // @CompileStatic
    // public Cookie makeCoookie(final String name, final ImmutableHashMap data) {
    //     final Cookie cookie = new Cookie(name, (String) data.get("value"));

    //     if (data.hasKey("domain")) {
    //         String domain = (String) data.get("domain");
    //         if (domain != null) {
    //             cookie.setDomain(domain);
    //         }
    //     }
    // }
}