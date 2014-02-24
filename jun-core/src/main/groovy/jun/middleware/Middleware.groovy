package jun.middleware

import jun.handlers.Handler;

public interface Middleware {
    public Handler wrap(final Handler handler);
}