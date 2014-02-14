package yuki.adapter.jetty;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import yuki.handler.Handler;

public class HandlerWrapper extends AbstractHandler {
    public final Handler handler;

    public HandlerWrapper(final Handler handler) {
        this.handler = handler;
    }

    public void handle(String target, Request baseReq,
                       HttpServletRequest request, HttpServletResponse response) {
    }
}