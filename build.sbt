name := """ecommerce"""

version := "1.0-SNAPSHOT"


lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)


scalaVersion := "2.11.2"


libraryDependencies ++= Seq(
  jdbc,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.5.Final",
  "mysql" % "mysql-connector-java" % "5.1.30",
  "org.scaldi" %% "scaldi-play" % "0.3.3",
  "org.json4s" %% "json4s-native" % "3.2.9",
  "org.json4s" %% "json4s-jackson" % "3.2.9",
  cache
)
