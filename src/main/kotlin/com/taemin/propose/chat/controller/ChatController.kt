package com.taemin.propose.chat.controller

import com.taemin.propose.chat.dto.ChatCountByDateResponse
import com.taemin.propose.chat.dto.ChatCountByMessageResponse
import com.taemin.propose.chat.dto.ChatCountByNameResponse
import com.taemin.propose.chat.dto.ChatCountResponse
import com.taemin.propose.chat.service.ChatService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController("/chat")
class ChatController(private val chatService: ChatService) {


    @GetMapping("/count")
    fun getChatCount(): ResponseEntity<ChatCountResponse> {
        return ResponseEntity.ok(chatService.getChatCount())
    }

    @GetMapping("/count/{name}")
    fun getChatCountByName(@PathVariable name: String): ResponseEntity<ChatCountByNameResponse> {
        return ResponseEntity.ok(chatService.getChatCountByName(name))
    }

    @GetMapping("/dateByChat")
    fun getDateByChat(): ResponseEntity<List<ChatCountByDateResponse>> {
        return ResponseEntity.ok(chatService.getChatCountByDate())
    }

    @GetMapping("/dateByChat/{name}")
    fun getDateByChatByName(@PathVariable name: String): ResponseEntity<List<ChatCountByDateResponse>> {
        return ResponseEntity.ok(chatService.getChatCountByDateByName(name))
    }

    @GetMapping("/message/ranking")
    fun getMessageRank(): ResponseEntity<List<ChatCountByMessageResponse>> {
        return ResponseEntity.ok(chatService.getChatMessageRanking())
    }

    @GetMapping("/message/ranking/{name}")
    fun getMessageRank(@PathVariable name: String): ResponseEntity<List<ChatCountByMessageResponse>> {
        return ResponseEntity.ok(chatService.getChatMessageRankingByName(name))
    }
}
