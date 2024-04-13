package com.taemin.propose.util

import com.taemin.propose.calender.service.CoupleCalenderService
import com.taemin.propose.chat.service.ChatService
import com.taemin.propose.couple.service.CoupleService
import com.taemin.propose.user.service.UserService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MigrationController(
    private val userService: UserService,
    private val chatService: ChatService,
    private val coupleService: CoupleService,
    private val coupleCalenderService: CoupleCalenderService
) {
    @GetMapping("/init")
    @Transactional
    fun init() {
        val users = userService.initUser()
        val couple = coupleService.initCouple(users)
        chatService.migrationChat(users)
        val calender = coupleCalenderService.createCalender(couple)
        coupleCalenderService.init(requireNotNull(calender.id))
    }
}