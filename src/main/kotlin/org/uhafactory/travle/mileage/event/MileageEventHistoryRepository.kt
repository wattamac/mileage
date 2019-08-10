package org.uhafactory.travle.mileage.event

import org.springframework.stereotype.Repository
import org.uhafactory.travle.mileage.event.calculator.RuleType

@Repository
class MileageEventHistoryRepository {
    fun findByInRuleTypeAndTargetId(ruleTypes: List<RuleType>, targetId: String): List<MileageEventHistory> {
        return listOf()
    }
}