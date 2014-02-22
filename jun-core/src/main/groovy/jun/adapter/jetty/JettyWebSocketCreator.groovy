package jun.adapter.jetty;

import org.eclipse.jetty.websocket.servlet.WebSocketCreator
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse
import org.eclipse.jetty.websocket.api.WebSocketAdapter
import org.eclipse.jetty.websocket.api.Session

import jun.adapter.jetty.handlers.websocket.WSHandler

public class JettyWebSocketCreator implements WebSocketCreator {
    private final WSHandler wshandler;

    public JettyWebSocketCreator(final WSHandler wshandler) {
        this.wshandler = wshandler;
    }

    public Object createWebSocket(ServletUpgradeRequest upRequest, ServletUpgradeResponse upResponse) {
        final WSHandler wshandler = this.wshandler;

        return new WebSocketAdapter() {
            @Override
            public void onWebSocketConnect(final Session session) {
                wshandler.onConnect(session)
            }

            @Override
            public void onWebSocketText(final String msg) {
                wshandler.onMessage(msg)
            }

            public void onWebSocketClose(int statusCode, String reason) {
                wshandler.onClose(reason, statusCode)
            }

            public void onWebSocketError(Throwable error) {
                wshandler.onError(error)
            }
        }
    }
}
