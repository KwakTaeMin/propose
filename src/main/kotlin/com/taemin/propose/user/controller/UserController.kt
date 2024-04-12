package com.taemin.propose.user.controller

import com.taemin.propose.user.domain.User
import com.taemin.propose.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    @GetMapping("/user/{name}")
    fun getUserByName(@PathVariable name: String) : ResponseEntity<User> {
        return ResponseEntity.ok(userService.getUserName(name))

    }
}