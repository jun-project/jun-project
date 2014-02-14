package yuki.adapter.jetty;

import groovy.transform.InheritConstructors;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;

import org.eclipse.jetty.util.thread.QueuedThreadPool;

import yuki.handler.Handler;
import yuki.adapter.jetty.HandlerWrapper;


public class JettyAdapter extends yuki.adapter.Adapter {
    public final Server server;

    public QueuedThreadPool makePool() {
        final long maxThreads = this.options.get("poolMaxThreads", 50);
        final boolean daemonByDefault = this.options.get("poolThreadsDaemon", false);

        final QueuedThreadPool pool = new QueuedThreadPool(maxThreads);
        pool.setDaemon(daemonByDefault);
        return pool;
    }

    public ServerConnector makeHttpConnector(final Server server) {
        final long port = options.get("port", 8080);
        final long idleTimeout = options.get("idleTimeout", 200000);
        final String host = options.get("host", null);

        final ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setHost(host);
        connector.setIdleTimeout(idleTimeout);

        return connector;
    }

    public JettyAdapter(final Handler handler, final Map options) {
        super(handler, options);

        final QueuedThreadPool pool = makePool()
        final Server server = new Server(pool);

        // Attach http connector
        final ServerConnector httpConnector = makeHttpConnector(server);
        //final ServerConnector httpsConnector = makeHttpsConnector(server);

        server.addConnector(httpConnector);
        server.addConnector(httpsConnector);

        final HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
        server.setHandler(handlerWrapper);
    }

    public void start() {
        this.server.start();
    }

    public void stop() {
        this.server.stop();
    }

    public void join() {
        this.server.join();
    }
}
