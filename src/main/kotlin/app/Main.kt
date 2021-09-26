package app

import com.fizzed.rocker.Rocker
import io.javalin.Javalin
import io.javalin.plugin.rendering.FileRenderer
import io.javalin.plugin.rendering.JavalinRenderer
import io.javalin.plugin.rendering.template.TemplateUtil.model

fun main() {
    JavalinRenderer.register({ filepath, model, _ -> Rocker.template(filepath).bind(model).render().toString() }, ".rhtml")
    val app = Javalin.create().start(7000)
    app.get("/") { ctx ->
        ctx.render("templates/root.rocker.rhtml")
    }
    app.get("/hello") { ctx ->
        ctx.render("templates/demo.rocker.rhtml", model("message", "Hello Rocker from Kotlin!"))
    }
    app.get("/hello2") { ctx ->
        ctx.html(templates.demo.template("Hi from Kotlin! I am using a compiler-checked version!").render().toString())
    }
}