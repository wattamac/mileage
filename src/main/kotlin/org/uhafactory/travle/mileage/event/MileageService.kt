package org.uhafactory.travle.mileage.event

import org.springframework.stereotype.Service
import org.uhafactory.travle.mileage.event.calculator.MileageCalculator

@Service
class MileageService(val calculator: MileageCalculator) {
    fun applyEvent(event: MileageEvent): MileageResult {
//        val calculateResult : CalculatedResult = calculator.calculate(event)
        return MileageResult()
    }
}
