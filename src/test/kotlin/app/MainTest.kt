package app

import com.fizzed.rocker.Rocker
import io.javalin.Javalin
import io.javalin.plugin.rendering.FileRenderer
import io.javalin.plugin.rendering.JavalinRenderer
import io.javalin.plugin.rendering.template.TemplateUtil
import io.javalin.testtools.TestUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MainTest {

    private val testApp by lazy { prepareApp() }

    private fun prepareApp(): Javalin {
        JavalinRenderer.register({ filepath, model, _ -> Rocker.template(filepath).bind(model).render().toString() }, ".rhtml")
        val app = Javalin.create()
        app.get("/") { ctx ->
            ctx.render("templates/root.rocker.rhtml")
        }
        app.get("/hello") { ctx ->
            ctx.render("templates/demo.rocker.rhtml", TemplateUtil.model("message", "dynamic"))
        }
        app.get("/hello2") { ctx ->
            ctx.html(templates.demo.template("precompiled").render().toString())
        }
        return app
    }

    @Test
    fun testCompiled() {
        val template = templates.demo.template("Test-Message").render().toString()
        assertTrue(template.startsWith("<h1>Test-Message</h1>"), "Compiled template does not contain supplied message!")
    }

    @Test
    fun testDynamic() {
        val template = Rocker.template("templates/demo.rocker.rhtml")
                .bind(mapOf("message" to "test-dyn"))
                .render()
                .toString()
        assertTrue(template.startsWith("<h1>test-dyn</h1>"), "Dynamic template does not contain supplied message!")
    }

    @Test
    fun rootWorks() = TestUtil.test(testApp) { _, http ->
        val body = http.get("/").body
        assertNotNull(body)
        assertTrue("<title>Javalin Rocker Demo</title>" in body.string())
    }

    @Test
    fun dynamicRenderingWorks() = TestUtil.test(testApp) { _, http ->
        val body = http.get("/hello").body
        assertNotNull(body)
        assertTrue("<h1>dynamic</h1>" in body.string())
    }

    @Test
    fun precompiledRenderingWorks() = TestUtil.test(testApp) { _, http ->
        val body = http.get("/hello2").body
        assertNotNull(body)
        assertTrue("<h1>precompiled</h1>" in body.string())
    }
}