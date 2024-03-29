package org.uhafactory.travle.mileage

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService
import org.uhafactory.travle.mileage.event.calculator.CalculatedResult
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

        applyMileage(calculatedResult)
        //release

    }

    private fun applyMileage(calculatedResult: CalculatedResult) {
        mileageEventHistoryService.save(calculatedResult.toHistory())
        val mileage = getMileage(calculatedResult.getUserId())
        mileage.point = mileage.point + calculatedResult.totalPoint()
        mileageRepository.save(mileage)
    }

    fun getMileage(userId: String): Mileage {
        return mileageRepository.findById(userId)
                .orElse(Mileage(userId = userId, point = 0))
    }

}
