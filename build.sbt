name := "akka-http-first-api"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.10",
  "com.typesafe.akka" %% "akka-stream" % "2.5.23",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.10",
  "org.postgresql" % "postgresql" % "42.2.5",
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "org.slf4j" % "slf4j-nop" % "1.7.26",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)
