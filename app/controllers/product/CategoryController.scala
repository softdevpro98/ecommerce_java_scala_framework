package controllers.product

import play.api.mvc.Controller
import scaldi.Injector
import play.api.libs.json.Json
import models.product.Category
import play.api.libs.json.JsValue
import utils.RequestUtil
import scaldi.Injectable._
import services.product.CategoryService
import play.api.mvc.Result
import play.api.http.MimeTypes
import play.api.mvc.Action
import play.api.mvc.Request
import play.api.mvc.AnyContent

class CategoryController(implicit inj: Injector) extends Controller {

  private val categoryService = inject[CategoryService]

  def viewAllCategories = Action { implicit request =>
    Ok(views.html.product.categoryList())
  }

  def listCategories = Action { implicit request =>
    val allCategories = categoryService.findAll.map(Category.toDTO(_))
    Ok(RequestUtil.toJsonString(allCategories)).as(JSON)
  }

  def viewCategoryForm(categoryId: Long) = Action { implicit request =>
    Ok(views.html.product.category(views.forms.product.categoryForm, categoryService.findAll))
  }

  def addCategoryForm = Action { implicit request =>
    Ok(views.html.product.category(views.forms.product.categoryForm, categoryService.findAll))
  }

  def addCategory = Action { implicit request =>
    import views.forms.product.categoryForm
    categoryForm.bindFromRequest.fold(formWithErrors => {
      Ok(views.html.product.category(formWithErrors, categoryService.findAll))
    }, categoryDTO => {
      val category = Category.fromDTO(categoryDTO)
      categoryService.save(category)
      Redirect(routes.CategoryController.viewAllCategories())
    })
  }

  def updateCategory = Action { implicit request =>
    Ok(RequestUtil.toJsonString(categoryService.findAll)).as(JSON)
  }

  def deleteCategory(categoryId: Long) = Action { implicit request =>
    Ok(RequestUtil.toJsonString(categoryService.findAll)).as(JSON)
  }
}
