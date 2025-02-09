package com.revature;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Hello world!
 */
public final class App {
    private App() {
    }
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx-> ctx.result("Hello World"));

    }
}
