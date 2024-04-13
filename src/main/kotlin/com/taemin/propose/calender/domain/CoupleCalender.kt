package com.taemin.propose.calender.domain

import com.taemin.propose.couple.domain.Couple
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId

@Document
data class CoupleCalender(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    @DBRef
    val couple: Couple
) {
    companion object {
        fun of(couple: Couple): CoupleCalender {
            return CoupleCalender(couple = couple)
        }
    }
}
