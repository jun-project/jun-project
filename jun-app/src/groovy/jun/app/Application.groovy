package jun.app;

import static jun.helpers.MiddlewareHelper.combine;

import groovy.util.ConfigSlurper;
import jun.adapter.jetty.JettyAdapter;

import jun.handler.middleware.QueryParamsHandler;
import jun.handler.AbstractHandler;


class Application extends AbstractHandler {
    def initialized = false;
    def applicationName = "jun-app";

    def urlDispatchHandler;
    def mainHandler;

    // Main Constructor
    def Application(name) {
        this.applicationName = name;
    }

    // Configuration
    def setUrlDispatcher(dispatcher) {
        this.urlDispatchHandler = dispatcher;
    }

    def makeHandler() {
        if (!this.urlDispatchHandler) {
            throw RuntimeException("Can not start app if url dispatcher is not specified.");
        }

        // TODO: add more default middlewares when them
        // are implemented on jun-core module.
        return combine(this.urlDispatchHandler,
                       new QueryParamsHandler());
    }

    def initializeHandler() {
        this.mainHandler = this.makeHandler();
    }

    def initializeConfig() {
        // TODO
    }

    def initialize() {
        println "Initialize application..."
        this.initializeHandler();
        this.initializeConfig();
    }

    def handle(request) {
        if (!this.initialized) {
            this.initialze();
            this.initialized = true;
        }

        return this.mainHandler.handle(request);
    }
}