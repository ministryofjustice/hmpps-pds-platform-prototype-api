package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.users

import org.junit.jupiter.api.Test
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.Mockito.`when`
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.IntegrationTestBase
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.users.User
import uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.integration.users.UserRepository
import java.time.OffsetDateTime

class UserResourceTest : IntegrationTestBase() {
  
  @MockitoBean
  private lateinit var userRepository : UserRepository
  
  @Test
  fun `creates a user and returns 201`() {
    `when`(userRepository.insert(anyString(), anyString()))
      .thenReturn(User(1L, "alex", "alex@example.com", OffsetDateTime.parse("2026-01-01T10:00:00Z")))

    webTestClient.post()
      .uri("/users")
      .headers(setAuthorisation())
      .conectType(MediaType.APPLICATION_JSON)
      .bodyValue("""{"username": "alex",  "email": "alex@example.com"}""")
      .exchange()
      .expectStatus().isCreated
      .expectBody()
      .jsonPath("userID").isEqualto(1)
      .jsonPath("username").isEqualto("alex")
      jsonPath("email").isEqualto("alex@example.com")
  }


}
