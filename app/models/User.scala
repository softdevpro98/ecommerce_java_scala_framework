package models

import javax.persistence._
import models.values.Address
import scala.collection.immutable.Set
import java.util.{Set => JSet}
import java.util.HashSet
import javax.persistence.Inheritance
import org.hibernate.FetchMode

@Entity
class User extends BaseEntity {

  @Column(nullable = false)
  var firstName: String = _

  @Column(nullable = false)
  var lastName: String = _

  @Column(nullable = false, unique = true)
  var email: String = _

  @Column(nullable = false)
  var password: String = _

  @Embedded
  var address: Address = _

  @ManyToMany(fetch = FetchType.EAGER)
  var roles: JSet[Role] = new HashSet

  override def toString = {
    """
      User[id: ${id},
      firstName: ${firstName}
      lastName: ${lastName}
      """
  }

  def fullName = {
    firstName + " " + lastName
  }
}

object User {
  val FirstName = "firstName"
  val LastName = "lastName"
  val Email = "email"
  val Password = "password"
}
