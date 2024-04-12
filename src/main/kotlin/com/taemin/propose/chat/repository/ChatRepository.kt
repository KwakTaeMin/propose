package com.taemin.propose.chat.repository

import com.taemin.propose.chat.domain.Chat
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : MongoRepository<Chat, String> {
    fun findByName(name: String): List<Chat>
}