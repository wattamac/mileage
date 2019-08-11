package org.uhafactory.travle.mileage.event

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.uhafactory.travle.AbstractRepositoryTest
import org.uhafactory.travle.mileage.event.calculator.RuleType

internal class MileageEventHistoryRepositoryTest: AbstractRepositoryTest() {
    @Autowired
    private lateinit var repository: MileageEventHistoryRepository

    @Test
    fun testFindByRuleTypesAndReviewId() {
        val reviewId = "3"
        repository.save(MileageEventHistory.Builder().reviewId(reviewId).ruleType(RuleType.PHOTO).build())
        repository.save(MileageEventHistory.Builder().reviewId("other").ruleType(RuleType.PHOTO).build())
        repository.save(MileageEventHistory.Builder().reviewId("other").build())
        repository.save(MileageEventHistory.Builder().reviewId(reviewId).ruleType(RuleType.CONTENT).build())
        repository.save(MileageEventHistory.Builder().reviewId(reviewId).ruleType(RuleType.FIRST_REVIEW).build())

        val result = repository.findByRuleTypesAndReviewId(setOf(RuleType.PHOTO, RuleType.CONTENT), reviewId)

        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun testFirstReview() {
        val placeId = "11"
        repository.save(MileageEventHistory.Builder().placeId(placeId).ruleType(RuleType.FIRST_REVIEW).point(1).build())
        repository.save(MileageEventHistory.Builder().placeId("other").ruleType(RuleType.FIRST_REVIEW).build())
        repository.save(MileageEventHistory.Builder().placeId("other").build())
        repository.save(MileageEventHistory.Builder().placeId(placeId).ruleType(RuleType.CONTENT).point(1).build())

        assertThat(repository.findFirstReviewPoint(placeId)).isEqualTo(1)
    }

    @Test
    fun testFirstReview_2() {
        val placeId = "11"
        repository.save(MileageEventHistory.Builder().placeId(placeId).ruleType(RuleType.FIRST_REVIEW).point(1).build())
        repository.save(MileageEventHistory.Builder().placeId(placeId).ruleType(RuleType.FIRST_REVIEW).point(-1).build())

        assertThat(repository.findFirstReviewPoint(placeId)).isEqualTo(0)

        assertThat(repository.findFirstReviewPoint("1")).isEqualTo(0)
    }
}