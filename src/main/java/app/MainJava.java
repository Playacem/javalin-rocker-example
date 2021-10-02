package app;

import com.fizzed.rocker.Rocker;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;

import java.util.HashMap;
import java.util.Map;

public class MainJava {
    public static void main(String[] args) {
        JavalinRenderer.register((filepath, model, ctx) -> Rocker.template(filepath).bind(model).render().toString(), ".rhtml");
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.render("templates/root.rocker.html"));
        app.get("/hello", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Rocker from Java!");
            ctx.render("templates/demo.rocker.html", model);
        });
        app.get("/hello2", ctx -> ctx.html(templates.demo.template("Hi from Java! I am using a compiler-checked version!").render().toString()));
    }
}
