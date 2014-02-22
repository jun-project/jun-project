package jun.adapter.jetty;

import groovy.transform.CompileStatic;

import java.io.OutputStream;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jun.adapter.jetty.JettyWebSocketHandler
import jun.adapter.jetty.handlers.websocket.WSHandler

import jun.handler.Handler;

public class JettyHandlerWrapper extends AbstractHandler {
    public final Handler handler;

    public JettyHandlerWrapper(final Handler handler) {
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

    @CompileStatic
    public InputStream makeInputStreamFromBody(final Object body) {
        if (body instanceof String) {
            final String bodyStr = (String) body;
            return new ByteArrayInputStream(bodyStr.getBytes("UTF-8"));
        }
    }

    @CompileStatic
    public void handleResponse(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                               Request serverRequest, Map response) {
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

    @CompileStatic
    public void handleWebSocketResponse(String target, Request serverRequest, HttpServletRequest servletRequest,
                                        HttpServletResponse servletResponse, WSHandler wshandler) {
        def ws = new JettyWebSocketHandler(wshandler)
        def factory = ws.getWebSocketFactory() 

        ws.configure(factory);
        factory.init();
        ws.handle(target, serverRequest, servletRequest, servletResponse);
    }

    @CompileStatic
    public void handle(String target, Request serverRequest,
                       HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        final Map request = this.makeRequest(servletRequest, servletResponse);
        final Map response = this.handler.handle(request);

        final Object body = response.body;

        if (body instanceof WSHandler) {
            this.handleWebSocketResponse(target, serverRequest, servletRequest,  servletResponse, (WSHandler) body);
        } else if (body instanceof String) {
            this.handleResponse(servletRequest, servletResponse, serverRequest, response);
        }
    }
}