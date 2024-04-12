package com.taemin.propose.user.service

import com.taemin.propose.user.domain.User
import com.taemin.propose.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun initUser() : List<User> {
        val taeminUser = User.createTaemin()
        val hyunjooUser = User.createHyunjoo()
        return userRepository.saveAll(arrayListOf(taeminUser,hyunjooUser))
    }

    fun getUserName(name: String): User {
        return userRepository.findByName(name).orElseThrow()
    }

}
