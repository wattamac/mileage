package org.uhafactory.travle

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MileageApplication

fun main(args: Array<String>) {
	runApplication<MileageApplication>(*args)
}
