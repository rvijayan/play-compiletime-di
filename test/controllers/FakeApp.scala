package controllers

import org.scalatest.mock.MockitoSugar
import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader, BuiltInComponentsFromContext}
import router.Routes
import services.WeatherService

class FakeApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context)  with MockitoSugar {

  private val service = mock[WeatherService]
  private val controller = new WeatherController(service)

  override def router = new Routes(httpErrorHandler, controller)
}

class FakeAppLoader extends ApplicationLoader {
  override def load(context: Context): Application =
    new FakeApplicationComponents(context).application
}