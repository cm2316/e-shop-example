package controllers

import play.api.mvc.Controller

/**
  * Created by dreamen on 19/11/15.
  */
class Admin extends Controller with Secured{
  def index = IsAuthenticated{ user => request =>
    Ok(views.html.index("Your new application is ready."))
  }
}
