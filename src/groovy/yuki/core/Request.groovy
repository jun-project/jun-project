package yuki.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

public class Request extends Expando {
    long serverPort;
    String serverName;
    String remoteAddress;
    String queryString;
    String path;
    String scheme;
    String method;
    String contentType;

    InputStream body;
}