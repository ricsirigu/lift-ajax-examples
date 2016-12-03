package code.comet

import net.liftweb.actor.LiftActor
import net.liftweb.common.SimpleActor
import net.liftweb.http.{CometActor, CometListener, ListenerManager, RenderOut}

/**
  * Created by Riccardo Sirigu on 28/11/2016.
  */
class MessageComet extends CometActor with CometListener{
  override def render: RenderOut = ???

  override protected def registerWith: SimpleActor[Any] = MessageServer
}


object MessageServer extends LiftActor with ListenerManager{
  override protected def messageHandler: PartialFunction[Any, Unit] = ???

  override protected def createUpdate: Any = ???
}
