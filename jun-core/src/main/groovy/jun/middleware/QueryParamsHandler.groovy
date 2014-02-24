package jun.middleware;

import java.nio.charset.Charset
import groovy.transform.InheritConstructors
import org.apache.http.client.utils.URLEncodedUtils

import jun.middleware.Middleware
import jun.handlers.Handler

class QueryParamsMiddleware implements Middleware {
    public Map parseQueryParams(final Map request) {
        Map queryParams = [:]

        if (request.queryString) {
            final Map rawParams = URLEncodedUtils.parse(request.queryString, Charset.forName("UTF-8"));
            queryParams = rawParams.collectEntries { [(it.getName()):(it.getValue())]}
        }

        return queryParams.asImmutable();
    }

    public Handler wrap(final Handler handler) {
        return new Handler() {
            public Map handle(final Map request) {
                final Map queryParams = parseQueryParams(request)
                final Map req = request.plus([queryParams:queryParams])
                return handler.handle(req.asImmutable())
            }
        }
    }
}

