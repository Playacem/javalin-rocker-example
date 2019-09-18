package app

import com.fizzed.rocker.Rocker
import kotlin.test.Test
import kotlin.test.assertTrue

class MainTest {

    @Test
    fun testCompiled() {
        val template = templates.demo.template("Test-Message").render().toString()
        assertTrue(template.startsWith("<h1>Test-Message</h1>"), "Compiled template does not contain supplied message!")
    }

    @Test
    fun testDynamic() {
        val template = Rocker.template("templates/demo.rocker.html")
                .bind(mapOf("message" to "test-dyn"))
                .render()
                .toString()
        assertTrue(template.startsWith("<h1>test-dyn</h1>"), "Dynamic template does not contain supplied message!")
    }
}