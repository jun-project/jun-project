package jun.handler;

import jun.core.Request;
import jun.core.Response;

public interface Handler {
    public Response handle(final Request request);
}