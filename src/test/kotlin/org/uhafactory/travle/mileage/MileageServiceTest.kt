package org.uhafactory.travle.mileage

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistoryService
import org.uhafactory.travle.mileage.event.calculator.CalculatedResult
import org.uhafactory.travle.mileage.event.calculator.MileageCalculator
import org.uhafactory.travle.mileage.event.calculator.Point
import org.uhafactory.travle.mileage.event.calculator.RuleType

@ExtendWith(MockitoExtension::class)
internal class MileageServiceTest {
    @InjectMocks
    private lateinit var mileageService: MileageService

    @Mock
    private lateinit var calculator: MileageCalculator

    @Mock
    private lateinit var mileageEventHistoryService: MileageEventHistoryService

    @Mock
    private lateinit var mileageRepository: MileageRepository

    @Test
    fun testApply() {
        val userId = "userID"
        val point = 3
        val event = MileageEvent.Builder()
                .userId(userId)
                .build()

        val calculatedResult = CalculatedResult(event, listOf(Point(RuleType.FIRST_REVIEW, point)))
        given(calculator.calculate(event)).willReturn(calculatedResult)

        mileageService.applyEvent(event)

        verify(mileageEventHistoryService).save(ArgumentMatchers.anyList())
        verify(mileageRepository).applyPoint(userId, point)
    }
}