package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class UserRepository(private val jdbcTemplate: JdbcTemplate) {
  
  fun insert (username: String, email: String): User = jdbcTemplate.queryForObject(
    """
    INSERT INTO pds_platform_dev.users (username, email)
    VALUES (?,?)
    RETURNING user_id, username, email, created_at
    """.trimIndent(),
    rowMapper,
    username,
    email
  )!!

  private val rowMapper - RowMapper { rs, _ ->
    User(
      userID = rs.getLong("user_id"),
      username = rs.getString("username"),
      email = rs.getString("email"),
      createdAt = rs.getObject("created_at", OffsetDateTime::class.java)
    )
  }
}