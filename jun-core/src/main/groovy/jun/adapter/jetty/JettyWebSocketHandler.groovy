package jun.adapter.jetty;

import org.eclipse.jetty.websocket.server.WebSocketHandler
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory 

import jun.adapter.jetty.JettyWebSocketCreator
import jun.adapter.jetty.handlers.websocket.WSHandler


class JettyWebSocketHandler extends WebSocketHandler {
    private final WSHandler wshandler;

    public JettyWebSocketHandler(final WSHandler wshandler) {
        super();
        this.wshandler = wshandler;
    }

    public void configure(WebSocketServletFactory factory) {
        factory.register(this.class);
        factory.setCreator(new JettyWebSocketCreator(this.wshandler));
    }
}
