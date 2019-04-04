package services

import models.BaseEntity
import daos.BaseDAO
import org.hibernate.Session
import scaldi.Injectable
import org.hibernate.SessionFactory
import scaldi.Injectable.inject
import org.hibernate.Transaction
import scaldi.Injector
import java.io.Serializable
import scala.collection.immutable
import persistence.query.Order
import persistence.query.OrderBy

abstract class BaseService[T <: BaseEntity, PK <: Serializable](implicit inj: Injector) {
  protected def dao: BaseDAO[T, PK];

  protected val sessionFactory = inject [SessionFactory]

  def inTransaction[RES](func: Session => RES): RES = {
    implicit var s: Session = null;
    var tx: Transaction = null;
    try {
      s = sessionFactory.openSession()
      tx = s.beginTransaction()
      val result = func(s)
      tx.commit();
      result;
    } catch {
      case ex: Throwable => {
        try {
          if (tx != null && tx.isActive()) {
            tx.rollback();
          }
        } catch {
          case rollbakcException: Throwable => rollbakcException.printStackTrace();
        }
        throw new RuntimeException(ex)
      }
    } finally {
      if(s != null) {
        s.close();
      }
    }
  }

  def findById(id: PK): Option[T] = {
    inTransaction { implicit session =>
      dao.findById(id)
    }
  }

  def findAll(ordering: OrderBy*): immutable.Seq[T] = {
    inTransaction { implicit session =>
      dao.findAll(ordering: _*)
    }
  }
  
  // No-arg version, to avoid having to specify parentheses all the time when there is
  // no ordering applied 
  def findAll: immutable.Seq[T] = {
    inTransaction { implicit session =>
      dao.findAll()
    }
  }

  def removeById(id: PK) = {
    inTransaction { implicit session =>
      dao.removeById(id)
    }
  }

  def remove(entity: T) = {
    inTransaction { implicit session =>
      dao.remove(entity)
    }
  }

  def save(entity: T): T = {
    inTransaction { implicit session =>
      dao.save(entity)
    }
  }

  def update(entity: T): T = {
    inTransaction { implicit session =>
      dao.update(entity)
    }
  }
}
