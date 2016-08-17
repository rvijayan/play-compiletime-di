package controllers

import java.io.File

import org.scalatest.{TestData, MustMatchers}
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api._
import play.api.http.MimeTypes._
import play.api.http.{HeaderNames, HttpProtocol, Status}
import play.api.libs.json.Json
import play.api.test._
import org.mockito.Matchers._
import org.mockito.Mockito._
import services.WeatherService

import scala.concurrent.Future

class WeatherControllerSpec extends PlaySpec
  with MockitoSugar
  with PlayRunners
  with DefaultAwaitTimeout
  with FutureAwaits
  with HeaderNames
  with Status
  with HttpProtocol
  with ResultExtractors
  with Writeables
  with OneAppPerTest {

  private val service = mock[WeatherService]
  private val controller = new WeatherController(service)

  override def newAppForTest(testData: TestData) = {
    val appLoader = new FakeAppLoader
    val context = ApplicationLoader.createContext(
      new Environment(new File("."), ApplicationLoader.getClass.getClassLoader, Mode.Test)
    )
    appLoader.load(context)
  }

    "WeatherController" should {

    "get weather data by location" in {

      val data = Json.obj("weather" -> Json.obj("id" -> 100, "main" -> "Clouds"))

      when(service.weather(any[String])).thenReturn(Future.successful(data))

      val result = controller.get("SOME_LOCATION")(FakeRequest("GET", "/weather/:location"))

      status(result) mustEqual OK
      contentType(result) mustEqual Some(JSON)
      contentAsString(result) must include("Clouds")
    }
  }
}




