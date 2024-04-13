package com.taemin.propose.util

import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Component


private const val CALENDER_FOLDER_PATTERN = "classpath:/calender/calender.ics"

@Component
class IcsFileReaderService {
    fun readIcsFile(): Calendar {
        val resolver = PathMatchingResourcePatternResolver()
        val inputStream = resolver.getResource(CALENDER_FOLDER_PATTERN).inputStream
        val calenderBuilder = CalendarBuilder()
        return calenderBuilder.build(inputStream)
    }
}