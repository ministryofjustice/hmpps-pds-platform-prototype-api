package uk.gov.justice.digital.hmpps.pdsplatformprototypeapi.users

class UserNotFoundException(userId: Long) : RuntimeException("User with id $userId not found")
