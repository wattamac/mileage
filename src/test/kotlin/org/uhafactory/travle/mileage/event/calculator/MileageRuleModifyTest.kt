package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.*

@ExtendWith(MockitoExtension::class)
internal class MileageRuleModifyTest {
    @InjectMocks
    private lateinit var mileageRule: MileageRuleModify

    @Mock
    private lateinit var mileageEventHistoryService: MileageEventHistoryService

    @Test
    fun testCanApply() {
        assertThat(mileageRule.canApply(MileageEvent.Builder()
                .action(Action.MOD).build())).isEqualTo(true)
        assertThat(mileageRule.canApply(MileageEvent.Builder()
                .action(Action.ADD).build())).isEqualTo(false)
    }

    @Test
    fun testCalculate_ADD_PHOTO() {
        val event = MileageEvent.Builder()
                .attachedPhotoIds(listOf("1"))
                .reviewId("reviewId")
                .build()

        val histories = mapOf(Pair(RuleType.CONTENT, 1))
        given(mileageEventHistoryService.getPointsByReviewId(setOf(RuleType.CONTENT, RuleType.PHOTO), event.reviewId)).willReturn(histories)
        val points = mileageRule.calculate(event)

        assertThat(points[0].type).isEqualTo(RuleType.PHOTO)
        assertThat(points[0].point).isEqualTo(1)
    }

    @Test
    fun testCalculate_DEL_PHOTO() {
        val event = MileageEvent.Builder()
                .reviewId("reviewId")
                .build()

        val histories = mapOf(Pair(RuleType.CONTENT, 1), Pair(RuleType.PHOTO, 1))
        given(mileageEventHistoryService.getPointsByReviewId(setOf(RuleType.CONTENT, RuleType.PHOTO), event.reviewId)).willReturn(histories)
        val points = mileageRule.calculate(event)

        assertThat(points[0].type).isEqualTo(RuleType.PHOTO)
        assertThat(points[0].point).isEqualTo(-1)
    }

    @Test
    fun testCalculate_DEL_PHOTO_NO_PREVIOUS_MILEAGE() {
        val event = MileageEvent.Builder()
                .reviewId("reviewId")
                .attachedPhotoIds(null)
                .build()

        val histories = mapOf(Pair(RuleType.CONTENT, 1))
        given(mileageEventHistoryService.getPointsByReviewId(setOf(RuleType.CONTENT, RuleType.PHOTO), event.reviewId)).willReturn(histories)
        val points = mileageRule.calculate(event)

        assertThat(points.size).isEqualTo(0)
    }

    @Test
    fun testCalculate_DEL_PHOTO_NO_CONTENT() {
        val event = MileageEvent.Builder()
                .reviewId("reviewId")
                .attachedPhotoIds(null)
                .build()

        val histories = emptyMap<RuleType, Int>()
        given(mileageEventHistoryService.getPointsByReviewId(setOf(RuleType.CONTENT, RuleType.PHOTO), event.reviewId)).willReturn(histories)
        val points = mileageRule.calculate(event)

        assertThat(points.size).isEqualTo(0)
    }
}