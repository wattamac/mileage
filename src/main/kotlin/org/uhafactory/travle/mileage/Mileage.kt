package org.uhafactory.travle.mileage

import java.util.*
import javax.persistence.*

@Entity
@Table
data class UserMileage(
        @Id
        val userId: String,

        var point: Int,

        @Temporal(TemporalType.TIMESTAMP)
        var lastUpdatedAt: Date
)