package org.uhafactory.travle.mileage.event

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.calculator.RuleType

@ExtendWith(MockitoExtension::class)
internal class MileageEventHistoryServiceTest {
    @InjectMocks
    private lateinit var service: MileageEventHistoryService

    @Mock
    private lateinit var repository: MileageEventHistoryRepository

    @Test
    fun testGetPointsByReviewId() {
        val reviewId = "1231"
        val ruleTypes = setOf(RuleType.CONTENT, RuleType.PHOTO)
        val histories = listOf(
                MileageEventHistory.Builder().ruleType(RuleType.PHOTO).point(1).build(),
                MileageEventHistory.Builder().ruleType(RuleType.CONTENT).point(-2).build(),
                MileageEventHistory.Builder().ruleType(RuleType.CONTENT).point(2).build()
        )
        given(repository.findByRuleTypesAndReviewId(ruleTypes, reviewId))
                .willReturn(histories)

        val result = service.getPointsByReviewId(ruleTypes, reviewId)

        assertThat(result.size).isEqualTo(1)
        assertThat(result[RuleType.PHOTO]).isEqualTo(1)
    }

    @Test
    fun testGetPointsByReviewId_emptyRequest() {
        val result = service.getPointsByReviewId(emptySet(), "reviewId")

        verify(repository, never()).findByRuleTypesAndReviewId(any(), any())
        assertThat(result.size).isEqualTo(0)
    }

    private fun <T> any(): T {
        return Mockito.any()
    }
}