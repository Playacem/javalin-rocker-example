package app;

import com.fizzed.rocker.Rocker;
import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JavalinRenderer.register((filepath, model) -> Rocker.template(filepath).bind(model).render().toString(),".rocker.html");
        Javalin app = Javalin.create().start(7000);
        app.get("/hello", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", "Hello Rocker!");
            ctx.render("templates/demo.rocker.html", model);
        });
        app.get("/hello2", ctx -> {
            ctx.html(templates.demo.template("Hi! I am using a compiler-checked version!").render().toString());
        });
    }
}
