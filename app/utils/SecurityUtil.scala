package utils

import models.User
import models.Role.AdminRole

object SecurityUtil {
  def userIsAdmin(user: User) = {
    import scala.collection.JavaConversions.asScalaSet
    user.roles.find(_.name == AdminRole).isDefined
  }
}