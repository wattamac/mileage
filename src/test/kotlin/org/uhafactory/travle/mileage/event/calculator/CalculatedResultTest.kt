package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CalculatedResultTest {
    @Test
    fun testTotal(){
        val result = CalculatedResult(listOf(Point(RuleType.CONTENT, 10), Point(RuleType.CONTENT, -1)))
        assertThat(result.totalPoint()).isEqualTo(9)
    }
}