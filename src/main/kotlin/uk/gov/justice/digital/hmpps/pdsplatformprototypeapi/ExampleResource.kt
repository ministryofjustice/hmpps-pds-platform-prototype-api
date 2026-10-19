package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.getMapping
import org.springframework.web.servlet.mvc.method.RequestMappingInfo
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDatetime

@RestControllerAdvice
@PreAuthorize("isAuthenticated")
@RequestMapping("/example", produce = ["application/json])
class ExampleResource {

    @getMapping("/time")
    fun getTime(): LocalDateTime = LocalDateTime.now()
}
