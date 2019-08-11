package org.uhafactory.travle

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@ExtendWith(SpringExtension::class)
@Transactional
class AbstractRepositoryTest {
    @Autowired
    protected lateinit var em: EntityManager
}