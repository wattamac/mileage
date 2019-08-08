package org.uhafactory.travle.mileage.review.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.review.Action
import org.uhafactory.travle.mileage.review.MileageEvent

@ExtendWith(MockitoExtension::class)
internal class MileageCalculatorTest {
    @InjectMocks
    private lateinit var mileageCalculator: MileageCalculator


    @Test
    fun calculate_Add_content_photo() {
        val event = MileageEvent.Builder()
                .action(Action.ADD)
                .content("co")
                .attachedPhotoIds(listOf("1"))
                .build()

        val calculatedResult = mileageCalculator.calculate(event)
        assertThat(calculatedResult.totalPoint()).isEqualTo(2)

    }
}