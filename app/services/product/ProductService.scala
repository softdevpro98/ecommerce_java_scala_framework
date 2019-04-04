package services
package product

import scaldi.Injector
import daos.product.ProductDAO
import scaldi.Injectable.inject
import java.lang.Long
import models.product.Product

class ProductService(implicit injector: Injector) extends BaseService[Product, Long] {
  protected val dao = inject [ProductDAO]
}