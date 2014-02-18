package jun.handler;

import jun.core.Request;
import jun.core.Response;

//public interface Handler<A extends Request,B extends Response> {
//    public B handle(A request);
//}
public interface Handler<A extends Object,B extends Object> {
    public B handle(A request);
}