name := "play-compiletime-di"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  ws,
  "org.mockito"    %  "mockito-all" % "1.9.5" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)