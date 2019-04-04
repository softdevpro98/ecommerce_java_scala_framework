package services
package product

import scaldi.Injector
import daos.product.ProductDAO
import scaldi.Injectable.inject
import models.product.Sku
import daos.product.SkuDAO
import java.lang.Long

class SkuService(implicit injector: Injector) extends BaseService[Sku, Long] {
  protected val dao = inject [SkuDAO]
}