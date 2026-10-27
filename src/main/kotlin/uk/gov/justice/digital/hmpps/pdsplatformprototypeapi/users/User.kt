package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.OffsetDateTime;

data class CreateUserRequest(
  @field:NotBlank(message = "must not be blank")
  @field:Size(max = 100, message = "must be at most 100 characters")
  val username: String?,

  @field:NotBlank(message = "must not be blank")
  @field:Size(max = 255, message = "must be at most 255 characters")
  @field:Email(message = "must be a valid email address")
  val email: String?,
)

data class User(
  val userId: Lond
  val username: String,
  val email: String,
  val createdAt: OffsetDateTime, 
)
