package models

import javax.persistence.Id
import javax.persistence._
import scala.beans.BeanProperty
import org.hibernate.annotations.Type
import org.hibernate.Hibernate
import org.hibernate.`type`.LongType

@MappedSuperclass
@Access(AccessType.FIELD)
abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Access(AccessType.PROPERTY)
  @Type(`type` = "long")
  var id: Option[Long] = None

  @Version
  var version: Integer = 0

  def getId: java.lang.Long = {
    id.getOrElse(null).asInstanceOf[Long]
  }

  def setId(newId: java.lang.Long) {
    id = Option(newId)
  }

  override def equals(other: Any) = {
    if(other == null || other.getClass != this.getClass) {
      false
    }

    val otherEntity = other.asInstanceOf[BaseEntity];
    otherEntity.id == this.id
  }

  override def hashCode = {
    if(id == null) 0 else id.hashCode
  }

}

trait IdentifierProperty {
  val Id = "id"	
}
