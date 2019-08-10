package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService

@ExtendWith(MockitoExtension::class)
internal class MileageRuleDeleteTest {
    @InjectMocks
    private lateinit var rule: MileageRuleDelete

    @Mock
    private lateinit var mileageHistoryService: MileageEventHistoryService

    @Test
    fun testCanApply() {
        assertThat(rule.canApply(MileageEvent.Builder().action(Action.DELETE).build())).isEqualTo(true)
        assertThat(rule.canApply(MileageEvent.Builder().action(Action.ADD).build())).isEqualTo(false)
    }

    @Test
    fun testCalculate() {
        val reviewId = "301"
        val target = MileageEvent.Builder().reviewId(reviewId).action(Action.DELETE).build()

        val accumulatedPoint = mapOf(Pair(RuleType.PHOTO, 2), Pair(RuleType.CONTENT, 1))
        given(mileageHistoryService.getPointsByReviewId(setOf(RuleType.PHOTO, RuleType.CONTENT, RuleType.FIRST_REVIEW), reviewId))
                .willReturn(accumulatedPoint)

        val result = rule.calculate(target).sortedBy { it.type }

        assertThat(result.size).isEqualTo(2)
        assertThat(result[0].type).isEqualTo(RuleType.CONTENT)
        assertThat(result[0].point).isEqualTo(-1)
        assertThat(result[1].type).isEqualTo(RuleType.PHOTO)
        assertThat(result[1].point).isEqualTo(-2)
    }
}