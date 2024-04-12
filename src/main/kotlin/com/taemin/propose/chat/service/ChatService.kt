package com.taemin.propose.chat.service

import com.taemin.propose.chat.domain.Chat
import com.taemin.propose.chat.dto.ChatCountByDateResponse
import com.taemin.propose.chat.dto.ChatCountByMessageResponse
import com.taemin.propose.chat.dto.ChatCountByNameResponse
import com.taemin.propose.chat.dto.ChatCountResponse
import com.taemin.propose.chat.repository.ChatRepository
import com.taemin.propose.user.domain.User
import com.taemin.propose.user.repository.UserRepository
import com.taemin.propose.util.ResourceManager
import org.springframework.stereotype.Service

private const val EMOTICON_STRING = "이모티콘"
private const val PHOTO_STRING = "사진"

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    fun getChatCount(): ChatCountResponse {
        return ChatCountResponse(chatRepository.findAll().count())
    }


    fun getChatCountByName(name: String): ChatCountByNameResponse {
        return ChatCountByNameResponse(name, chatRepository.findByName(name).size)
    }


    fun getChatCountByDate(): List<ChatCountByDateResponse> {
        val chats = chatRepository.findAll()
        val responses = getChatCountByDateResponses(chats)
        return responses.sortedBy { it.date }
    }


    fun getChatCountByDateByName(name: String): List<ChatCountByDateResponse> {
        val chats = chatRepository.findByName(name)
        val responses = getChatCountByDateResponses(chats)
        return responses.sortedBy { it.date }
    }


    fun getChatMessageRanking(): List<ChatCountByMessageResponse> {
        return chatRepository.findAll().groupBy { it.message }
            .asSequence()
            .map { ChatCountByMessageResponse(message = it.key, count = it.value.size) }
            .filter { it.message.trim() != EMOTICON_STRING }
            .filter { it.message.trim() != PHOTO_STRING }
            .toList()
            .sortedByDescending { it.count }
            .toList().subList(0, 200)
    }

    fun getChatMessageRankingByName(name: String): List<ChatCountByMessageResponse> {
        return chatRepository.findByName(name).groupBy { it.message }
            .asSequence()
            .map { ChatCountByMessageResponse(message = it.key, count = it.value.size) }
            .filter { it.message.trim() != EMOTICON_STRING }
            .filter { it.message.trim() != PHOTO_STRING }
            .toList()
            .sortedByDescending { it.count }
            .toList().subList(0, 200)
    }

    fun migrationChat(users: List<User>) {
        val resources = ResourceManager.getChatResource()
        resources.forEach { resource ->
            val chatLines = resource.inputStream.bufferedReader().use { it.readLines() }
            val chats = Chat.of(chatLines, users)
            requireNotNull(chats)
            chatRepository.saveAll(chats)
        }
    }

    private fun getChatCountByDateResponses(chats: List<Chat>): MutableList<ChatCountByDateResponse> {
        val chatsByChatDate = chats.groupBy { chat -> chat.chatDate }
        val dates = chatsByChatDate.keys
        val responses = mutableListOf<ChatCountByDateResponse>()
        dates.forEach { date ->
            val chatsByDate = chatsByChatDate[date]
            checkNotNull(chatsByDate)
            responses.add(ChatCountByDateResponse(date, chatsByDate.size))
        }
        return responses
    }
}