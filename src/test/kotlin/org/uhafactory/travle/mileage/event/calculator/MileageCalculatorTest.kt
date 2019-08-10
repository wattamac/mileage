package org.uhafactory.travle.mileage.event.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.verify
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.Action
import org.uhafactory.travle.mileage.event.MileageEvent

@ExtendWith(MockitoExtension::class)
internal class MileageCalculatorTest {

    @Mock
    private lateinit var rule: CalculatorRule

    @Mock
    private lateinit var ruleOther: CalculatorRule

    @Test
    fun testCalculate() {
        var mileageCalculator = MileageCalculator(listOf(rule, ruleOther))

        val event = MileageEvent.Builder()
                .action(Action.ADD)
                .content("co")
                .attachedPhotoIds(listOf("1"))
                .build()

        val expectedPoint = listOf(Point(RuleType.CONTENT, 1))
        given(rule.canApply(event)).willReturn(true)
        given(rule.calculate(event)).willReturn(expectedPoint)
        given(ruleOther.canApply(event)).willReturn(false)

        val calculatedResult = mileageCalculator.calculate(event)

        assertThat(calculatedResult.getPoint()).isEqualTo(expectedPoint)
        verify(ruleOther, never()).calculate(event)

    }
}