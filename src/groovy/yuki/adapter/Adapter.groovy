package yuki.adapter;

import yuki.util.Lifecycle
import yuki.handler.Handler;

public abstract class Adapter implements Lifecycle {
    final Map options;
    final Handler handler;

    public Adapter(final Handler handler, final Map options) {
        this.options = options;
        this.handler = handler;
    }

    public abstract void start();
    public abstract void join();
    public abstract void stop();
}