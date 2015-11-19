package models

/**
  * Created by dreamen on 19/11/15.
  */

object User{
  val users = List(
    User("bob@gmail.com","pass",true,10),
    User("alic@gmail.com","pass",true,0),
    User("john@gmail.com","pass",false,10)
  )

  def find(username:String):Option[User] = users.filter(_.username == username).headOption
}

case class User(username:String, password:String, isPremium:Boolean, balance:Int) {
  def checkPassword(password:String):Boolean = this.password == password
}
