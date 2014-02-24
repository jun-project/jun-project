package jun.adapter.jetty.handlers.websocket;

import org.eclipse.jetty.websocket.api.Session
import jun.handlers.Handler

public abstract class WSHandler implements Handler {
    abstract public void onConnect(Session session);
    abstract public void onError(Throwable exception);
    abstract public void onMessage(String message);
    abstract public void onClose(String reason, Integer statusCode);

    public Map handle(final Map request) {
        return [body: this];
    }
}