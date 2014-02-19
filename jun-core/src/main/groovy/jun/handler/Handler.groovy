package jun.handler;

import jun.Request;
import jun.Response;

public interface Handler<A extends Request,B extends Response> {
    public B handle(A request);
}