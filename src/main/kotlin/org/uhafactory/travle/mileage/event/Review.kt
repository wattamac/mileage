package org.uhafactory.travle.mileage.event

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "REV_POINT")
data class ReviewPoint(
        @Id
        val historyId: Long,
        val createdAt: Date
)