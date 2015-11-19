package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by dreamen on 19/11/15.
  */
class Project extends Controller with Secured{
  def index = IsAuthenticated{ username => request =>
    Ok(views.html.index("Your new application is ready."))
  }
}
