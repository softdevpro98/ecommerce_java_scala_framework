package models.product.traits

import javax.persistence.Column

trait BasicDetails {

  @Column(nullable = false, length = 192)
  var name: String = _

  @Column(nullable = true, length = 255)
  var description: String = _

  @Column(nullable = true, length = 5000)
  var longDescription: String = _

}