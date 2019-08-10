package org.uhafactory.travle.mileage.event.calculator

import org.springframework.stereotype.Component
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService

@Component
class MileageRuleDelete(
        private val mileageEventHistoryService: MileageEventHistoryService
) : CalculatorRule {
    override fun canApply(event: MileageEvent) = Action.DELETE == event.action

    override fun calculate(event: MileageEvent): List<Point> {
        val accumulatedMileage = mileageEventHistoryService.getPointsByReviewId(RuleType.allSet(), event.reviewId)
        return accumulatedMileage.map { Point(type = it.key, point = it.value * -1) }
                .toList()
    }

}