package org.uhafactory.travle.mileage.review.calculator

import org.springframework.stereotype.Component
import org.uhafactory.travle.mileage.review.MileageEvent
import kotlin.streams.toList

@Component
class MileageCalculator {

    private lateinit var rules: List<CalculatorRule>

    fun calculate(event: MileageEvent): CalculatedResult {

        val points = rules.filter { it.canApply(event) }
                .map { it.calculate(event) }
                .flatMap { it.stream().toList() }
                .toList()

        val result = CalculatedResult(points)
        return result
    }

}
