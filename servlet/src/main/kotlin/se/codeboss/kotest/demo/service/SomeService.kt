package se.codeboss.kotest.demo.service

import java.time.Instant

class SomeService(
    private val timeService: TimeService
) {
    fun createEntity() =
        MyEntity(timeService.now())
}

data class MyEntity(
    val created: Instant
)
