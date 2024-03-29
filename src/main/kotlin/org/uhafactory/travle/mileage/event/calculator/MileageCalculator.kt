package org.uhafactory.travle.mileage.event.calculator

import org.springframework.stereotype.Component
import org.uhafactory.travle.mileage.event.MileageEvent
import kotlin.streams.toList

@Component
class MileageCalculator(
        val rules: List<CalculatorRule>
) {

    fun calculate(event: MileageEvent): CalculatedResult {
        val points = rules.filter { it.canApply(event) }
                .map { it.calculate(event) }
                .flatMap { it.stream().toList() }
                .toList()

        return CalculatedResult(event, points)
    }

}
