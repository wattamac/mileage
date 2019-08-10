package org.uhafactory.travle.mileage.event.calculator

class CalculatedResult(private val points: List<Point>) {
    fun totalPoint(): Int {
        return points.fold(0) {
            sum, element -> sum + element.point
        }
    }

    fun getPoint(): List<Point> {
        return points
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