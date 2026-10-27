package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.users

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.IntegrationTestBase
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users.User
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users.UserRepository
import java.time.OffsetDateTime

class UsersResourceTest : IntegrationTestBase() {

  @MockitoBean
  private lateinit var userRepository: UserRepository

  @Test
  fun `creates a user and returns 201`() {
    `when`(userRepository.insert(anyString(), anyString()))
      .thenReturn(User(1L, "alex", "alex@example.com", OffsetDateTime.parse("2026-01-01T10:00:00Z")))

    webTestClient.post()
      .uri("/users")
      .headers(setAuthorisation())
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue("""{"username": "alex",  "email": "alex@example.com"}""")
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("userId").isEqualTo(1)
      .jsonPath("username").isEqualTo("alex")
      .jsonPath("email").isEqualTo("alex@example.com")
  }
}
