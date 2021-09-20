package se.codeboss.kotest.demo.api

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import se.codeboss.kotest.demo.config.JacksonConfig
import se.codeboss.kotest.demo.model.MyDate
import se.codeboss.kotest.demo.model.toMyRepr

/**
 * Example of using Kotest with Spring, including injection of ObjectMapper
 */
@Suppress("BlockingMethodInNonBlockingContext")
@AutoConfigureJson
@Import(JacksonConfig::class)
@ContextConfiguration(
    classes = [MyController::class]
)
class MyControllerTest(
    @Autowired val objectMapper: ObjectMapper
) : FunSpec() {

    private val mvc: MockMvc = MockMvcBuilders.standaloneSetup(MyController()).build()

    init {

        test("Basic ObjectMapper usage") {
            objectMapper.writeValueAsString(MyData("Hej")) shouldBe """{ "name" : "Hej" }"""
        }

        test("Basic MockMVC sample") {
            mvc.get("/api/v1/hello")
                .andExpect { content { string("hello") } }
        }

        test("Mocking extension function") {
            mockkStatic(String::toMyRepr)

            every { "hello".toMyRepr() } returns MyDate(2021, 1, 7)

            mvc.get("/api/v1/date")
                .andExpect { content { json(""" { "year": 2021, "month": 1, "day": 7 }""") } }
        }
    }

    // https://kotest.io/docs/extensions/spring.html
    override fun extensions() = listOf(SpringExtension)

    data class MyData(
        val name: String
    )
}
