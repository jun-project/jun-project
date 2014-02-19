package jun.adapter.jetty;

import groovy.transform.CompileStatic;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request as ServerRequest;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jun.handler.Handler;

public class HandlerWrapper extends AbstractHandler {
    public final Handler handler;

    public HandlerWrapper(final Handler handler) {
        this.handler = handler;
    }

    @CompileStatic
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

    @CompileStatic
    public Map makeHeadersMap(final HttpServletRequest servletRequest) {
        final Enumeration<String> headerNames = servletRequest.getHeaderNames();
        final Map headers = [:];

        headerNames.each { String name ->
            headers[name] = servletRequest.getHeader(name);
        }

        return headers.asImmutable();

    }

    public Map makeRequest(servletRequest, servletResponse) {
        return [serverPort: servletRequest.getServerPort(),
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
                headers: this.makeHeadersMap(servletRequest),
                servletRequest: servletRequest,
                servletResponse: servletResponse];
    }

    // FIXME: At this momment only supports String body

    @CompileStatic
    public InputStream makeInputStreamFromBody(final Object body) {
        if (body instanceof String) {
            final String bodyStr = (String) body;
            return new ByteArrayInputStream(bodyStr.getBytes("UTF-8"));
        }
    }

    @CompileStatic
    public void handle(String target, ServerRequest serverRequest,
                       HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        final Map request = this.makeRequest(servletRequest, servletResponse);
        final Map response = this.handler.handle(request);

        if (response.headers) {
            response.headers.each { Map.Entry entry ->
                final String key = (String) entry.getKey();
                final String value = (String) entry.getValue();

                if (key.toLowerCase() != "content-type") {
                    servletResponse.setHeader(key, value);
                }
            }
        }

        // Set status code
        servletResponse.setStatus((Integer) response.status);
        servletResponse.setContentType((String) response.contentType);

        // Set encoding if response has not null encoding value
        if (response.encoding) {
            servletResponse.setCharacterEncoding((String) response.encoding);
        }

        final InputStream input = this.makeInputStreamFromBody(response.body);
        final OutputStream output = servletResponse.getOutputStream();

        this.copy(input, output, 1024*2);

        input.close();
        output.close();

        serverRequest.setHandled(true);
    }
}