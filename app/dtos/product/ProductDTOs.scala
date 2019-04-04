package dtos.product

import play.api.libs.json.Writes
import play.api.libs.json.Json

case class CategoryDTO(id: Option[Long], name: String, parentCategoryId: Option[Long]) {
  // Sometimes we might need the ancestry to clarify each category. Since it is possible 
  // for multiple categories to have the same name.
  var ancestry: Seq[CategoryDTO] = Nil
  var parentCategoryName: Option[String] = None
}