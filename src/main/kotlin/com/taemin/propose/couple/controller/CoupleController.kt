package com.taemin.propose.couple.controller

import com.taemin.propose.couple.domain.Couple
import com.taemin.propose.couple.service.CoupleService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CoupleController(private val coupleService: CoupleService) {
    @GetMapping("/couple/{userId}")
    fun getCoupleByUserId(@PathVariable userId: String) :ResponseEntity<Couple>{
        return ResponseEntity.ok(coupleService.getCoupleByUserId(userId))
    }
}