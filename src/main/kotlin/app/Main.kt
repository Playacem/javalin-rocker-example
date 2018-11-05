package app

import com.fizzed.rocker.Rocker
import io.javalin.Javalin
import io.javalin.rendering.FileRenderer
import io.javalin.rendering.JavalinRenderer
import io.javalin.rendering.template.TemplateUtil.model

fun main(args: Array<String>) {
    // TODO: Replace .html extension with .rocker.html once Javalin updates
    JavalinRenderer.register(FileRenderer { filepath, model -> Rocker.template(filepath).bind(model).render().toString()}, ".rocker.html")
    val app = Javalin.create().start(7000)
    app.get("/hello") { ctx ->
        ctx.render("templates/test.rocker.html", model("message", "Hello Rocker!"))
    }
    app.get("/hello2") {ctx ->
        ctx.html(templates.test.template("Hi! I am using a compiler-checked version!").render().toString())
    }
}