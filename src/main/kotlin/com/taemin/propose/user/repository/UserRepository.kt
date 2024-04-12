package com.taemin.propose.user.repository

import com.taemin.propose.user.domain.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : MongoRepository<User, String> {
    fun findByIdIn(userIds: List<String>): List<User>
    fun findByName(name: String) : Optional<User>
}