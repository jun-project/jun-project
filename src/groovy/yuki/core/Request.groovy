package yuki.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

public class Request extends Expando {
    final long serverPort;
    final String serverName;
    final String remoteAddress;
    final String queryString;
    final String path;
    final String scheme;
    final String method;
    final String contentType;

    final ServletInputStream body;

    public Request(final HttpServletRequest servletRequest) {
        this.serverPort = servletRequest.getServerPort();
        this.serverName = servletRequest.getServerName();
        this.remoteAddress = servletRequest.getRemoteAddr();
        this.queryString = servletRequest.getQueryString();
        this.path = servletRequest.getRequestURI();
        this.scheme = servletRequest.getScheme();
        this.method = servletRequest.getMethod().toLowerCase();
        this.contentType = servletRequest.getContentType();

        this.body = servletRequest.getInputStream();

        // PENDING:
        // ContentLength
        // CharEncoding
    }
}