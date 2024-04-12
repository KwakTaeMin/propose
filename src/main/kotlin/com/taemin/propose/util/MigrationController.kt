package com.taemin.propose.util

import com.taemin.propose.chat.service.ChatService
import com.taemin.propose.couple.service.CoupleService
import com.taemin.propose.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MigrationController(
    private val userService: UserService,
    private val chatService: ChatService,
    private val coupleService: CoupleService
) {
    @GetMapping("/init")
    fun init() {
        val users = userService.initUser()
        coupleService.initCouple(users)
        chatService.migrationChat(users)
    }
}