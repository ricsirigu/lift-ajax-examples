package code.model

import java.util.Date

import net.liftweb.actor.LAFuture

/**
  * Created by Riccardo Sirigu on 23/10/2016.
  */
case class Message(text: String, timestamp: String)

object MessageFactory{

  val messages = List("Hi, how are you", "Just chillin..", "Learning new things", "Reading a good book")

  def getAll: Seq[Message] = {
    Thread.sleep(3000)
    messages.map(msg => Message(msg, new Date().toString))
  }

  def getAllAsync: LAFuture[Seq[Message]] = {
    LAFuture.build{
      Thread.sleep(3000)
      getAll
    }
  }
}
