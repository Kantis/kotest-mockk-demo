package se.codeboss.kotest.demo.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import se.codeboss.kotest.demo.model.toMyRepr

@RestController
@RequestMapping("/api/v1")
class MyController {

    @GetMapping("hello")
    fun sayHello() = "hello"

    @GetMapping("date")
    fun getMyRepo() = "hello".toMyRepr()

}
