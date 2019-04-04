package models

import javax.persistence.Embeddable
import javax.persistence.Entity

@Entity
class Country extends BaseEntity {
  var name: String = _
  var code: String = _

  override def equals(other: Any) = {
    if(other == null || !other.isInstanceOf[Country]) {
      false
    }
    val otherCountry = other.asInstanceOf[Country]
    otherCountry.code == this.code
  }

  override def hashCode = {
    if(code == null) 0 else code.hashCode
  }
}