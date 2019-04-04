package models
package product

import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import models.BaseEntity
import javax.persistence.Basic
import javax.persistence.Column
import java.util.{Set => JSet}
import java.util.LinkedHashSet
import javax.persistence.OneToMany
import javax.persistence.CascadeType
import javax.persistence.Transient
import scala.collection.JavaConversions._
import models.product.traits.BasicDetails
import javax.persistence.OneToOne

@Entity
class Product extends BaseEntity with BasicDetails {

  @Column(nullable = false)
  var enabled: Boolean = true

  @Column(nullable = false)
  var featured: Boolean = false

  @OneToMany(mappedBy = "product", cascade = Array(CascadeType.REMOVE))
  var allSkus: JSet[Sku] = new LinkedHashSet

  def activeSkus = {
    allSkus.filter(_.enabled)
  }

  @OneToOne(optional = false, cascade = Array(CascadeType.REMOVE))
  var defaultSku: Sku = _

}