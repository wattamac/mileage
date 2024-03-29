package org.uhafactory.travle.mileage.event

import org.uhafactory.travle.mileage.event.calculator.Point
import org.uhafactory.travle.mileage.event.calculator.RuleType
import java.util.*
import javax.persistence.*

@Entity
@Table
data class MileageEventHistory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val reviewId: String,
        val placeId: String,
        @Enumerated(EnumType.STRING)
        val ruleType: RuleType,
        val point: Int,
        val action: Action,
        @Temporal(TemporalType.TIMESTAMP)
        var createdAt: Date?,
        val userId: String
) {
    constructor(event: MileageEvent, point: Point) :
            this(
                    reviewId = event.reviewId, placeId = event.placeId, action = event.action, userId = event.userId,
                    ruleType = point.type, point = point.point,
                    id = 0L, createdAt = null
            )

    @PrePersist
    fun prePersist() {
        this.createdAt = Date()
    }

    class Builder {
        private var id: Long = 0
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
        fun point(point: Int) = apply { this.point = point }
        fun action(action: Action) = apply { this.action = action }
        fun userId(userId: String) = apply { this.userId = userId }
        fun placeId(placeId: String) = apply { this.placeId = placeId }

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