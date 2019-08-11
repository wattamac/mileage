package org.uhafactory.travle.mileage.event.calculator

import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService

@Component
class MileageRuleAdd : CalculatorRule {
    private lateinit var mileageEventHistoryService: MileageEventHistoryService

    override fun canApply(event: MileageEvent) = Action.ADD == event.action

    override fun calculate(event: MileageEvent): List<Point> {
        val result: MutableList<Point> = mutableListOf()
        if (StringUtils.hasLength(event.content)) {
            result.add(Point(RuleType.CONTENT, 1))
        }
        if (!CollectionUtils.isEmpty(event.attachedPhotoIds)) {
            result.add(Point(RuleType.PHOTO, 1))
        }

        if (mileageEventHistoryService.isFirstReview(event.placeId)) {
            result.add(Point(RuleType.FIRST_REVIEW, 1))
        }
        return result
    }

}