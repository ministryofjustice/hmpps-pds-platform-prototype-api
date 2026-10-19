package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@PreAuthorize("isAuthenticated")
@RequestMapping("/example", produce = ["application/json])
class ExampleResource {

    @getMapping("/time")
    fun getTime(): LocalDateTime = LocalDateTime.now()
}
