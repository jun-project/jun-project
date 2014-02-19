package jun;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import jun.util.ImmutableHashMap;
import org.pcollections.PMap;

public class Response extends ImmutableHashMap {
    static String defaultContentType = "text/html";
    static String defaultEncoding = "UTF-8";
    static Integer defaultStatusCode = 200;

    public Response(final String body) {
        this(body, defaultStatusCode, defaultContentType);
    }

    public Response(final ImmutableHashMap data) {
        super(data);
    }

    public Response(final String body, final Integer statusCode, final String contentType) {
        this([body: body,
              contentType: defaultContentType,
              encoding: defaultEncoding,
              status: statusCode,
              contentType: contentType]);
    }

    public Response(final File body) {
        super([body: body]);
    }

    public Response(final InputStream body) {
        super([body: body]);
    }

    public Response(final Map opts) {
        super(opts);
    }

    public Response() {
        this("", 200, defaultContentType);
    }

    public Response withHeaders(final Map headers) {
        return this.assoc([headers:headers]);
    }

    public Response assoc(final Map opts) {
        return new Response(super.assoc(opts));
    }

    public Response assoc(final Object key, final Object value) {
        return this.assoc([key:value]);
    }

    public Response dissoc(final Objects... keys) {
        return new Response(this.dissoc(keys));
    }

    public Object propertyMissing(final Object name) {
        if (this.hasKey(name)) {
            return this.get(name);
        }
        return null;
    }
}

