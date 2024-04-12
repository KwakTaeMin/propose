package com.taemin.propose.couple.domain

import com.taemin.propose.user.domain.User
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Document
data class Couple(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    @DBRef
    val user: User,
    @DBRef
    val anotherUser: User,
    val meetDate: LocalDate
) {
    companion object {
        fun of(users: List<User>, meetDate: LocalDate): List<Couple> {
            return arrayListOf(
                Couple(user = users[0], anotherUser = users[1], meetDate = meetDate),
                Couple(user = users[1], anotherUser = users[0], meetDate = meetDate)
            )
        }
    }
}
