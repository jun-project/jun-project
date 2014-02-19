package jun.adapter.jetty;

import java.io.OutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request as ServerRequest;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jun.handler.Handler;
import jun.Request;

public class HandlerWrapper extends AbstractHandler {
    public final Handler handler;

    public HandlerWrapper(final Handler handler) {
        this.handler = handler;
    }

    public void copy(InputStream input, OutputStream output, final int bufferSize) {
        final byte[] buffer = new byte[bufferSize];

        while (true) {
            final int s = input.read(buffer);

            if (s > 0) {
                output.write(buffer, 0, s);
            } else {
                break;
            }
        }
    }

    public Request makeRequest(servletRequest, servletResponse) {
        return new Request([serverPort: servletRequest.getServerPort(),
                            serverName: servletRequest.getServerName(),
                            remoteAddress: servletRequest.getRemoteAddr(),
                            queryString: servletRequest.getQueryString(),
                            path: servletRequest.getRequestURI(),
                            scheme: servletRequest.getScheme(),
                            method: servletRequest.getMethod().toLowerCase(),
                            contentType: servletRequest.getContentType(),
                            encoding: servletRequest.getCharacterEncoding(),
                            contentLength: servletRequest.getContentLength(),
                            body: servletRequest.getInputStream(),
                            contextPath: servletRequest.getContextPath(),
                            servletRequest: servletRequest,
                            servletResponse: servletResponse]);
    }

    // FIXME: At this momment only supports String body
    public InputStream makeInputStreamFromBody(final String body) {
        return new ByteArrayInputStream(body.getBytes("UTF-8"));
    }

    public void handle(String target, ServerRequest serverRequest,
                       HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        def request = this.makeRequest(servletRequest, servletResponse);
        def response = this.handler.handle(request);

        if (response.hasKey("headers")) {
            // Set headers
            response.headers.each { key, val ->
                def keylowercase = key.toLowerCase();
                if (keylowercase != "content-type") {
                    if (val instanceof List) {
                        val.each { servletResponse.addHeader(key, it.toString()); }
                    } else {
                        servletResponse.setHeader(key, val.toString());
                    }
                }
            }
        }

        // Set status code
        servletResponse.setStatus(response.status);
        servletResponse.setContentType(response.contentType);

        // Set encoding if response has not null encoding value
        if (response.hasKey("encoding")) {
            servletResponse.setCharacterEncoding(response.encoding);
        }

        final InputStream input = this.makeInputStreamFromBody(response.body);
        final OutputStream output = servletResponse.getOutputStream();

        this.copy(input, output, 1024*2);

        input.close();
        output.close();

        serverRequest.setHandled(true);
    }
}