package views.forms

import views.html.helper.FieldElements

object FormHelpers {
  import views.html.helper.FieldConstructor
  implicit val bootstrapVerticalFormFieldConstructor = 
    FieldConstructor(views.html.forms.bootstrapVerticalFormFieldConstrutor.f)
}