package org.uhafactory.travle.mileage

import org.slf4j.LoggerFactory

interface Log {
    fun logger() = LoggerFactory.getLogger(this.javaClass)
}