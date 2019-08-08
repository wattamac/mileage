package org.uhafactory.travle.mileage.review.calculator

import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils
import org.uhafactory.travle.mileage.review.MileageEvent
import org.uhafactory.travle.mileage.review.MileageRepository

@Component
class MileageRuleAdd : CalculatorRule{
    private lateinit var mileageRepository: MileageRepository

    override fun canApply(event: MileageEvent): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun calculate(event: MileageEvent): List<Point> {
        val result: MutableList<Point> = mutableListOf()
        if(StringUtils.hasLength(event.content)){
            result.add(Point(RuleType.CONTENT, 1))
        }
        if(!CollectionUtils.isEmpty(event.attachedPhotoIds)) {
            result.add(Point(RuleType.PHOTO, 1))
        }

        if(mileageRepository.isFirstReview(event.placeId)) {
            result.add(Point(RuleType.FIRST_REVIEW, 1))
        }
        return result
    }

}