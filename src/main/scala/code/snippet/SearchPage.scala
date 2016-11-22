package code.snippet

import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmd
import net.liftweb.util.Helpers._
import net.liftweb.util.PassThru

/**
  * Created by Riccardo Sirigu on 21/11/2016.
  */
class SearchPage {

  var searchResults = SHtml.idMemoize(renderer => PassThru)

  var searchTerm = ""

  def updateResults(term: String): JsCmd = {
    searchTerm = term
    println("Updating results")
    searchResults.setHtml
  }

  def render() ={
    "#search-field" #> SHtml.ajaxText(searchTerm, updateResults)
  }
}

class Results  {
  def render() = {

    println("Re rendering")

    "ul *" #> (1 to randomInt(10)).map(i => "li *" #> i)
  }
}