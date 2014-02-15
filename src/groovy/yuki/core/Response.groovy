package yuki.core;

public class Response extends Expando {
    public static defaultContentType = "text/html";

    public long statusCode;
    public Map headers;
    public String body;

    // WARNING: body is string type for first moment.
    // Response should be refactored for accept strams and other types.

    public Response(final String body, final long statusCode, final Map headers) {
        this.body = body;
        this.statusCode = statusCode;
        this.headers = {};
    }

    public Response(final String body, final long statusCode) {
        super(body, statusCode, {});
    }
}
