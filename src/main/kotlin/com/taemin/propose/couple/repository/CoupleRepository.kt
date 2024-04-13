package com.taemin.propose.couple.repository

import com.taemin.propose.couple.domain.Couple
import com.taemin.propose.user.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CoupleRepository : MongoRepository<Couple, String> {
    @Query("{'userIds': ?0}")
    fun findByUserId(userId: String): Couple

}