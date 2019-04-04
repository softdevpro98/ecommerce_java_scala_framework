package app.module

import scaldi.Module
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import java.util.Properties
import org.hibernate.cfg.Configuration
import java.nio.file.Files
import java.nio.file.Paths
import models.Country
import models.Role
import models.values.Address
import models.User
import models.product.Product
import org.hibernate.SessionFactory
import play.Play
import models.security.Token
import models.product.Sku
import models.product.Category

class PersistenceModule extends Module {

  val sessionFactory = buildSessionFactory()

  bind[SessionFactory] to sessionFactory

  private def buildSessionFactory() = {
    val configuration = new Configuration
    configuration.setProperties(getHibernateProperties)
    addAnnotatedClasses(configuration)

    val srb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties);
    configuration.buildSessionFactory(srb.build());
  }

  private def getHibernateProperties = {
    val properties = new Properties()
    val is = Play.application().resourceAsStream("hibernate.properties")
    properties.load(is)
    is.close()
    properties
  }

  private def addAnnotatedClasses(configuration: Configuration) = {
    configuration.addAnnotatedClass(classOf[User])
    configuration.addAnnotatedClass(classOf[Role])
    configuration.addAnnotatedClass(classOf[Address])
    configuration.addAnnotatedClass(classOf[Country])
    configuration.addAnnotatedClass(classOf[Token])
    configuration.addAnnotatedClass(classOf[Product])
    configuration.addAnnotatedClass(classOf[Sku])
    configuration.addAnnotatedClass(classOf[Category])
  }
}