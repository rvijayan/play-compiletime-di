package weather

import controllers.WeatherController
import play.api.ApplicationLoader.Context
import services.WeatherService

import play.api._
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.routing.Router
import router.Routes

class WeatherAppLoader extends ApplicationLoader {
  def load(context: Context) = {
    new AppComponents(context).application
  }
}

class AppComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
  with AhcWSComponents {

  lazy val weatherService = new WeatherService(wsClient)

  lazy val weatherController = new WeatherController(weatherService)

  lazy val router: Router = new Routes(httpErrorHandler,weatherController)
}