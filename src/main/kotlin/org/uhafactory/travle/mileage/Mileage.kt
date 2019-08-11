package org.uhafactory.travle.mileage

import java.util.*
import javax.persistence.*

@Entity
@Table
data class Mileage(
        @Id
        val userId: String,

        var point: Int,

        @Temporal(TemporalType.TIMESTAMP)
        var lastUpdatedAt: Date?
) {
    constructor(userId: String, point: Int): this(userId = userId, point = point, lastUpdatedAt = null)

    @PrePersist
    @PreUpdate
    fun preUpdate(){
        this.lastUpdatedAt = Date()
    }
}
