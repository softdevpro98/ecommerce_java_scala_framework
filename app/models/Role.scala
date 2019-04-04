package models

import javax.persistence.Entity
import javax.persistence.Table

@Entity
class Role(roleId: Option[Long] = None, roleName: String = null) extends BaseEntity {
  def this() = {
    this(None)
  }

  id = roleId;
  var name: String = roleName
}

object Role {
  val Name = "name"

  val UserRole = "ROLE_USER"
  val AdminRole = "ROLE_ADMIN"
}