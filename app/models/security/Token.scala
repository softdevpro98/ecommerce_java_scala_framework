package models.security

import models.BaseEntity
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Temporal
import javax.persistence.TemporalType
import org.joda.time.DateTime

@Entity
class Token extends BaseEntity {
  @Column(nullable = false, unique = true, updatable = false)
  var uuid: String = _

  @Column(nullable = false)
  var email: String = _

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  var creationTime: Date = new Date

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  var expirationTime: Date = _

  @Column(nullable = false)
  var isSignUpToken: Boolean = false
}

object Token {
  val Uuid = "uuid"
  val Email = "email"
}
