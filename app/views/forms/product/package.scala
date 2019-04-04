package views.forms

import play.api.data._
import play.api.data.Forms._
import dtos.product.CategoryDTO
import daos.product.CategoryDAO
import dtos.product.CategoryDTO

package object product {
  val categoryForm = Form(
    mapping(
      "id" -> optional(longNumber(strict = true)),
      "name" -> text(minLength = 1, maxLength = 255),
      "parentCategoryId" -> optional(longNumber)
    ) (CategoryDTO.apply) (CategoryDTO.unapply)
  )
}

