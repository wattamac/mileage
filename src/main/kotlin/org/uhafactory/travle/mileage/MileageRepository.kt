package org.uhafactory.travle.mileage

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MileageRepository: JpaRepository<Mileage, String>