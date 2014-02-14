package yuki.handler;

import yuki.core.Request;
import yuki.core.Response;

public interface Handler {
    public Response handle(final Request request);
}