package daos

import models.security.Token
import org.hibernate.Session
import org.hibernate.criterion.Restrictions
import java.lang.Long

class TokenDAO extends BaseDAO[Token, Long] {
  def findByUuid(uuid: String)(implicit session: Session): Option[Token] = {
    val c = session.createCriteria(classOf[Token])
    c.add(Restrictions.eq(Token.Uuid, uuid))
    Option(c.uniqueResult().asInstanceOf[Token])
  }
}