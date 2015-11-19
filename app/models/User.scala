package models

/**
  * Created by dreamen on 19/11/15.
  */

object User{

}

case class User(username:String, password:String, isPremium:Boolean, balance:Int) {
  def checkPassword(password:String):Boolean = this.password == password
}
