package org.uhafactory.travle.mileage.review.calculator

import org.uhafactory.travle.mileage.review.MileageEvent

interface CalculatorRule {
    fun canApply(event: MileageEvent): Boolean
    fun calculate(event: MileageEvent): List<Point>
}