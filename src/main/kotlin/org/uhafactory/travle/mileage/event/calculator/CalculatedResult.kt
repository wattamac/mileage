package org.uhafactory.travle.mileage.event.calculator

class CalculatedResult(private val points: List<Point>) {
    fun totalPoint(): Int{
        return points.map { it.point }.sum()
    }

    fun getPoint() : List<Point> {
        return points
    }
}

data class Point(val type: RuleType, val point: Int)

enum class RuleType{
    CONTENT,
    PHOTO,
    FIRST_REVIEW
}