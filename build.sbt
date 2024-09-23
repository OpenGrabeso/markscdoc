organization := "com.todesking"

name := "nyandoc"

version := "0.1.0"

scalaVersion := "2.12.19"

publishTo := Some(Resolver.file("com.todesking", file("./repo/"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies ++= Seq(
  "org.jsoup"               %  "jsoup"         % "1.18.1",
  "org.json4s"              %% "json4s-native" % "3.6.12"
)

scalacOptions ++= Seq("-deprecation", "-feature")
