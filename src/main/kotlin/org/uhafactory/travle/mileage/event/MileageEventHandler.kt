package org.uhafactory.travle.mileage.event

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.uhafactory.travle.Log
import reactor.core.publisher.Mono

@Component
class MileageEventHandler {
    companion object : Log

    fun handleEvent(request: ServerRequest): Mono<ServerResponse> {
        return request
                .bodyToMono(MileageEvent::class.java)
                .log()
                .flatMap {
                    ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).build()
//                            .body(BodyInserters.fromObject(it))
                }
    }
}
