package org.uhafactory.travle.mileage

import org.springframework.stereotype.Repository

@Repository
class MileageRepository {
    fun isFirstReview(placeId: String): Boolean {
        return true
    }

}