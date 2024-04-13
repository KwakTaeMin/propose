package com.taemin.propose.calender.service

import com.taemin.propose.calender.domain.CoupleCalender
import com.taemin.propose.calender.domain.CoupleCalenderEvent
import com.taemin.propose.calender.repository.CoupleCalenderEventRepository
import com.taemin.propose.calender.repository.CoupleCalenderRepository
import com.taemin.propose.couple.domain.Couple
import com.taemin.propose.util.IcsFileReaderService
import org.springframework.stereotype.Service

@Service
class CoupleCalenderService(
    private val icsFileReaderService: IcsFileReaderService,
    private val coupleCalenderRepository: CoupleCalenderRepository,
    private val coupleCalenderEventRepository: CoupleCalenderEventRepository
) {
    fun createCalender(couple: Couple) : CoupleCalender {
        val coupleCalender = CoupleCalender.of(couple)
        return coupleCalenderRepository.save(coupleCalender)
    }

    fun init(coupleCalenderId: String) {
        val calender = icsFileReaderService.readIcsFile()
        val coupleCalenderEvents = CoupleCalenderEvent.of(calender, coupleCalenderId)
        coupleCalenderEventRepository.saveAll(coupleCalenderEvents)
    }
}