organization := "com.todesking"

name := "nyandoc"

version := "0.1.0"

scalaVersion := "2.12.19"

publishTo := Some(Resolver.file("com.todesking", file("./repo/"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies ++= Seq(
  "org.jsoup"               %  "jsoup"         % "1.15.4",
  "org.json4s"              %% "json4s-native" % "3.6.12"
)

scalacOptions ++= Seq("-deprecation", "-feature")

Compile / sourceGenerators += Def.task {
  val file = (Compile / sourceManaged).value / "Version.scala"
  IO.write(file, s"""package com.todesking.nyandoc
                    |object Version {
                    |  val string = "${version.value}"
                    |}""".stripMargin)
  Seq(file)
}

compile := (Compile / compile).dependsOn(Def.task {
  val content = s"""[app]
                   |  version: ${version.value.replaceAll("\\+$", "")}
                   |  org: ${organization.value}
                   |  name: ${name.value}
                   |  class: com.todesking.nyandoc.Main
                   |[scala]
                   |  version: ${scalaVersion.value}
                   |[repositories]
                   |  local
                   |  scala-tools-releases
                   |  maven-central
                   |  todesking: http://todesking.github.io/mvn/""".stripMargin
  val dir = (Compile / sourceDirectory).value / "conscript" / "nyandoc"
  IO.createDirectory(dir)
  val launchconfig = dir / "launchconfig"
  IO.write(launchconfig, content)
}).value

