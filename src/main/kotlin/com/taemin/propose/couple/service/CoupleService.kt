package com.taemin.propose.couple.service

import com.taemin.propose.couple.domain.Couple
import com.taemin.propose.couple.repository.CoupleRepository
import com.taemin.propose.user.domain.User
import com.taemin.propose.user.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class CoupleService(
    private val coupleRepository: CoupleRepository,
    private val userRepository: UserRepository
) {
    fun getCoupleByUserId(userId: String): Couple {
        return coupleRepository.findByUserId(userId)
    }

    fun initCouple(users: List<User>) : Couple {
        val couple = Couple.of(users, LocalDate.of(2022, 4, 29))
        return coupleRepository.save(couple)
    }
}