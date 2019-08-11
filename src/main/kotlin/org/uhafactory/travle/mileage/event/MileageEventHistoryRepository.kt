package org.uhafactory.travle.mileage.event

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.uhafactory.travle.mileage.event.calculator.RuleType

interface MileageEventHistoryRepository : JpaRepository<MileageEventHistory, Long>, MileageEventHistoryRepositoryCustom

interface MileageEventHistoryRepositoryCustom {
    fun findByRuleTypesAndReviewId(ruleTypes: Set<RuleType>, reviewId: String): List<MileageEventHistory>
    fun findFirstReviewPoint(placeId: String): Int
}

class MileageEventHistoryRepositoryImpl :
        QuerydslRepositorySupport(MileageEventHistory::class.java),
        MileageEventHistoryRepositoryCustom {

    private val mileageEventHistory = QMileageEventHistory.mileageEventHistory

    override fun findByRuleTypesAndReviewId(ruleTypes: Set<RuleType>, reviewId: String): List<MileageEventHistory> {
        return from(mileageEventHistory)
                .where(
                        mileageEventHistory.reviewId.eq(reviewId)
                        .and(mileageEventHistory.ruleType.`in`(ruleTypes))
                ).fetch()
    }

    override fun findFirstReviewPoint(placeId: String): Int {
        return from(mileageEventHistory)
                .where(mileageEventHistory.placeId.eq(placeId)
                        .and(mileageEventHistory.ruleType.eq(RuleType.FIRST_REVIEW)))
                .select(mileageEventHistory.point.sum().coalesce(0))
                .fetchFirst()
    }

}