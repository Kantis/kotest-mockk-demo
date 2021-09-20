package se.codeboss.kotest.demo

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import se.codeboss.kotest.demo.service.SomeService
import se.codeboss.kotest.demo.service.SomeServiceV2
import se.codeboss.kotest.demo.service.Time
import se.codeboss.kotest.demo.service.TimeService
import java.time.Instant

class MockkSampleTests : FunSpec(
    {
        val timeService = mockk<TimeService>()
        val myService = SomeService(timeService)

        val expected = Instant.now()

        test("uses timestamp from TimeService") {
            every { timeService.now() } returns expected
            // whenever(timeService().now()).thenReturn(expected)

            myService.createEntity().created shouldBe expected
            // assertThat(myService.createEntity().created).isEqualTo(expected)
        }

        mockkObject(Time)
        val myService2 = SomeServiceV2()

        test("uses timestamp from Time") {
            every { Time.now() } returns expected
            myService2.createEntity().created shouldBe expected
        }
    }
)
