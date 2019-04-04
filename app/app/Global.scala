package app

import play.api.GlobalSettings
import scaldi.play.ScaldiSupport
import app.module.WebModule
import app.module.PersistenceModule

object Global extends ScaldiSupport {
  override def applicationModule = new WebModule :: new PersistenceModule
}
