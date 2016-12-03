package code
package snippet

import code.model.MessageFactory
import net.liftweb.http._
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.http.js.jquery.JqJsCmds.PrependHtml
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers._

import scala.xml.Text

class ShareWall {

  var newMsg = ""

  private val renderMessagesAsync = SHtml.idMemoize{renderer =>
     ".msg" #> MessageFactory.getAllAsync.map{ msg =>
       msg.map { m =>
         ".collection-item *" #> m.text
       }
     }
  }

  private val renderMessagesSync = SHtml.idMemoize{renderer =>
     ".msg" #> MessageFactory.getAll.map{ msg =>
         ".collection-item *" #> msg.text
     }
  }

  def processNewMsg(msg: String): JsCmd = {
    if(msg.isEmpty){
      S.error("empty-field", Text("You must type something"))
    }
    else {
      newMsg = msg
      SetValById("text-input", "") &
        PrependHtml("messages", <li class="collection-item msg">{newMsg}</li>)
    }
  }

  def render = {
    ClearClearable andThen
    SHtml.makeFormsAjax andThen
    "#messages" #> renderMessagesAsync &
    "#text-input" #> SHtml.ajaxText(newMsg, processNewMsg)
  }

}