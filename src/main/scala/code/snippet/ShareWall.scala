package code
package snippet

import code.model.MessageFactory
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.http.js.jquery.JqJsCmds.PrependHtml
import net.liftweb.util.ClearClearable
import net.liftweb.util.Helpers._

class ShareWall {

  var newMsg = ""

  private val renderMessages = SHtml.idMemoize{renderer =>
     ".msg" #> MessageFactory.getAll.map{ msg =>
       ".collection-item *" #> msg.text
     }
  }

  def processNewMsg(msg: String): JsCmd = {
    newMsg = msg
    SetValById("text-input", "") &
    PrependHtml("messages", <li class="collection-item msg">{newMsg}</li>)
  }

  def render = {
    ClearClearable andThen
    SHtml.makeFormsAjax andThen
    "#messages" #> renderMessages &
    "#text-input" #> SHtml.ajaxText(newMsg, processNewMsg)
  }

}