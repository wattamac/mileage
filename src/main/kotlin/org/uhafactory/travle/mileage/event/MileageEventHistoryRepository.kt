package org.uhafactory.travle.mileage.event

import org.springframework.stereotype.Repository
import org.uhafactory.travle.mileage.event.calculator.RuleType

@Repository
class MileageEventHistoryRepository {
    fun findByInRuleTypeAndReviewId(ruleTypes: Set<RuleType>, reviewId: String): List<MileageEventHistory> {
        return listOf()
    }
}