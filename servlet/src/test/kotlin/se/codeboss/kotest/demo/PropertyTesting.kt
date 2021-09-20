package se.codeboss.kotest.demo

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.checkAll
import se.codeboss.kotest.demo.model.Money

class PropertyTesting : FunSpec(
    {
        /**
         * Will test 1000 different Long values in this case, including edgecases (0, 1, -1, Min and Max values)
         * Check here for more examples on how to define custom generators etc:
         * https://kotest.io/docs/extensions/spring.html
         */
        test("Property testing sample") {
            checkAll<Long> {
                Money(it).truncated().value % 100 shouldBe 0L
            }
        }
    }
)
