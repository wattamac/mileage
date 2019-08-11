package org.uhafactory.travle.mileage.event

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils
import org.springframework.util.StringUtils
import org.uhafactory.travle.mileage.event.calculator.RuleType

@Service
@Transactional(readOnly = true)
class MileageEventHistoryService(
        private val mileageEventHistoryRepository: MileageEventHistoryRepository
) {
    fun getPointsByReviewId(ruleTypes: Set<RuleType>, reviewId: String): Map<RuleType, Int> {
        if(CollectionUtils.isEmpty(ruleTypes) || StringUtils.isEmpty(reviewId)){
            return emptyMap()
        }
        val histories = mileageEventHistoryRepository.findByRuleTypesAndReviewId(ruleTypes, reviewId)

        return histories.groupingBy { it.ruleType }
                .fold(0) { sum, element -> sum + element.point }
                .filter { it.value != 0 }
    }

    fun isFirstReview(placeId: String): Boolean {
        val point = mileageEventHistoryRepository.findFirstReviewPoint(placeId)
        return point == 0
    }

    @Transactional
    fun save(toHistory: List<MileageEventHistory>) {
        mileageEventHistoryRepository.saveAll(toHistory)
    }
}