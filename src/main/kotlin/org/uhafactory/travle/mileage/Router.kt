package org.uhafactory.travle.mileage

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import org.uhafactory.travle.mileage.review.MileageEventHandler

@Configuration
class RouterConfiguration(val mileageEventHandler: MileageEventHandler) {
    @Bean
    fun route() = router {
        GET("/test") { ServerResponse.ok().body(fromObject(arrayOf(1, 2, 3))) }
        (accept(MediaType.APPLICATION_JSON)).nest{
            POST("/events", mileageEventHandler::handleEvent)
        }
    }
}