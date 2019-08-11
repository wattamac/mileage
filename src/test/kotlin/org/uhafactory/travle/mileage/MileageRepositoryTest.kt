package org.uhafactory.travle.mileage

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
internal class MileageRepositoryTest {

    @Autowired
    private lateinit var em: EntityManager

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