package jun.core;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;


public class Response extends Expando {
    public static defaultContentType = "text/html";

    public Map headers;
    public int statusCode;

    public Object body;
    public String encoding = "UTF-8";
    public String contentType = "application/octet-stream";

    public Response(final Object body, final int statusCode) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.encoding = null;

        if (body instanceof String) {
            this.body = new ByteArrayInputStream(body.getBytes("UTF-8"));
            this.conte
        } else if (body instanceof InputStream) {
            this.body = body;
        } else if (body instanceof File) {
            this.body = new FileInputStream(body);
        }
    }

    public Response(final Object body) {
        this(body, 200);
    }

    public Response(final Object body, final String contentType) {
        this(body, 200);
        this.contentType = contentType;
    }
}
