package org.uhafactory.travle.mileage.event

data class MileageEvent(
        val type: EventType,
        val action: Action,
        val reviewId: String,
        val content: String,
        val attachedPhotoIds: List<String>?,
        val userId: String,
        val placeId: String
) {
    class Builder {
        private var type: EventType = EventType.REVIEW
        private var action: Action = Action.ADD
        private var reviewId: String = ""
        private var content: String = ""
        private var attachedPhotoIds: List<String>? = null
        private var userId: String = ""
        private var placeId: String = "USA"

        fun action(action: Action) = apply { this.action = action }
        fun reviewId(reviewId: String) = apply { this.reviewId = reviewId }
        fun content(content: String) = apply { this.content = content }
        fun attachedPhotoIds(attachedPhotoIds: List<String>? ) = apply { this.attachedPhotoIds = attachedPhotoIds }
        fun userId(userId: String) = apply { this.userId = userId }
        fun placeId(placeId: String) = apply { this.placeId = placeId }

        fun build() = MileageEvent(
                type = type,
                action = action,
                reviewId = reviewId,
                content = content,
                attachedPhotoIds = attachedPhotoIds,
                userId = userId,
                placeId = placeId
        )
    }
}

enum class EventType {
    REVIEW
}

enum class Action {
    ADD, MOD, DELETE
}