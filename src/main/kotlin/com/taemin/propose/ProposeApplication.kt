package com.taemin.propose

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
class ProposeApplication

fun main(args: Array<String>) {
    runApplication<ProposeApplication>(*args)
}
