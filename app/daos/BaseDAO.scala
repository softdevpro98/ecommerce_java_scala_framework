package daos

import scala.collection.mutable.Buffer
import org.hibernate.Session
import java.lang.reflect.ParameterizedType
import scala.collection.JavaConversions.asScalaBuffer
import java.util.{List => JList}
import models.BaseEntity
import java.io.Serializable
import scala.collection.immutable
import persistence.query.Order
import org.hibernate.Criteria
import persistence.query.OrderBy
import persistence.query.Order
import org.hibernate.criterion.{Order => HibernateOrder}

abstract class BaseDAO[T <: BaseEntity, PK <: Serializable] {
  // Get the static methods in scope
  import BaseDAO._
  
  protected val persistentClass: Class[T] =
    getClass.getGenericSuperclass().asInstanceOf[ParameterizedType].
      getActualTypeArguments().apply(0).asInstanceOf[Class[T]]

  def findById(id: PK)(implicit s: Session): Option[T] = {
    Option(s.get(persistentClass, id).asInstanceOf[T]);
  }

  def findAll(ordering: OrderBy*)(implicit s: Session): immutable.Seq[T] = {
    val criteria = s.createCriteria(persistentClass)
    addOrders(criteria, ordering)
    criteria.list.asInstanceOf[JList[T]].toList
  }
  
  def removeById(id: PK)(implicit s: Session) = {
    s.delete(findById(id))
  }

  def remove(entity: T)(implicit s: Session) = {
    s.delete(entity)
  }

  def save(entity: T)(implicit s: Session): T = {
    s.save(entity)
    entity
  }

  def update(entity: T)(implicit s: Session): T = {
    s.update(entity)
    entity
  }
}

object BaseDAO {
  // The conversion from our Order instance to a hibernate Order instance
  // could have been done using implicits. However, the implicits make it 
  // very hard to determine what is going on without help from the IDEs 
  // (eclipse annotates the places where implicit conversions are taking place).
  private def toHibernateOrder(order: OrderBy) = {
    order match {
      case Order.asc(propertyName) => HibernateOrder.asc(propertyName)
      case Order.desc(propertyName) => HibernateOrder.desc(propertyName)
      case _ => throw new RuntimeException("invalid order type")
    }
  }
  
  protected def addOrders(criteria: Criteria, ordering: Seq[OrderBy]) = {
    ordering.foreach { order =>
	  val hibernateOrder = BaseDAO.toHibernateOrder(order)
      criteria.addOrder(hibernateOrder)
    }
  }
}