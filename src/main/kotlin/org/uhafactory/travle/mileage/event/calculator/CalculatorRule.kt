package org.uhafactory.travle.mileage.event.calculator

import org.uhafactory.travle.mileage.event.MileageEvent

interface CalculatorRule {
    fun canApply(event: MileageEvent): Boolean
    fun calculate(event: MileageEvent): List<Point>
}