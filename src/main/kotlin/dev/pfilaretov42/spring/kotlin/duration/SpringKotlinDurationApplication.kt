package dev.pfilaretov42.spring.kotlin.duration

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@SpringBootApplication
class SpringKotlinDurationApplication

fun main(args: Array<String>) {
    runApplication<SpringKotlinDurationApplication>(*args)
}

@RestController
class DurationController {

    /**
     * Returns HTTP 200
     */
    @PostMapping("/java/duration")
    fun postDuration(@RequestBody dto: JavaDurationDto) {
        logger.info { "java Duration [dto=$dto]" }
    }

    /**
     * Returns HTTP 500:
     * com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of
     * `kotlin.time.Duration` (no Creators, like default constructor, exist):
     * no String-argument constructor/factory method to deserialize from String value ('PT1S')
     */
    @PostMapping("/kotlin/duration")
    fun postDuration(@RequestBody dto: KotlinDurationDto) {
        logger.info { "kotlin Duration [dto=$dto]" }
    }
}

data class JavaDurationDto(
    val id: String,
    val name: String,
    val duration: java.time.Duration,
)

data class KotlinDurationDto(
    val id: String,
    val name: String,
    val duration: kotlin.time.Duration,
)