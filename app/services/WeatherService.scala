package services

import play.api.libs.json.JsValue
import play.api.libs.ws.WSClient

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

class WeatherService(ws: WSClient) {

  val URL = "http://api.openweathermap.org/data/2.5/weather"

  val APIKey = "787120d8face53883119f9c15790e852"

  def request(location: String) = ws
    .url(URL)
    .withHeaders("Accept" -> "application/json")
    .withQueryString(("q" -> location),("appid" -> APIKey))

  def weather(location: String): Future[JsValue] = {
    val response = request(location).get

    response.map { x =>
      x.json
    }
  }
}
