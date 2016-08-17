package services

import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.Future

import scala.concurrent.ExecutionContext

class WeatherService(ws: WSClient) {

  val URL = "http://api.openweathermap.org/data/2.5/weather"

  val APIKey = "XXXXX"

  implicit val ec = ExecutionContext.Implicits.global

  def weather(location: String): Future[JsValue] = {
    val response = request(location).get

    response.map(_.json)
  }

  private def request(location: String) = ws
    .url(URL)
    .withHeaders("Accept" -> "application/json")
    .withQueryString(("q" -> location),("appid" -> APIKey))

}
