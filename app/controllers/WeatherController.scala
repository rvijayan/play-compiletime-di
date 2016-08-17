package controllers

import play.api.mvc.{Action, Controller}
import services.WeatherService

import scala.concurrent.ExecutionContext.Implicits.global

class WeatherController(ws: WeatherService) extends Controller {

  def get(location: String) = Action.async { implicit request =>

    val weather = ws.weather(location)

    weather.map { x =>
      Ok(x)
    }.recover { case _ => NotFound }
  }
}
