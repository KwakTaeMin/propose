package com.taemin.propose.calender.domain

import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@Document
data class CoupleCalenderEvent(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    val eventName: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val summary: String,
    val coupleCalenderId: String
) {
    companion object {
        fun of(calender: Calendar, coupleCalenderId: String): List<CoupleCalenderEvent> {
            return calender.components.filterIsInstance<VEvent>()
                .mapNotNull { event -> of(event, coupleCalenderId) }
                .toList()
        }

        private fun of(event: VEvent, coupleCalenderId: String): CoupleCalenderEvent {
            return CoupleCalenderEvent(
                eventName = event.name,
                startDate =LocalDateTime.ofInstant(event.startDate.date.toInstant(), ZoneId.systemDefault()),
                endDate = LocalDateTime.ofInstant(event.endDate.date.toInstant(), ZoneId.systemDefault()),
                summary = event.summary.name,
                coupleCalenderId = coupleCalenderId
            )
        }
    }
}
