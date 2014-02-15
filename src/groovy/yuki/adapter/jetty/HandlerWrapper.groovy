package yuki.adapter.jetty;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request as ServerRequest;
import org.eclipse.jetty.server.handler.AbstractHandler;

import yuki.handler.Handler;
import yuki.core.Request;
import yuki.core.Response;

public class HandlerWrapper extends AbstractHandler {
    public final Handler handler;

    public HandlerWrapper(final Handler handler) {
        this.handler = handler;
    }

    // it should be moved to jetty adapter this logic?
    public static Request makeRequest(final HttpServletRequest servletRequest) {
        final Request req = new Request();

        req.serverPort = servletRequest.getServerPort();
        req.serverName = servletRequest.getServerName();
        req.remoteAddress = servletRequest.getRemoteAddr();
        req.queryString = servletRequest.getQueryString();
        req.path = servletRequest.getRequestURI();
        req.scheme = servletRequest.getScheme();
        req.method = servletRequest.getMethod().toLowerCase();
        req.contentType = servletRequest.getContentType();

        req.body = servletRequest.getInputStream();

        // PENDING:
        // ContentLength
        // CharEncoding

        return req;
    }

    public void updateServletResponse(final HttpServletResponse servletResponse, final Response response) {
        // Set status code
        servletResponse.setStatus(response.statusCode);

        // Set headers
        response.headers.each { key, val ->
            keylowercase = key.toLowerCase();
            if (keylowercase == "content-type") {
                servletResponse.setContentType(val);
            } else {
                if (val instanceof List) {
                    val.each { servletResponse.addHeader(key, it); }
                } else {
                    servletResponse.setHeader(key, val);
                }
            }
        }

        // Set body (at this momment only string is allowed)
        final java.io.PrintWriter writer = servletResponse.getWriter();
        writer.print(response.body);
    }

    public void handle(String target, ServerRequest serverRequest,
                       HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        final Request request = this.makeRequest(servletRequest);
        final Response response = this.handler.handle(request);

        this.updateServletResponse(servletResponse, response);
        serverRequest.setHandled(true);
    }
}