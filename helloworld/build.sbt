name := """helloworld"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean).disablePlugins(PlayFilters)

scalaVersion := "2.12.4"
// resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)

resolvers += Resolver.typesafeRepo("releases")

resolvers += Resolver.sonatypeRepo("releases")

libraryDependencies ++= Seq(
  guice,
  filters,
  evolutions,
  javaJdbc,
  evolutions,
  javaWs,
  javaJpa,
  "org.webjars" % "bootstrap" % "3.3.4",
  "org.mindrot" % "jbcrypt" % "0.4",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "com.typesafe.play" %% "play-json" % "2.6.10",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "be.objectify" %% "deadbolt-java" % "2.6.0",
  "org.apache.poi" % "poi" % "3.8",
  "org.apache.poi" % "poi-ooxml" % "3.9"

)