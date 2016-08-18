package controllers

import java.io.File
import org.scalatest.mock.MockitoSugar
import play.api.ApplicationLoader.Context
import play.api._
import router.Routes
import services.WeatherService

object FakeApp {
  val app = {
    val appLoader = new FakeAppLoader
    val context = ApplicationLoader.createContext(
      new Environment(new File("."), ApplicationLoader.getClass.getClassLoader, Mode.Test)
    )
    appLoader.load(context)
  }

  class FakeApplicationComponents(context: Context) extends BuiltInComponentsFromContext(context)  with MockitoSugar {

    private val service = mock[WeatherService]
    private val controller = new WeatherController(service)

    override def router = new Routes(httpErrorHandler, controller)
  }

  class FakeAppLoader extends ApplicationLoader {
    override def load(context: Context): Application =
      new FakeApplicationComponents(context).application
  }
}

