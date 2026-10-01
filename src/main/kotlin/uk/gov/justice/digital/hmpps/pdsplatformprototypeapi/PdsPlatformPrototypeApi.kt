package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PdsPlatformPrototypeApi

fun main(args: Array<String>) {
  runApplication<PdsPlatformPrototypeApi>(*args)
}
