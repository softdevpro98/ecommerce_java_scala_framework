package models.product

import models.BaseEntity
import play.api.libs.json.Writes
import play.api.libs.json.Json
import dtos.product.CategoryDTO
import javax.persistence.Entity
import javax.persistence.Column
import javax.persistence.Access
import javax.persistence.AccessType
import java.util.LinkedHashSet
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.util.{Set => JSet}
import dtos.product.CategoryDTO
import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization._
import play.api.data.Form
import play.api.data._
import play.api.data.Forms._
import dtos.product.CategoryDTO
import models.IdentifierProperty
import dtos.product.CategoryDTO
import javax.persistence.Transient
import dtos.product.CategoryDTO
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
// This is a bit weird. When using nested annotations, the standard way, commented
// out below, doesn't work. Using the `new`keyword soleves the problem.
// This needs to be investigated further.
// @Table(uniqueConstraints = Array(@UniqueConstraint(columnNames = Array())))
class Category extends BaseEntity {
  
  def this(id: Option[Long]) = {
    this()
    this.id = id
  }

  @Column(nullable = false, length = 255)
  var name: String = _

  @Access(AccessType.PROPERTY)
  @ManyToOne(targetEntity = classOf[Category])
  var parentCategory: Option[Category] = None

  @OneToMany(mappedBy = "parentCategory")
  var childCategories: JSet[Category] = new LinkedHashSet


  def ancestors = {
	traverseAncestry(parentCategory)  
  }
  
  private def traverseAncestry(parentCategory: Option[Category], lst: List[Category] = Nil): Seq[Category] = {
    parentCategory  match {
      case Some(currentParentCategory) => traverseAncestry(currentParentCategory.parentCategory, currentParentCategory :: lst)
      case _ => lst
    }
    /*
    if(parentCategory.isDefined) {
      traverseAncestry(parentCategory.get.parentCategory, parentCategory.get :: lst)
    } else {
      lst
    }*/
  }
  
  protected def getParentCategory() = {
    parentCategory.getOrElse(null)
  }

  protected def setParentCategory(category: Category) {
    parentCategory = Option(category)
  }
}

object Category extends IdentifierProperty {
  val Name = "name"
  val ParentCategory = "parentCategory"
  val ChildCategories = "childCategories"
  
    
  def toDTO(category: Category, withAncestry: Boolean = false): CategoryDTO = {
    val dto = CategoryDTO(category.id, category.name, category.parentCategory.map(_.id.asInstanceOf[Long]))
    dto.parentCategoryName = category.parentCategory.map(_.name)
    dto.ancestry = if(withAncestry) { 
      category.ancestors.map(Category.toDTO(_)) 
    } else { 
      Nil
    }
    dto
  }
  
  def fromDTO(dto: CategoryDTO) = {
    val category = new Category
    category.name = dto.name
    category.parentCategory = dto.parentCategoryId.map(id => new Category(Some(id)))
    category
  }
  
}
