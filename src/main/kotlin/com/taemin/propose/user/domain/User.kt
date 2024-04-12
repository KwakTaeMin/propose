package com.taemin.propose.user.domain

import com.taemin.propose.user.enum.BloodType
import com.taemin.propose.user.enum.MBTIType
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Document
data class User(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    val name: String,
    val nickname: String,
    val birthDay: LocalDate,
    val mbti: MBTIType,
    val bloodType: BloodType,
    val height: Double,
    val weight: Double,
    val address: String,
    val footSize: Int,
    val allergy: List<String>?,
    val feature: List<String>?
) {
    companion object {
        fun createTaemin() : User {
            return User(
                name = "곽태민",
                nickname = "태민",
                birthDay = LocalDate.of(1990,2,1),
                mbti = MBTIType.INFP,
                bloodType = BloodType.A,
                height = 177.7,
                weight = 80.0,
                address = "서울시 성동구 성수동2가",
                footSize = 270,
                allergy = null,
                feature = null
            )
        }
        fun createHyunjoo() : User {
            return User(
                name = "이현주",
                nickname = "현주",
                birthDay = LocalDate.of(1993,12,3),
                mbti = MBTIType.ENFP,
                bloodType = BloodType.B,
                height = 165.1,
                weight = 0.0,
                address = "시흥시 대야로3번길7",
                footSize = 250,
                allergy = arrayListOf("민트"),
                feature = arrayListOf("겨울 쿨톤")
            )
        }
    }
}
