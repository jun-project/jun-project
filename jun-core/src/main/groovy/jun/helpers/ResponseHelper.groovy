package jun.helpers

public class ResponseHelper {
    public static String defaultContentType = "text/html"
    public static String defaultEncoding = "UTF-8"
    public static Integer defaultStatusCode = 200

    public static Map response(final String body, final Integer status, final String contentType) {
        final Map rsp = [body: body, status: status, contentType: contentType]
        return rsp.asImmutable()
    }

    public static Map response(final String body, final Integer status) {
        return response(body, status, defaultContentType)
    }

    public static Map response(final String body) {
        return response(body, defaultStatusCode, defaultContentType)
    }
}