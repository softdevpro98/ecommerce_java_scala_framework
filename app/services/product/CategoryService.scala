package services
package product

import daos.product.CategoryDAO
import models.product.Category
import scaldi.Injectable.inject
import scaldi.Injector
import java.lang.Long
import scala.collection.immutable
import persistence.query.Order


class CategoryService(implicit injector: Injector) extends BaseService[Category, Long] {
  protected val dao = inject [CategoryDAO]
  
  override def findAll: immutable.Seq[Category] = {
    super.findAll(Order.asc(Category.Name))
  }
}