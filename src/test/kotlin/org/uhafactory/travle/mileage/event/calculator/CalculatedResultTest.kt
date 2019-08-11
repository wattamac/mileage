package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent

internal class CalculatedResultTest {
    @Test
    fun testTotal(){
        val event = MileageEvent.Builder().build()
        val result = CalculatedResult(event, listOf(Point(RuleType.CONTENT, 10), Point(RuleType.CONTENT, -1)))
        assertThat(result.totalPoint()).isEqualTo(9)
    }

    @Test
    fun testToHistory() {
        val reviewId = "1"
        val userId = "dddd"
        val action = Action.ADD
        val placeId = "placeId"

        val event = MileageEvent.Builder()
                .reviewId(reviewId)
                .userId(userId)
                .action(action)
                .placeId(placeId)
                .build()

        val result = CalculatedResult(
                event,
                listOf(Point(RuleType.CONTENT, 1), Point(RuleType.PHOTO, 2))
        ).toHistory()

        assertThat(result.size).isEqualTo(2)
        assertThat(result[0].reviewId).isEqualTo(reviewId)
        assertThat(result[0].userId).isEqualTo(userId)
        assertThat(result[0].action).isEqualTo(action)
        assertThat(result[0].ruleType).isEqualTo(RuleType.CONTENT)
        assertThat(result[0].point).isEqualTo(1)
        assertThat(result[1].ruleType).isEqualTo(RuleType.PHOTO)
        assertThat(result[1].point).isEqualTo(2)

    }
}