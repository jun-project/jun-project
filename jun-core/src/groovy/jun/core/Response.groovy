package jun.core;

import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;


public class Response {
    static defaultContentType = "text/html";

    def headers;
    def statusCode;
    def body;

    def encoding = "UTF-8";
    def contentType = "application/octet-stream";

    def Response(body, statusCode, contentType) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.contentType = contentType;
        this.encoding = null;
        this.body = body;
    }

    def Response(body, contentType) {
        this(body, 200, contentType);
    }

    def Response(body) {
        this(body, 200, "text/html");
    }

    def getInputStream() {
        if (this.body instanceof String) {
            return new ByteArrayInputStream(body.getBytes("UTF-8"));
        } else if (this.body instanceof InputStream) {
            return this.body;
        } else if (this.body instanceof File) {
            return new FileInputStream(body);
        }

        return this.body;
    }
}
