package controllers

import play.api.mvc.{Action, Controller}
import services.WeatherService

import scala.concurrent.ExecutionContext

class WeatherController(ws: WeatherService) extends Controller {
  implicit val ec = ExecutionContext.Implicits.global

  def get(location: String) = Action.async { implicit request =>

    val weather = ws.weather(location)

    weather.map { x =>
      Ok(x)
    }.recover { case _ => NotFound }
  }
}
