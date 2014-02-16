package jun.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends Expando {
    def serverPort;
    def serverName;
    def remoteAddress;
    def queryString;
    def path;
    def scheme;
    def method;
    def contentType;
    def contentLength;
    def encoding;
    def contextPath;
    def body;

    HttpServletResponse servletResponse;
    HttpServletRequest servletRequest;

    def Request(servletRequest, servletResponse) {
        this.servletResponse = servletResponse;
        this.servletRequest = servletRequest;

        this.initialize(servletRequest, servletResponse);
    }

    def makeHeadersMap(servletRequest) {
        def headerNames = servletRequest.getHeaderNames();
        def headers = [:];

        headerNames.each { name ->
            headers[name] = servletRequest.getHeader(name);
        }

        return headers;
    }

    def makeCookiesMap(servletRequest) {
        def cookies = servletRequest.getCookies();
        def cookiesMap = cookies.collectEntries { ["s": 1] }

        return cookiesMap;
    }

    def initialize(servletRequest, servletResponse) {
        this.serverPort = servletRequest.getServerPort();
        this.serverName = servletRequest.getServerName();
        this.remoteAddress = servletRequest.getRemoteAddr();
        this.queryString = servletRequest.getQueryString();
        this.path = servletRequest.getRequestURI();
        this.scheme = servletRequest.getScheme();
        this.method = servletRequest.getMethod().toLowerCase();
        this.contentType = servletRequest.getContentType();
        this.encoding = servletRequest.getCharacterEncoding();
        this.contentLength = servletRequest.getContentLength();
        this.body = servletRequest.getInputStream();
        this.contextPath = servletRequest.getContextPath();

        this.headers = this.makeHeadersMap(servletRequest);
        //this.cookies = this.makeCookiesMap(servletRequest);
    }
}