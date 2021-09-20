package se.codeboss.kotest.demo.model

import java.time.Instant
import java.time.ZoneId

fun String.toMyRepr() = with (Instant.now().atZone(ZoneId.of("UTC")).toLocalDate()) {
    MyDate(
        this.year,
        this.monthValue,
        this.dayOfMonth
    )
}

data class MyDate(
    val year: Int,
    val month: Int,
    val day: Int
)
