package se.codeboss.kotest.demo.reactive

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.coEvery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import se.codeboss.kotest.demo.reactive.api.GreetingController
import se.codeboss.kotest.demo.reactive.service.GreetingService

@WebFluxTest(GreetingController::class)
class GreetingControllerTest : FunSpec() {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var service: GreetingService

    override fun extensions() = listOf(SpringExtension)

    init {
        test("returns value from service") {
            coEvery { service.sayHello("jim") } returns "hello jim!"

            webTestClient.get()
                .uri("/api-reactive/v1/greet/jim")
                .exchange()
                .expectStatus().isOk
                .expectBody().json(
                    """
                    {
                        "greeting": "hello jim!"                    
                    }
                    """.trimIndent()
                )
        }
    }
}
