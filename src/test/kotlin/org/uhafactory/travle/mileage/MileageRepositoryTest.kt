package org.uhafactory.travle.mileage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.uhafactory.travle.AbstractRepositoryTest

internal class MileageRepositoryTest: AbstractRepositoryTest() {

    @Autowired
    private lateinit var mileageRepository: MileageRepository

    @Test
    fun testEntity() {
        val mileage = Mileage(userId = "userId", point = 3)

        mileageRepository.save(mileage)
        em.flush()
        em.clear()

        val result = mileageRepository.findById("userId")
        assertThat(result.get().point).isEqualTo(3)
        assertThat(result.get().lastUpdatedAt).isNotNull()
    }
}