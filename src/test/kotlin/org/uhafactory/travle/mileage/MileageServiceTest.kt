package org.uhafactory.travle.mileage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
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
import java.util.*

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
        val mileage = Mileage(userId = userId, point = 1)
        given(mileageRepository.findById(userId)).willReturn(Optional.of(mileage))
        mileageService.applyEvent(event)

        verify(mileageEventHistoryService).save(ArgumentMatchers.anyList())

        val argCaptor = ArgumentCaptor.forClass(Mileage::class.java)
        verify(mileageRepository).save(argCaptor.capture())

        val savedMileage = argCaptor.value
        assertThat(savedMileage.point).isEqualTo(4)
    }
}