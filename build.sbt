name := "linkstore-scala"

version := "0.1"

scalaVersion := "2.13.1"


libraryDependencies += "info.picocli" % "picocli" % "4.1.2"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"
libraryDependencies += "redis.clients" % "jedis" % "3.2.0"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.29"