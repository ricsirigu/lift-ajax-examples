package code.model

import java.util.Date

/**
  * Created by Riccardo Sirigu on 23/10/2016.
  */
case class Message(text: String, timestamp: String)

object MessageFactory{

  val messages = List("Hi, how are you", "Just chillin..", "Learning new things", "Reading a good book")

  def getAll: Seq[Message] = {
    messages.map(msg => Message(msg, new Date().toString))
  }
}
