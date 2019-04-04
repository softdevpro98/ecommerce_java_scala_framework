package services

import models.security.Token
import scaldi.Injector
import scaldi.Injectable.inject
import daos.UserDAO
import daos.TokenDAO
import java.lang.Long

class TokenService(implicit injector: Injector) extends BaseService[Token, Long] {
  override val dao = inject [TokenDAO]

  def findByUuid(uuid: String): Option[Token] = {
    inTransaction{ implicit session =>
      dao.findByUuid(uuid)
    }
  }

  def removeByUuid(uuid: String) {
    inTransaction{ implicit session =>
      dao.findByUuid(uuid).foreach(token => dao.remove(token))
    }
  }

}