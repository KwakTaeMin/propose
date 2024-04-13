package com.taemin.propose.couple.domain

import com.taemin.propose.user.domain.User
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

@Document
data class Couple(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    val userIds: List<String>,
    val meetDate: LocalDate
) {
    companion object {
        fun of(users: List<User>, meetDate: LocalDate): Couple {
            val userIds = users.map { user ->
                requireNotNull(user.id)
                user.id
            }.toList()
            return Couple(userIds = userIds , meetDate = meetDate)
        }
    }
}
