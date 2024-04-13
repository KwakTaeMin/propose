package com.taemin.propose.calender.repository

import com.taemin.propose.calender.domain.CoupleCalender
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CoupleCalenderRepository : MongoRepository<CoupleCalender, String>