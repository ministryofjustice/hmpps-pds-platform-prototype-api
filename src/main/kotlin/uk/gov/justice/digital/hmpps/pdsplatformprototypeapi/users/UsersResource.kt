package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("isAuthenticated")
@RequestMapping("/users", produces = ["application/json"])
class UsersResource(private val userRepository: UserRepository) {

  @PostMapping(consumes = ["application/json"])
  @ResponseStatus(HttpStatus.CREATED)
  fun createUser(@Valid @RequestBody request: CreateUserRequest): User = userRepository.insert(
    username = request.username!!.trim(),
    email = request.email!!.trim(),
  )
}
