package se.codeboss.kotest.demo.reactive

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableWebFlux
class ReactiveDemoApplication

fun main(args: Array<String>) {
    runApplication<ReactiveDemoApplication>(*args)
}
