package daos.product

import models.product.Product
import daos.BaseDAO
import java.lang.Long
import models.product.Category
import org.hibernate.Session
import org.hibernate.criterion.Order
import java.util.{List => JList}

import scala.collection.JavaConversions.asScalaBuffer
class CategoryDAO extends BaseDAO[Category, Long]