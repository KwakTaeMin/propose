package com.taemin.propose.util

import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component

private const val CHAT_FOLDER_PATTERN = "classpath:/chat/*.txt"

@Component
class ResourceManager {
    companion object {
        fun getChatResource(): List<Resource> {
            val resolver = PathMatchingResourcePatternResolver()
            return resolver.getResources(CHAT_FOLDER_PATTERN).toList()
        }
    }
}