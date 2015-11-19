package controllers

import play.api.data.Form
import play.api.mvc._
import play.api.data.Forms._

import models._
import views._

/**
  * Created by dreamen on 19/11/15.
  */
class Login extends Controller {

  val loginForm = Form(
    tuple(
      "email" -> text,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => User.find(email).isDefined
    })
  )

  def login = Action{ implicit request =>
    Ok(html.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest().fold(
      formWithErrors => BadRequest(html.login(formWithErrors)),
      user => Redirect(routes.Admin.index).withSession("email" -> user._1)
    )
  }

  def logout = Action{ implicit request =>
    Redirect(routes.Login.login()).withNewSession.flashing(
      "success" -> "You have been logged out"
    )
  }

}

trait Secured {

  private def username(request: RequestHeader) = request.session.get("email")

  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Login.login())

  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) {
    user =>
      Action(request => f(user)(request))
  }

  def IsMemberOf(project: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
    //Project.isMember(project, user)
    if(true){
      f(user)(request)
    }else{
      Results.Forbidden
    }
  }

  def IsOwnerOf(task: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
    //Task.isOwner(task, user)
    if(true){
      f(user)(request)
    }else{
      Results.Forbidden
    }
  }
}
