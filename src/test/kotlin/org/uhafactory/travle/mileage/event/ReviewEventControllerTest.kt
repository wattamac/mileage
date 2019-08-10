package org.uhafactory.travle.mileage.event

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import org.uhafactory.travle.RouterConfiguration

@ExtendWith(MockitoExtension::class)
internal class ReviewEventControllerTest {

    var mileageEventHandler = MileageEventHandler()

    lateinit var client: WebTestClient

    @BeforeEach
    fun setup() {
        client = WebTestClient.bindToRouterFunction(RouterConfiguration(mileageEventHandler).route()).build()
    }

    @Test
    fun testParsing() {
//        POST /events
//        {
//            "type": "REVIEW",
//            "action": "ADD", /* "MOD", "DELETE" */
//            "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
//            "content": "좋아요!",
//            "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
//            "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
//            "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
//        }
        val client = WebTestClient.bindToRouterFunction(RouterConfiguration(mileageEventHandler).route()).build()

        val event = MileageEvent(type = EventType.REVIEW,
                action = Action.ADD,
                reviewId = "1231",
                content = "좋아요",
                attachedPhotoIds = listOf("1", "2"),
                userId = "11",
                placeId = "placeId"
        )

        client.post()
                .uri("/events")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(event))
                .exchange()
                .expectStatus().is2xxSuccessful

    }
}