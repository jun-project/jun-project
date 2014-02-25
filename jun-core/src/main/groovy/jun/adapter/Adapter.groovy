package jun.adapter;

import jun.util.Lifecycle
import jun.handlers.Handler;

public abstract class Adapter implements Lifecycle {
    public final Map options;
    public final Handler handler;

    public Adapter(final Handler handler, final Map options) {
        this.options = options;
        this.handler = handler;
    }

    public abstract void start();
    public abstract void join();
    public abstract void stop();
}