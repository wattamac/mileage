package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions
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
internal class MileageRuleAddTest {
    @InjectMocks
    private lateinit var rule: MileageRuleAdd

    @Mock
    private lateinit var mileageEventHistoryService: MileageEventHistoryService

    @Test
    fun testCanApply() {
        assertThat(rule.canApply(MileageEvent.Builder()
                .action(Action.MOD).build())).isEqualTo(false)
        assertThat(rule.canApply(MileageEvent.Builder()
                .action(Action.ADD).build())).isEqualTo(true)
    }

    @Test
    fun calculate_content_photo() {
        val event = MileageEvent.Builder()
                .content("co")
                .attachedPhotoIds(listOf("1"))
                .build()

        val points = rule.calculate(event)
        assertThat(points[0].type).isEqualTo(RuleType.CONTENT)
        assertThat(points[0].point).isEqualTo(1)
        assertThat(points[1].type).isEqualTo(RuleType.PHOTO)
        assertThat(points[1].point).isEqualTo(1)
    }

    @Test
    fun calculate_content() {
        val event = MileageEvent.Builder()
                .content("co")
                .attachedPhotoIds(null)
                .build()

        val points = rule.calculate(event)
        assertThat(points.size).isEqualTo(1)
        assertThat(points[0].type).isEqualTo(RuleType.CONTENT)
        assertThat(points[0].point).isEqualTo(1)
    }

    @Test
    fun calculate_fistReview() {
        val placeId = "place"
        val event = MileageEvent.Builder()
                .placeId(placeId)
                .build()

        given(mileageEventHistoryService.isFirstReview(placeId)).willReturn(true)

        val points = rule.calculate(event)

        Assertions.assertThat(points.size).isEqualTo(1)
        Assertions.assertThat(points[0].type).isEqualTo(RuleType.FIRST_REVIEW)
        Assertions.assertThat(points[0].point).isEqualTo(1)
    }
}