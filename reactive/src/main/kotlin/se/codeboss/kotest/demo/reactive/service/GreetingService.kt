@file:Suppress("unused")

package se.codeboss.kotest.demo.reactive.service

import com.github.benmanes.caffeine.cache.Caffeine
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import java.time.Duration
import kotlin.random.Random

interface GreetingService {

    suspend fun sayHello(name: String): String
}

@Service
class ExpensiveGreetingService : GreetingService {

    override suspend fun sayHello(name: String): String {
        delay(500)
        return "Hello $name"
    }
}

@Primary
@Service
class CachingGreetingService(
    private val delegate: ExpensiveGreetingService
) : GreetingService {

    private val cache = Caffeine.newBuilder()
        .maximumSize(10_000)
        .expireAfterWrite(Duration.ofMinutes(5))
        .refreshAfterWrite(Duration.ofMinutes(1))
        .build<String, String> { key ->
            runBlocking {
                require(Random.nextInt(0, 1000) % 2 == 0) { "Random failure" }
                delegate.sayHello(key)
            }
        }

    override suspend fun sayHello(name: String): String =
        cache[name] ?: error("stupid cache")
}
