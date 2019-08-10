package org.uhafactory.travle.mileage.event

import org.uhafactory.travle.mileage.event.calculator.RuleType
import java.util.*

data class MileageEventHistory(
        val id: Long,
        val reviewId: String,
        val placeId: String,
        val ruleType: RuleType,
        val point: Int,
        val action: Action,
        val createdAt: Date,
        val userId: String
){
    class Builder {
        private var id: Long = 1
        private var ruleType = RuleType.CONTENT
        private var reviewId = "reviewId"
        private var placeId = "placeId"
        private var point = 0
        private var action = Action.ADD
        private var createdAt = Date()
        private var userId = "userID"

        fun id(id: Long) = apply { this.id = id }
        fun reviewId(reviewId: String) = apply { this.reviewId = reviewId }
        fun ruleType(ruleType: RuleType) = apply { this.ruleType = ruleType }
        fun point(point: Int ) = apply { this.point = point }
        fun action(action: Action ) = apply { this.action = action }
        fun userId(userId: String) = apply { this.userId = userId }

        fun build() = MileageEventHistory(
                id = id,
                reviewId = reviewId,
                placeId = placeId,
                ruleType = ruleType,
                point = point,
                action = action,
                createdAt = createdAt,
                userId = userId
        )
    }
}