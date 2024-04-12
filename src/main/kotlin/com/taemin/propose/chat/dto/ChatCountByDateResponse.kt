package com.taemin.propose.chat.dto

import java.time.LocalDate

data class ChatCountByDateResponse(
    val date: LocalDate,
    val count: Int
)
