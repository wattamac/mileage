package org.uhafactory.travle.mileage.event.calculator

import org.springframework.stereotype.Component
import org.uhafactory.travle.mileage.event.MileageEvent

@Component
class MileageRuleDelete : CalculatorRule {
    override fun canApply(event: MileageEvent): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun calculate(event: MileageEvent): List<Point> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}