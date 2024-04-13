package com.taemin.propose.calender.repository

import com.taemin.propose.calender.domain.CoupleCalenderEvent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CoupleCalenderEventRepository : MongoRepository<CoupleCalenderEvent, String>