package se.codeboss.kotest.demo.reactive.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.codeboss.kotest.demo.reactive.service.GreetingService

@RestController
@RequestMapping("/api-reactive/v1/greet")
class GreetingController @Autowired constructor(
    val greetingService: GreetingService
) {

    @GetMapping("{name}")
    suspend fun sayHello(@PathVariable name: String) =
        GreetingResponse(greetingService.sayHello(name))
}

data class GreetingResponse(
    val greeting: String
)
