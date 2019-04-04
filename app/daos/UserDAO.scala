package daos

import models.User
import java.lang.Long
import org.hibernate.Session
import org.hibernate.criterion.Restrictions

class UserDAO extends BaseDAO[User, Long] {
  def findByEmail(email: String)(implicit session: Session): Option[User] = {
    val c = session.createCriteria(classOf[User])
    c.add(Restrictions.eq(User.Email, email))
    Option(c.uniqueResult().asInstanceOf[User])
  }
}