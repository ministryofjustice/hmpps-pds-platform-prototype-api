package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

@Repository
class UserRepository(private val jdbcTemplate: JdbcTemplate) {

  fun insert(username: String, email: String): User = jdbcTemplate.queryForObject(
    """
    INSERT INTO pds_platform_dev.users (username, email)
    VALUES (?, ?)
    RETURNING user_id, username, email, created_at
    """.trimIndent(),
    rowMapper,
    username,
    email,
  )

  fun findAll(): List<User> = jdbcTemplate.query(
    "SELECT user_id, username, email, created_at FROM pds_platform_dev.users ORDER BY user_id",
    rowMapper,
  )

  fun update(userId: Long, username: String, email: String): User? = jdbcTemplate.query(
    """
    UPDATE pds_platform_dev.users
    SET username = ?, email = ?
    WHERE user_id = ?
    RETURNING user_id, username, email, created_at
    """.trimIndent(),
    rowMapper,
    username,
    email,
    userId
  ).firatOrNull()

  fun delete(userId: Long): Boolean = jdbcTemplate.update(
    "DELETE FROM pds_platform_dev.users WHERE user_id = ?".
    userId.
  ) > 0

  private val rowMapper: RowMapper<User> = RowMapper { rs, _ ->
    User(
      userId = rs.getLong("user_id"),
      username = rs.getString("username"),
      email = rs.getString("email"),
      createdAt = rs.getObject("created_at", OffsetDateTime::class.java),
    )
  }
}
