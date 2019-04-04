package models.product

import javax.persistence.Entity
import javax.persistence.MappedSuperclass
import models.BaseEntity
import javax.persistence.Column
import java.math.BigDecimal
import javax.persistence.ManyToOne
import models.product.traits.BasicDetails

@Entity
class Sku extends BaseEntity with BasicDetails {

  @Column(nullable = false)
  var enabled: Boolean = true

  @Column(nullable = false)
  var availableQuantity: Int = 0

  @Column(nullable = false)
  var retailPrice: BigDecimal = _

  @Column(nullable = false)
  var salePrice: BigDecimal = _

  @ManyToOne(optional = false)
  var product: Product = _

}