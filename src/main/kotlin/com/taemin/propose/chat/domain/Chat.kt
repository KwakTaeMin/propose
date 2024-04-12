package com.taemin.propose.chat.domain

import com.taemin.propose.user.domain.User
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

private const val CHAT_DATE_TIME_PATTERNS = "yyyy. M. d. a h:mm"
private const val CHAT_DATE_PATTERNS = """\d{4}년 \d{1,2}월 \d{1,2}일 [월화수목금토일]요일"""
private const val NOT_SPLIT_COUNT = 2
private const val EMOTICON = "이모티콘"

@Document
//@CompoundIndex(def = "{'name': 1, 'message': 1, 'chatAt': 1}", name = "chat_compound_index", unique = true)
data class Chat(
    @MongoId(FieldType.OBJECT_ID)
    val id: String? = null,
    @DBRef(lazy = true)
    val user: User,
    val name: String,
    val message: String,
    val chatAt: LocalDateTime,
    val chatDate: LocalDate
) {
    companion object {
        fun of(plainChats: List<String>?, users: List<User>): List<Chat>? {
            return plainChats?.asSequence()
                ?.filter { !isDatePatternValid(it) }
                ?.filter { it.isNotEmpty() }
                ?.filter { it.split(", ").size == NOT_SPLIT_COUNT }
                ?.mapNotNull { from(it, users) }
                ?.distinct()
                ?.toList()
        }

        private fun from(plainChat: String, users: List<User>): Chat? {
            if (plainChat.split(", ", limit = NOT_SPLIT_COUNT).size != NOT_SPLIT_COUNT) return null
            val (dateTimeString, nameWithMessage) = plainChat.split(", ", limit = NOT_SPLIT_COUNT)
            val dateTimeFormatter = DateTimeFormatter.ofPattern(CHAT_DATE_TIME_PATTERNS, Locale.KOREA)
            if (nameWithMessage.split(" : ", limit = NOT_SPLIT_COUNT).size != NOT_SPLIT_COUNT) return null
            val (name, message) = nameWithMessage.split(" : ", limit = NOT_SPLIT_COUNT)
            return Chat(
                user = convertUser(name, users)?: throw RuntimeException("cannot user find"),
                name = name,
                message = message,
                chatAt = LocalDateTime.parse(dateTimeString, dateTimeFormatter),
                chatDate = LocalDateTime.parse(dateTimeString, dateTimeFormatter).toLocalDate()
            )
        }

        private fun convertUser(name: String, users: List<User>): User? {
            return users.filter { user -> name == user.nickname }.getOrNull(0)
        }

        private fun isDatePatternValid(dateString: String): Boolean {
            return dateString.matches(CHAT_DATE_PATTERNS.toRegex())
        }
    }
}
