package jun.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

public class Request extends Expando {
    int serverPort;
    String serverName;
    String remoteAddress;
    String queryString;
    String path;
    String scheme;
    String method;
    String contentType;
    int contentLength;
    String encoding;
    String contextPath;

    InputStream body;
}