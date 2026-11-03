package uk.gov.justice.digital.hmpps.pdsplatformprototype.users

class UserNotFoundException(userId: Long) : RuntimeException("User with id $userid not found")