package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("isAuthenticated")
@RequestMapping("/users", produces = ["application/json"])
class UsersResource(private val userRepository: UserRepository) {

  @GetMapping
  fun getUsers(): List<User> = userRepository.findAll()

  @PostMapping(consumes = ["application/json"])
  @ResponseStatus(HttpStatus.CREATED)
  fun createUser(@Valid @RequestBody request: CreateUserRequest): User = userRepository.insert(
    username = request.username!!.trim(),
    email = request.email!!.trim(),
  )

  @PutMapping("/{userId}", consumes = ["application/json"])
  fun updateUser(
    @PathVariable("userId") userId: Long,
    @Valid @RequestBody request: CreateUserRequest,
  ): User = userRepository.update(
    userId = userId,
    username = request.username!!.trim(),
    email = request.email!!.trim(),
  ) ?: throw UserNotFoundException(userId)

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteUser(@PathVariable("userId") userId: Long) {
    if (!userRepository.delete(userId)) throw UserNotFoundException(userId)
  }
}
