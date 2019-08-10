package org.uhafactory.travle.mileage.event

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.uhafactory.travle.mileage.event.calculator.MileageCalculator

@ExtendWith(MockitoExtension::class)
internal class MileageServiceTest {
    @InjectMocks
    private lateinit var mileageService: MileageService

    @Mock
    lateinit var mileageCalculator: MileageCalculator

    @Test
    fun testApply() {
        val event = MileageEvent(type = EventType.REVIEW,
                action = Action.ADD,
                reviewId = "1231",
                content = "좋아요",
                attachedPhotoIds = listOf("1", "2"),
                userId = "11",
                placeId = "placeId"
        )

        val result: MileageResult = mileageService.applyEvent(event)

    }
}