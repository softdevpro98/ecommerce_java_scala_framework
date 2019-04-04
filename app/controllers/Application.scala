package controllers

import java.nio.file.Files
import java.nio.file.Paths
import java.util.Properties
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import javax.persistence.Entity
import models.Country
import models.Role
import models.User
import models.values.Address
import play.api.mvc.Action
import play.api.mvc.Controller
import play.Play
import scaldi.Injector
import scala.collection.JavaConversions.asScalaSet
import org.hibernate.SessionFactory
import scaldi.Injectable.inject
import services.UserService
import scala.util.Random
import services.RoleService
import services.RoleService
import utils.SecurityUtil
import play.api.Logger.logger
import play.api.Routes
import controllers.product.CategoryController

class Application(implicit inj: Injector) extends Controller {

  val userService = inject [UserService]
  val roleService = inject [RoleService]

  def javascriptRoutes = Action { implicit request =>
    import routes.javascript._
    Ok(Routes.javascriptRouter("jsRoutes")(
        controllers.product.routes.javascript.CategoryController.listCategories
    ))
  }

  def index = Action { implicit request =>
    Ok(views.html.index("Logged in Successfully"))
  }

  def userAdmin = Action { implicit request =>
    Ok(views.html.index("Logged in Successfully"))
  }

  def deleteUser(userId: Long) = Action { implicit request =>
    val user = userService.findById(userId)
    user.foreach(userService.remove)
    Ok(views.html.index("Logged in Successfully"))
  }

  // TODO add validations
  def makeUserAdmin(userId: Long) = Action { implicit request =>
    val user = userService.findById(userId)
    user.filterNot(SecurityUtil.userIsAdmin).foreach { user =>
      user.roles.add(roleService.findByName(Role.AdminRole).get)
      userService.update(user)
    }
    Ok(views.html.index("Logged in Successfully"))
  }
}
