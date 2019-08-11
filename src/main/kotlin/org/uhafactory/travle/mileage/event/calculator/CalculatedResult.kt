package org.uhafactory.travle.mileage.event.calculator

import org.uhafactory.travle.mileage.event.MileageEvent
import org.uhafactory.travle.mileage.event.MileageEventHistory

class CalculatedResult(
        private val target: MileageEvent,
        private val points: List<Point>
) {
    fun totalPoint(): Int {
        return points.fold(0) {
            sum, element -> sum + element.point
        }
    }

    fun getPoint() = points

    fun hasPoint() = totalPoint() != 0
    fun toHistory(): List<MileageEventHistory> {
        return points.map {
            MileageEventHistory(target, it)
        }
    }
}

data class Point(val type: RuleType, val point: Int)

enum class RuleType {
    CONTENT,
    PHOTO,
    FIRST_REVIEW;

    companion object {
        fun allSet(): Set<RuleType> {
            return values().toSet()
        }
    }
}