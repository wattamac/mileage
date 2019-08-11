package org.uhafactory.travle.mileage

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService
import org.uhafactory.travle.mileage.event.calculator.MileageCalculator

@Service
@Transactional(readOnly = true)
class MileageService(
        val calculator: MileageCalculator,
        val mileageEventHistoryService: MileageEventHistoryService,
        val mileageRepository: MileageRepository
) {
    @Transactional
    fun applyEvent(event: MileageEvent){

        //lock
        val calculatedResult = calculator.calculate(event)
        if(!calculatedResult.hasPoint()){
            return
        }

        mileageEventHistoryService.save(calculatedResult.toHistory())
        mileageRepository.applyPoint(event.userId, calculatedResult.totalPoint())
        //release

    }

//    fun getCurrentMileage(userId: String) {
//        mileageRepository.findOne(userId)
//    }
}
