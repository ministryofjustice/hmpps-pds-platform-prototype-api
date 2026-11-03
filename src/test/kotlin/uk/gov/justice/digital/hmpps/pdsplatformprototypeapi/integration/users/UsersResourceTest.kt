package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.users

import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
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

  @Test
  fun `updates a user and returns 200`() {
    `when`(userRepository.update(anyLong(), anyString(), anyString()))
      .thenReturn(User(1L, "alex2", "alex2@example.com", OffsetDateTime.parse("2026-01-01T10:00:00Z")))

    webTestClient.put()
      .uri("/users/1")
      .headers(setAuthorisation())
      .contentType(MediaType.APPLICATION_JSON)
      .bodyValue("""{"username": "alex2", "email": "alex2@example.com"}""")
      .exchange()
      .expectStatus().isOk
      .expectBody()
      .jsonPath("username").isEqualTo("alex2")
  }

  @Test
  fun `deletes a user and returns 204`() {
    `when`(userRepository.delete(anyLong())).thenReturn(true)

    webTestClient.delete()
      .uri("/users/1")
      .headers(setAuthorisation())
      .exchange()
      .expectStatus().isNoContent
  }

  @Test
  fun `returns 404 when deleting a user that does not exist`() {
    `when`(userRepository.delete(anyLong())).thenReturn(false)

    webTestClient.delete()
      .uri("/users/99")
      .headers(setAuthorisation())
      .exchange()
      .expectStatus().isNotFound
  }
}
