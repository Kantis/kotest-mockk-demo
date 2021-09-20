package se.codeboss.kotest.demo

import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.endWith
import kotlin.math.max

@ExperimentalKotest
class ParameterizedTesting : FunSpec(
    {
        context("CsvSource equivalent") {
            withData(
                listOf(1, 5, 5),
                listOf(1, 0, 1),
                listOf(0, 0, 1)
            ) { (a, b, max) ->
                max(a, b) shouldBe max
            }
        }

        context("Testing all values of an enum") {
            Month.values().forEach {
                test("${it.name} - ends with uary") {
                    it.name should endWith("uary")
                }
            }
        }
    }
) {

    enum class Month {
        January, February, March
    }
}
