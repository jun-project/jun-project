package jun.app;

class Application {
    static currentApp = new Application();
    def name = "jun-app";

    def Application() {}

    def Application(name) {
        this.name = name;
    }

    // Return current application
    def getCurrent() {
        return this.currentApp;
    }
}