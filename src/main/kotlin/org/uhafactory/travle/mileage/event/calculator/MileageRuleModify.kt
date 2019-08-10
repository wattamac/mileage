package org.uhafactory.travle.mileage.event.calculator

import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService

@Component
class MileageRuleModify(
        private val mileageEventHistoryService: MileageEventHistoryService
) : CalculatorRule {
    override fun canApply(event: MileageEvent) = Action.MOD == event.action


    override fun calculate(event: MileageEvent): List<Point> {
        val accumulatedTypes = mileageEventHistoryService.getPointsByReviewId(
                setOf(RuleType.CONTENT, RuleType.PHOTO), event.reviewId).keys
        if (isDeletePhoto(event, accumulatedTypes)) {
            return listOf(Point(RuleType.PHOTO, -1))
        }
        if (isAddedPhoto(event, accumulatedTypes)) {
            return listOf(Point(RuleType.PHOTO, 1))
        }
        return emptyList()
    }

    private fun isAddedPhoto(event: MileageEvent, accumulatedTypes: Set<RuleType>): Boolean {
        return !CollectionUtils.isEmpty(event.attachedPhotoIds) && !accumulatedTypes.contains(RuleType.PHOTO)
    }

    private fun isDeletePhoto(event: MileageEvent, accumulatedTypes: Set<RuleType>): Boolean {
        return CollectionUtils.isEmpty(event.attachedPhotoIds)
                && accumulatedTypes.containsAll(setOf(RuleType.CONTENT, RuleType.PHOTO))
    }
}