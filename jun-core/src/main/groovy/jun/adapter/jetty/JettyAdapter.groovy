package jun.adapter.jetty;

import groovy.transform.InheritConstructors;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;

import org.eclipse.jetty.util.thread.QueuedThreadPool;

import jun.handler.Handler;
import jun.adapter.jetty.HandlerWrapper;


public class JettyAdapter extends jun.adapter.Adapter {
    public final Server server;

    public QueuedThreadPool makePool() {
        final int maxThreads = this.options.get("poolMaxThreads", 50);
        final boolean daemonByDefault = this.options.get("poolThreadsDaemon", false);

        final QueuedThreadPool pool = new QueuedThreadPool(maxThreads);
        pool.setDaemon(daemonByDefault);
        return pool;
    }

    public ServerConnector makeHttpConnector(final Server server) {
        final int port = options.get("port", 8080);
        final int idleTimeout = options.get("idleTimeout", 200000);
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
        this.server = new Server(pool);

        // Attach http connector
        final ServerConnector httpConnector = makeHttpConnector(this.server);
        //final ServerConnector httpsConnector = makeHttpsConnector(this.server);

        this.server.addConnector(httpConnector);
        //server.addConnector(httpsConnector);

        final HandlerWrapper handlerWrapper = new HandlerWrapper(handler);
        this.server.setHandler(handlerWrapper);
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
